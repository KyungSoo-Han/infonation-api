package kr.infonation.controller.inbound;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.SupplierDto;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.service.inbound.InboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inbound")
public class InboundApiController {
    private final InboundService inboundService;

    @PostMapping
    public ResponseDto<InboundDto.CreateResponse> createInbound (@RequestBody InboundDto.CreateRequest request) throws CustomException {

        Map<String, Object> objectMap = inboundService.createInbound(request);

        Biz biz  = (Biz) objectMap.get("Biz");
        Center center  = (Center) objectMap.get("Center");
        Customer customer  = (Customer) objectMap.get("Customer");
        Supplier supplier  = (Supplier) objectMap.get("Supplier");
        Inbound inbound  = (Inbound) objectMap.get("Inbound");

        BizDto.Response bizDto = new BizDto.Response(biz);
        CenterDto centerDto = new CenterDto(center.getName(),center.getAddress(), bizDto);
        CustomerDto.Response customerDto = new CustomerDto.Response(customer);
        SupplierDto.Response supplierDto = new SupplierDto.Response(supplier);
        InboundDto.CreateResponse response = new InboundDto.CreateResponse(inbound, bizDto, centerDto, customerDto, supplierDto);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
