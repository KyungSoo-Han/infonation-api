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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inbound")
public class InboundApiController {
    private final InboundService inboundService;

    @PostMapping
    public ResponseDto<InboundDto.CreateResponse> createInbound (@RequestBody List<InboundDto.CreateRequest> request) throws Exception {

        Map<String, Object> responseMap = inboundService.createInboundAndItem(request);

        Inbound inbound = (Inbound) responseMap.get("Inbound");
        List<InboundItem> inboundItem = (List<InboundItem>) responseMap.get("InboundItem");

        BizDto.Response bizDto = new BizDto.Response((Biz) responseMap.get("Biz"));
        CenterDto centerDto = new CenterDto(
                ((Center) responseMap.get("Center")).getName(),
                ((Center) responseMap.get("Center")).getAddress(),
                bizDto
        );
        CustomerDto.Response customerDto = new CustomerDto.Response((Customer) responseMap.get("Customer"));
        SupplierDto.Response supplierDto = new SupplierDto.Response((Supplier) responseMap.get("Supplier"));

        Stream<InboundDto.ItemCreateResponse> itemCreateResponseStream =
                inboundItem.stream().map(m -> new InboundDto.ItemCreateResponse(m, new ItemDto.Response(m.getItem())));

        InboundDto.CreateResponse response = new InboundDto.CreateResponse(
                inbound,
                bizDto,
                centerDto,
                customerDto,
                supplierDto,
                itemCreateResponseStream.collect(Collectors.toList())
        );

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
