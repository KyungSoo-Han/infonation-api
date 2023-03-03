package kr.infonation.service.inbound;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.repository.base.SlipNoRepository;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.SupplierRepository;
import kr.infonation.repository.inbound.InboundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InboundService {
    private final InboundRepository inboundRepository;
    private final BizRepository bizRepository;
    private final CenterRepository centerRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final SlipNoRepository slipNoRepository;


    @Transactional
    public Map<String, Object> createInbound(InboundDto.CreateRequest request) throws CustomException {

        Biz biz = bizRepository.findById(request.getBizId())
                .orElseThrow(() -> new CustomException("사업장을 찾을 수 없습니다."));
        Center center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new CustomException("센터를 찾을 수 없습니다."));
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomException("화주사를 찾을 수 없습니다."));
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new CustomException("공급사를 찾을 수 없습니다."));

        String slipNo = slipNoRepository.getSlipNo("I", request.getInboundDate());

        Inbound inbound = inboundRepository.save(request.toEntity(slipNo, biz, center, customer, supplier));

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("Biz", biz);
        rtnMap.put("Center", center);
        rtnMap.put("Customer", customer);
        rtnMap.put("Supplier", supplier);
        rtnMap.put("Inbound", inbound);

        return rtnMap;
    }

}
