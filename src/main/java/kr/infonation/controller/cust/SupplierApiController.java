package kr.infonation.controller.cust;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.SupplierDto;
import kr.infonation.service.cust.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/supplier")
public class SupplierApiController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseDto<SupplierDto.CreateResponse> createSupplier(@RequestBody SupplierDto.CreateRequest request) throws CustomException {

        Map<String, Object> objectMap = supplierService.createSupplier(request);
        Biz biz  = (Biz) objectMap.get("biz");
        Customer customer  = (Customer) objectMap.get("customer");
        Supplier supplier  = (Supplier) objectMap.get("supplier");

        BizDto.Response bizDto = new BizDto.Response(biz);
        CustomerDto.Response customerDto = new CustomerDto.Response(customer);
        SupplierDto.CreateResponse response = new SupplierDto.CreateResponse(supplier, bizDto, customerDto);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
