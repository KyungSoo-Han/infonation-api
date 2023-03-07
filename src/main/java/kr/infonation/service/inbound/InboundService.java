package kr.infonation.service.inbound;

import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.domain.inbound.InboundItem;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.repository.base.SlipNoService;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.SupplierRepository;
import kr.infonation.repository.inbound.InboundItemRepository;
import kr.infonation.repository.inbound.InboundRepository;
import kr.infonation.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Transactional
    public InboundDto.CreateResponse createInboundAndItem(InboundDto.CreateRequest request) throws Exception {

        /*
        Biz biz = bizRepository.findById(request.getBizId())
                .orElseThrow(() -> new EntityNotFoundException("사업장을 찾을 수 없습니다."));
        Center center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new EntityNotFoundException("센터를 찾을 수 없습니다."));
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("화주사를 찾을 수 없습니다."));
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException("공급사를 찾을 수 없습니다."));
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("품목을 찾을 수 없습니다."));
        */
        System.out.println("request = " + request);
        Biz biz = findByIdOrThrow(bizRepository, request.getBizId(), "사업장을 찾을 수 없습니다.");
        Center center = findByIdOrThrow(centerRepository, request.getCenterId(), "센터를 찾을 수 없습니다.");
        Customer customer = findByIdOrThrow(customerRepository, request.getCustomerId(), "화주사를 찾을 수 없습니다.");
        Supplier supplier = findByIdOrThrow(supplierRepository, request.getSupplierId(), "공급사를 찾을 수 없습니다.");

        /**
         * 전표번호 생성
         */
        String slipNo = slipNoService.generateSlipNo("I", request.getInboundDate().replace("-",""));

        Inbound inbound = inboundRepository.save(request.toEntity(slipNo, biz, center, customer, supplier));

        for (InboundDto.ItemCreateRequest req : request.getItemCreateRequest()) {

            Item item = findByIdOrThrow(itemRepository, req.getItemId(), "품목을 찾을 수 없습니다.");

            InboundItem inboundItem = inboundItemRepository.save(
                    req.toEntity(inbound, item, req.getQty(), req.getPrice(), req.getExpDate(),
                            req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark()));

            inbound.addInboundItem(inboundItem);
        }

        List<InboundItem> inboundItemList = inbound.getInboundItemList();
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

        /*
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("Biz", biz);
        rtnMap.put("Center", center);
        rtnMap.put("Customer", customer);
        rtnMap.put("Supplier", supplier);
        rtnMap.put("Inbound", inbound);
        rtnMap.put("InboundItem", inbound.getInboundItemList());
        */

        return response;
    }

    // findByIdOrThrow 메서드
    private <T> T findByIdOrThrow(CrudRepository<T, Long> repository, Long id, String errorMessage)
                                                                            throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

}
