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
import kr.infonation.repository.base.SlipNoRepository;
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
    private final SlipNoRepository slipNoRepository;
    private final ItemRepository itemRepository;


    @Transactional
    public Map<String, Object> createInboundAndItem(List<InboundDto.CreateRequest> request) throws Exception {

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

        Biz biz = findByIdOrThrow(bizRepository, request.get(0).getBizId(), "사업장을 찾을 수 없습니다.");
        Center center = findByIdOrThrow(centerRepository, request.get(0).getCenterId(), "센터를 찾을 수 없습니다.");
        Customer customer = findByIdOrThrow(customerRepository, request.get(0).getCustomerId(), "화주사를 찾을 수 없습니다.");
        Supplier supplier = findByIdOrThrow(supplierRepository, request.get(0).getSupplierId(), "공급사를 찾을 수 없습니다.");

        String slipNo = slipNoRepository.getSlipNo("I", request.get(0).getInboundDate());

        Inbound inbound = inboundRepository.save(request.get(0).toEntity(slipNo, biz, center, customer, supplier));

        for (InboundDto.CreateRequest req : request) {

            Item item = findByIdOrThrow(itemRepository, req.getItemId(), "품목을 찾을 수 없습니다.");

            InboundItem inboundItem = inboundItemRepository.save(
                    req.toItemEntity(inbound, item, req.getQty(), req.getPrice(), req.getExpDate(),
                            req.getMakeLotNo(), req.getMakeDate(), req.getSubRemark()));

            inbound.addInboundItem(inboundItem);
        }

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("Biz", biz);
        rtnMap.put("Center", center);
        rtnMap.put("Customer", customer);
        rtnMap.put("Supplier", supplier);
        rtnMap.put("Inbound", inbound);
        rtnMap.put("InboundItem", inbound.getInboundItemList());

        return rtnMap;
    }

    // findByIdOrThrow 메서드
    private <T> T findByIdOrThrow(CrudRepository<T, Long> repository, Long id, String errorMessage) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

}
