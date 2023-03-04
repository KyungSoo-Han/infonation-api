package kr.infonation.controller.inbound;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.inbound.Inbound;
import kr.infonation.domain.inbound.InboundItem;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.SupplierDto;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.dto.item.ItemDto;
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
    public ResponseDto<InboundDto.CreateResponse> createInbound (@RequestBody InboundDto.CreateRequest request) throws Exception {

        /*Map<String, Object> objectMap = inboundService.createInboundAndItem(request);

        Biz biz  = (Biz) objectMap.get("Biz");
        Center center  = (Center) objectMap.get("Center");
        Customer customer  = (Customer) objectMap.get("Customer");
        Supplier supplier  = (Supplier) objectMap.get("Supplier");
        Item item  = (Item) objectMap.get("Item");
        Inbound inbound  = (Inbound) objectMap.get("Inbound");
        InboundItem inboundItem  = (InboundItem) objectMap.get("InboundItem");

        BizDto.Response bizDto = new BizDto.Response(biz);
        CenterDto centerDto = new CenterDto(center.getName(),center.getAddress(), bizDto);
        CustomerDto.Response customerDto = new CustomerDto.Response(customer);
        SupplierDto.Response supplierDto = new SupplierDto.Response(supplier);
        ItemDto.Response itemDto = new ItemDto.Response(item);

        InboundDto.CreateResponse response = new InboundDto.CreateResponse(inbound, inboundItem, bizDto, centerDto, customerDto, supplierDto, itemDto);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);*/

        Map<String, Object> objectMap = inboundService.createInboundAndItem(request);

        Inbound inbound = (Inbound) objectMap.get("Inbound");
        InboundItem inboundItem = (InboundItem) objectMap.get("InboundItem");

        BizDto.Response bizDto = new BizDto.Response((Biz) objectMap.get("Biz"));
        CenterDto centerDto = new CenterDto(
                ((Center) objectMap.get("Center")).getName(),
                ((Center) objectMap.get("Center")).getAddress(),
                bizDto
        );
        CustomerDto.Response customerDto = new CustomerDto.Response((Customer) objectMap.get("Customer"));
        SupplierDto.Response supplierDto = new SupplierDto.Response((Supplier) objectMap.get("Supplier"));
        ItemDto.Response itemDto = new ItemDto.Response((Item) objectMap.get("Item"));

        InboundDto.CreateResponse response = new InboundDto.CreateResponse(
                inbound,
                inboundItem,
                bizDto,
                centerDto,
                customerDto,
                supplierDto,
                itemDto
        );

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
