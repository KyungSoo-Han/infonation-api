package kr.infonation.controller.cust;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.cust.Customer;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.service.cust.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @PutMapping("/{id}")
    public ResponseDto<CustomerDto.UpdateResponse> updateCustomer( @PathVariable Long id,
                                                                        @RequestBody CustomerDto.UpdateRequest request){
        CustomerDto.UpdateResponse updateResponse = customerService.updateCustomer(id, request);
        return ResponseDto.SuccessResponse(updateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<CustomerDto.DeleteResponse> deleteCustomer(@PathVariable Long id){
        CustomerDto.DeleteResponse deleteResponse = customerService.deleteCustomer(id);
        return ResponseDto.SuccessResponse(deleteResponse, HttpStatus.OK);
    }

}
