package kr.infonation.service.inbound;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.domain.inbound.InboundItem;
import kr.infonation.domain.item.Item;
import kr.infonation.domain.item.ItemStock;
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
import kr.infonation.repository.item.ItemStockQueryRepository;
import kr.infonation.repository.item.ItemStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
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
    private final ItemStockQueryRepository stockQueryRepository;
    private final ItemStockRepository stockRepository;


    public List<InboundQueryDto> findInbound(String inboundNo) {
       return inboundQueryRepository.findInbound(inboundNo);
    }

    public List<InboundQueryDto> findInboundList(InboundSrchCond srchCond){
        return inboundQueryRepository.findInboundList(srchCond);
    }
    @Transactional
    public InboundDto.CreateResponse createInboundAndItem(InboundDto.CreateRequest request) throws Exception {

        Biz biz = findByIdOrThrow(bizRepository, request.getBizId(), "사업장을 찾을 수 없습니다.");
        Center center = findByIdOrThrow(centerRepository, request.getCenterId(), "센터를 찾을 수 없습니다.");
        Customer customer = findByIdOrThrow(customerRepository, request.getCustomerId(), "화주사를 찾을 수 없습니다.");
        Supplier supplier = findByIdOrThrow(supplierRepository, request.getSupplierId(), "공급사를 찾을 수 없습니다.");

        // 전표번호 가져오기 -> 이미 채번된 전표일 경우 그대로 반환
        String slipNo = getSlipNo(request);

        // 전표 정보 저장
        Inbound inbound = inboundRepository.save(request.toEntity(slipNo, biz, center, customer, supplier));

        // 기존 저장된 품목 데이터 가져오기 -> 응답시 사용
        List<InboundItem> inboundItemList = getSavedInboundItem(request, slipNo, inbound);

        // 전표 품목 정보 저장
        for (InboundDto.ItemCreateRequest req : request.getItemCreateRequest()) {

            Item item = findByIdOrThrow(itemRepository, req.getItemId(), "품목을 찾을 수 없습니다.");

            Optional<ItemStock> getItemStock = stockQueryRepository.getItemStock(biz.getId(), center.getId(), customer.getId(), item.getId(),
                    req.getMakeLotNo(), req.getMakeDate(), req.getExpDate(), "A01-01-01");

            InboundItem inboundItem;
            if (req.getInboundSeq() == null) {
                // 순번이 없을 경우 신규 생성
                inboundItem = inboundItemRepository.save(
                        req.toEntity(inbound, item, req.getQty(), req.getPrice(), req.getExpDate(),
                                req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark()));

                inbound.addInboundItem(inboundItem);

                setStockQty(biz, center, customer, req, item, getItemStock, inboundItem);

            } else {
                // 순번이 있는 경우에는 find후 엔티티 update
                inboundItem = inboundItemRepository.findById(req.getInboundSeq()).get();

                // 기존 입고량과 변경된 입고량 계산을 위해 update전 수행
                setStockQty(biz, center, customer, req, item, getItemStock, inboundItem);

                inboundItem.update(item, req.getQty(), req.getPrice(), req.getExpDate(),
                                req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark(), req.isStatus());

            }
        }

        // 기존에 저장된 품목정보와 신규로 입력된 품목정보를 응답 객체로 생성
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
     * 재고 반영 (개선 필요)
     * @param biz
     * @param center
     * @param customer
     * @param req
     * @param item
     * @param getItemStock
     * @param inboundItem
     */
    private void setStockQty(Biz biz, Center center, Customer customer, InboundDto.ItemCreateRequest req, Item item, Optional<ItemStock> getItemStock, InboundItem inboundItem) {

        if(getItemStock.isEmpty()){
            // 재고가 없으면 생성
            ItemStock itemStock = ItemStock.builder()
                    .biz(biz).center(center).customer(customer).item(item)
                    .stockQty(req.getQty()).location("A01-01-01").expDate(req.getExpDate()).makeDate(req.getMakeDate())
                    .makeLotNo(req.getMakeLotNo()).build();

            stockRepository.save(itemStock);
        }
        else{
            // 재고가 있는데 수량이 변경되면 계산하여 재고 반영
            ItemStock itemStock = getItemStock.get();
            // 22- 10
            int modifyInboundQty = req.getQty() - inboundItem.getQty();
            System.out.println("req.getQty() = " + req.getQty());
            System.out.println("inboundItem.getQty() = " + inboundItem.getQty());

            itemStock.inboundStock(modifyInboundQty);
        }
    }

    /***
     * 기존에 입력된 입고 품목정보 가져오기
     * @param request
     * @param slipNo
     * @param inbound
     * @return
     */
    private List<InboundItem> getSavedInboundItem(InboundDto.CreateRequest request, String slipNo, Inbound inbound) {
        List<InboundItem> inboundItemList = inbound.getInboundItemList();

        if (!request.getInboundNo().isEmpty()) {
            List<InboundItem> getInboundItem = inboundItemRepository.findByInboundNo(request.getBizId(), slipNo)
                                                                    .stream()
                                                                    .collect(Collectors.toList());
            inboundItemList.addAll(getInboundItem);
        }
        return inboundItemList;
    }

    /***
     * 전표번호 생성
     * @param request
     * @return
     * @throws CustomException
     */
    private String getSlipNo(InboundDto.CreateRequest request) throws CustomException {

        String slipNo = request.getInboundNo().isEmpty()
                ? slipNoService.generateSlipNo("I", request.getInboundDate().replace("-", "")) : request.getInboundNo();
        return slipNo;
    }

    // findByIdOrThrow 메서드
    private <T> T findByIdOrThrow(CrudRepository<T, Long> repository, Long id, String errorMessage)
                                                                            throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

    @Transactional
    public void inboundApprove(List<InboundDto.InboundApproveRequest> req){
        inboundQueryRepository.inboundApprove(req);
    }

    public void inboundCancel(List<InboundDto.InboundCancelRequest> request) {

        inboundQueryRepository.inboundCancel(request);
    }

    @Transactional
    public void deleteInbound(Long bizId, String inboundNo) {

        inboundItemRepository.findByInboundNo(bizId, inboundNo).orElseThrow(()-> new EntityNotFoundException("입고정보의 품목데이터가 없습니다."));
        inboundQueryRepository.findInboundOptional(inboundNo).orElseThrow(()-> new EntityNotFoundException("입고정보의 품목데이터가 없습니다."));

        inboundItemRepository.deleteByInboundNo(inboundNo);
        inboundRepository.deleteByInboundNo(inboundNo);

    }
}
