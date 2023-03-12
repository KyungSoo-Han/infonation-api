package kr.infonation.service.cust;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.dto.cust.DestinationDto;
import kr.infonation.dto.cust.SupplierDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final BizRepository bizRepository;
    private final CustomerRepository customerRepository;


    @Transactional
    public Map<String, Object> createSupplier(SupplierDto.CreateRequest request) throws CustomException {

        Biz biz = bizRepository.findById(request.getBizId())
                .orElseThrow(() -> new CustomException("사업장을 찾을 수 없습니다."));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomException("화주사를 찾을 수 없습니다."));
        Supplier supplier = supplierRepository.save(request.toEntity(biz, customer));

        Map<String, Object> rtnObj = new HashMap<>();
        rtnObj.put("biz", biz);
        rtnObj.put("customer", customer);
        rtnObj.put("supplier", supplier);

        return rtnObj;
    }

    public SupplierDto.UpdateResponse updateSupplier(Long id, SupplierDto.UpdateRequest request) {

        Biz biz = bizRepository.findById(request.getBizId())
                .orElseThrow(() -> new EntityNotFoundException("사업장을 찾을 수 없습니다."));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("화주사를 찾을 수 없습니다."));

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("잘못된 배송지 아이디입니다."));

        supplier.update(request);

        return new SupplierDto.UpdateResponse(supplier, biz.getId(),customer.getId());

    }

    public SupplierDto.DeleteResponse deleteSupplier(Long id) {
        supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("잘못된 배송지 아이디입니다."));

        supplierRepository.deleteById(id);

        return new SupplierDto.DeleteResponse(id);

    }
}
