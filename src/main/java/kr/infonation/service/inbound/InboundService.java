package kr.infonation.service.inbound;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.domain.inbound.InboundItem;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.dto.inbound.InboundQueryDto;
import kr.infonation.dto.inbound.InboundSrchCond;
import kr.infonation.repository.base.SlipNoService;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.SupplierRepository;
import kr.infonation.repository.inbound.InboundItemRepository;
import kr.infonation.repository.inbound.InboundQueryRepository;
import kr.infonation.repository.inbound.InboundRepository;
import kr.infonation.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InboundService {
    private final InboundRepository inboundRepository;
    private final InboundItemRepository inboundItemRepository;
    private final BizRepository bizRepository;
    private final CenterRepository centerRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final SlipNoService slipNoService;
    private final ItemRepository itemRepository;
    private final InboundQueryRepository inboundQueryRepository;

    public List<InboundQueryDto> findInbound(String inboundNo) {
       return inboundQueryRepository.findInbound(inboundNo);

    }

    public List<InboundQueryDto> findInboundList(InboundSrchCond srchCond){
        return inboundQueryRepository.findInboundList(srchCond);
    }
    @Transactional
    public InboundDto.CreateResponse createInboundAndItem(InboundDto.CreateRequest request) throws Exception {

        Biz biz = findByIdOrThrow(bizRepository, request.getBizId(), "???????????? ?????? ??? ????????????.");
        Center center = findByIdOrThrow(centerRepository, request.getCenterId(), "????????? ?????? ??? ????????????.");
        Customer customer = findByIdOrThrow(customerRepository, request.getCustomerId(), "???????????? ?????? ??? ????????????.");
        Supplier supplier = findByIdOrThrow(supplierRepository, request.getSupplierId(), "???????????? ?????? ??? ????????????.");

        // ???????????? ???????????? -> ?????? ????????? ????????? ?????? ????????? ??????
        String slipNo = getSlipNo(request);

        // ?????? ?????? ??????
        Inbound inbound = inboundRepository.save(request.toEntity(slipNo, biz, center, customer, supplier));

        // ?????? ????????? ?????? ????????? ???????????? -> ????????? ??????
        List<InboundItem> inboundItemList = getSavedInboundItem(request, slipNo, inbound);

        // ?????? ?????? ?????? ??????
        for (InboundDto.ItemCreateRequest req : request.getItemCreateRequest()) {

            Item item = findByIdOrThrow(itemRepository, req.getItemId(), "????????? ?????? ??? ????????????.");

            if (req.getInboundSeq() == null) {
                // ????????? ?????? ?????? ?????? ??????
                InboundItem inboundItem = inboundItemRepository.save(
                        req.toEntity(inbound, item, req.getQty(), req.getPrice(), req.getExpDate(),
                                req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark()));

                inbound.addInboundItem(inboundItem);
            } else {
                // ????????? ?????? ???????????? find??? ????????? update
                inboundItemRepository.findById(req.getInboundSeq())
                        .get()
                        .update(item, req.getQty(), req.getPrice(), req.getExpDate(),
                                req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark(), req.isStatus());
            }
        }

        // ????????? ????????? ??????????????? ????????? ????????? ??????????????? ?????? ????????? ??????
        Stream<InboundDto.ItemCreateResponse> itemCreateResponseStream =
                inboundItemList.stream().map(m -> new InboundDto.ItemCreateResponse(m, m.getItem().getId(), m.getItem().getName()));

        InboundDto.CreateResponse response = new InboundDto.CreateResponse(
                inbound,
                biz.getId(),
                biz.getName(),
                center.getId(),
                center.getName(),
                customer.getId(),
                customer.getName(),
                supplier.getId(),
                supplier.getName(),
                itemCreateResponseStream.collect(Collectors.toList())
        );

        return response;
    }

    /***
     * ????????? ????????? ?????? ???????????? ????????????
     * @param request
     * @param slipNo
     * @param inbound
     * @return
     */
    private List<InboundItem> getSavedInboundItem(InboundDto.CreateRequest request, String slipNo, Inbound inbound) {
        List<InboundItem> inboundItemList = inbound.getInboundItemList();

        if (!request.getInboundNo().isEmpty()) {
            List<InboundItem> getInboundItem = inboundItemRepository.findByInboundNo(slipNo);
            inboundItemList.addAll(getInboundItem);
        }

        return inboundItemList;
    }

    /***
     * ???????????? ??????
     * @param request
     * @return
     * @throws CustomException
     */
    private String getSlipNo(InboundDto.CreateRequest request) throws CustomException {

        String slipNo = request.getInboundNo().isEmpty()
                ? slipNoService.generateSlipNo("I", request.getInboundDate().replace("-", "")) : request.getInboundNo();
        return slipNo;
    }

    // findByIdOrThrow ?????????
    private <T> T findByIdOrThrow(CrudRepository<T, Long> repository, Long id, String errorMessage)
                                                                            throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

}
