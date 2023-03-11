package kr.infonation.service.cust;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.cust.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BizRepository bizRepository;

    @Transactional
    public Customer createCustomer(CustomerDto.CreateRequest request) throws CustomException {

        Biz biz = bizRepository.findById(request.getBizId())
                .orElseThrow(() -> new CustomException("사업장을 찾을 수 없습니다."));

        return customerRepository.save(request.toEntity(biz));
    }

    public List<CustomerDto.Response> findCustomer(Long bizId) {
        List<Customer> customer = customerRepository.findCustomer(bizId);
        List<CustomerDto.Response> response = customer.stream().map(c -> new CustomerDto.Response(c)).collect(Collectors.toList());

        return response;
    }

    @Transactional
    public CustomerDto.UpdateResponse updateCustomer(Long id, CustomerDto.UpdateRequest request) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));

        customer.update(request);
        return new CustomerDto.UpdateResponse(customer);
    }

    public CustomerDto.DeleteResponse deleteCustomer(Long id) {
        customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));

        customerRepository.deleteById(id);

        return new CustomerDto.DeleteResponse(id);
    }
}
