package kr.infonation.controller.cust;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.domain.cust.Customer;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.service.cust.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cust")
public class CustomerApiController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseDto<CustomerDto.CreateResponse> createCustomer(@RequestBody CustomerDto.CreateRequest request){
        Customer customer = customerService.createCustomer(request);
        CustomerDto.CreateResponse response = new CustomerDto.CreateResponse(customer);
        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
