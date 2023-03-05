package kr.infonation.controller.cust;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.cust.Customer;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.service.cust.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseDto<List<CustomerDto.Response>> findCustomer(@RequestParam(required = false) Long bizId ){
        List<CustomerDto.Response> res = customerService.findCustomer(bizId);

        return ResponseDto.SuccessResponse(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseDto<CustomerDto.CreateResponse> createCustomer(@RequestBody CustomerDto.CreateRequest request) throws CustomException {
        System.out.println("request = " + request);
        Customer customer = customerService.createCustomer(request);

        CustomerDto.CreateResponse response = new CustomerDto.CreateResponse(customer);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
