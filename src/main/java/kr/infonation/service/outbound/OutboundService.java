package kr.infonation.service.outbound;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.item.Item;
import kr.infonation.domain.outbound.Outbound;
import kr.infonation.domain.outbound.OutboundItem;
import kr.infonation.dto.outbound.OutboundQueryDto;
import kr.infonation.dto.outbound.OutboundDto;
import kr.infonation.dto.outbound.OutboundDto;
import kr.infonation.dto.outbound.OutboundQueryDto;
import kr.infonation.repository.base.SlipNoService;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.DestinationRepository;
import kr.infonation.repository.outbound.OutboundQueryRepository;
import kr.infonation.repository.item.ItemRepository;
import kr.infonation.repository.outbound.OutboundItemRepository;
import kr.infonation.repository.outbound.OutboundQueryRepository;
import kr.infonation.repository.outbound.OutboundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OutboundService {

    private final OutboundRepository outboundRepository;
    private final OutboundItemRepository outboundItemRepository;
    private final BizRepository bizRepository;
    private final CenterRepository centerRepository;
    private final CustomerRepository customerRepository;
    private final DestinationRepository destinationRepository;
    private final ItemRepository itemRepository;
    private final SlipNoService slipNoService;
    private final OutboundQueryRepository outboundQueryRepository;

    public List<OutboundQueryDto> findOutbound(String outboundNo) {
        return outboundQueryRepository.findOutbound(outboundNo);

    }
    @Transactional
    public OutboundDto.CreateResponse createOutboundAndItem(OutboundDto.CreateRequest request) throws CustomException {

        Biz biz = findByIdOrThrow(bizRepository, request.getBizId(), "사업장을 찾을 수 없습니다.");
        Center center = findByIdOrThrow(centerRepository, request.getCenterId(), "센터를 찾을 수 없습니다.");
        Customer customer = findByIdOrThrow(customerRepository, request.getCustomerId(), "화주사를 찾을 수 없습니다.");
        Destination destination = findByIdOrThrow(destinationRepository, request.getDestinationId(), "공급사를 찾을 수 없습니다.");

        // 전표번호 가져오기 -> 이미 채번된 전표일 경우 그대로 반환
        String slipNo = getSlipNo(request);
        Outbound outbound = outboundRepository.save(request.toEntity(slipNo, biz, center, customer, destination));

        // 기존 저장된 품목 데이터 가져오기 -> 응답시 사용
        List<OutboundItem> outboundItemList = getSavedOutboundItem(request, slipNo, outbound);

        // 전표 품목 정보 저장
        for (OutboundDto.ItemCreateRequest req : request.getItemCreateRequest()) {
            System.out.println("req = " + req);
            Item item = findByIdOrThrow(itemRepository, req.getItemId(), "품목을 찾을 수 없습니다.");

            if (req.getOutboundSeq() == null) {
                // 순번이 없을 경우 신규 생성
                OutboundItem outboundItem = outboundItemRepository.save(
                        req.toEntity(outbound, item, req.getQty(), req.getPrice(), req.getExpDate(),
                                req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark()));

                outbound.addOutboundItem(outboundItem);
            } else {
                // 순번이 있는 경우에는 find후 엔티티 update
                outboundItemRepository.findById(req.getOutboundSeq())
                        .get()
                        .update(item, req.getQty(), req.getPrice(), req.getExpDate(),
                                req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark(), req.isStatus());
            }
        }

        // 기존에 저장된 품목정보와 신규로 입력된 품목정보를 응답 객체로 생성
        Stream<OutboundDto.ItemCreateResponse> itemCreateResponseStream =
                outboundItemList.stream().map(m -> new OutboundDto.ItemCreateResponse(m, m.getItem().getId(), m.getItem().getName()));
        
        return new OutboundDto.CreateResponse(outbound,
                                                biz.getId(),
                                                biz.getName(),
                                                center.getId(),
                                                center.getName(),
                                                customer.getId(),
                                                customer.getName(),
                                                destination.getId(),
                                                destination.getName(),
                                                itemCreateResponseStream.collect(Collectors.toList()));
    }

    /***
     * 기존에 입력된 출고 품목정보 가져오기
     * @param request
     * @param slipNo
     * @param outbound
     * @return
     */
    private List<OutboundItem> getSavedOutboundItem(OutboundDto.CreateRequest request, String slipNo, Outbound outbound) {
        List<OutboundItem> outboundItemList = outbound.getOutboundItemList();

        if (request.getOutboundNo() != null) {
            List<OutboundItem> getOutboundItem = outboundItemRepository.findByOutboundNo(slipNo)
                                                                        .stream()
                                                                        .collect(Collectors.toList());;
            outboundItemList.addAll(getOutboundItem);
        }

        return outboundItemList;
    }
    /***
     * 전표번호 생성
     * @param request
     * @return
     * @throws CustomException
     */
    private String getSlipNo(OutboundDto.CreateRequest request) throws CustomException {
        System.out.println("request = " + request);
        String slipNo = request.getOutboundNo() == null
                ? slipNoService.generateSlipNo("O", request.getOutboundDate().replace("-", ""))
                                                                    : request.getOutboundNo();
        return slipNo;
    }

    // findByIdOrThrow 메서드
    private <T> T findByIdOrThrow(CrudRepository<T, Long> repository, Long id, String errorMessage)
            throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

    @Transactional
    public void deleteOutbound(Long bizId, String outboundNo) {

        outboundItemRepository.findByOutboundNo(outboundNo).orElseThrow(()-> new EntityNotFoundException("입고정보의 품목데이터가 없습니다."));
        outboundQueryRepository.findOutboundOptional(outboundNo).orElseThrow(()-> new EntityNotFoundException("입고정보의 품목데이터가 없습니다."));

        outboundItemRepository.deleteByOutboundNo(outboundNo);
        outboundRepository.deleteByOutboundNo(outboundNo);
    }
}
