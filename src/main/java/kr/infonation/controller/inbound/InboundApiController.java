package kr.infonation.controller.inbound;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.dto.inbound.InboundQueryDto;
import kr.infonation.dto.inbound.InboundSrchCond;
import kr.infonation.service.inbound.InboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inbound")
public class InboundApiController {
    private final InboundService inboundService;

    @GetMapping
    public ResponseDto<List<InboundDto.Response>> findInbound(@RequestParam String inboundNo) {
        List<InboundQueryDto> flats = inboundService.findInbound(inboundNo);

        Map<InboundDto.Response, List<InboundDto.ItemResponse>> listMap = flats.stream()
                .collect(groupingBy(o -> new InboundDto.Response(o.getBizId(), o.getBizName(), o.getCenterId(), o.getCenterName(), o.getCustomerId(), o.getCustomerName(),
                                o.getSupplierId(), o.getSupplierName(), o.getRemark(), o.getInboundDate(), o.getInboundNo(), o.getInboundGbn(), o.getInboundExpDate()),
                        mapping(o -> new InboundDto.ItemResponse(o.getInboundNo(), o.getInboundSeq(), o.getItemId(), o.getItemName(),
                                o.getQty(), o.getPrice(), o.isStatus(), o.getSubRemark(), o.getExpDate(), o.getMakeLotNo(), o.getMakeDate()), toList())
                ));

        List<InboundDto.Response> responseList = listMap.entrySet().stream()
                .map(e -> new InboundDto.Response(e.getKey().getBizId(), e.getKey().getBizName(), e.getKey().getCenterId(),
                        e.getKey().getCenterName(), e.getKey().getCustomerId(), e.getKey().getCustomerName(), e.getKey().getSupplierId(), e.getKey().getSupplierName(),
                        e.getKey().getRemark(), e.getKey().getInboundDate(), e.getKey().getInboundNo(), e.getKey().getInboundGbn(),e.getKey().getInboundExpDate(), e.getValue()))
                .collect(toList());

        return ResponseDto.SuccessResponse(responseList, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseDto<List<InboundQueryDto>> findInboundList(@RequestBody InboundSrchCond srchCond){

        List<InboundQueryDto> responseList = inboundService.findInboundList(srchCond);

        return ResponseDto.SuccessResponse(responseList , HttpStatus.OK);
    }

    @PostMapping
    public ResponseDto<InboundDto.CreateResponse> createInbound (@RequestBody InboundDto.CreateRequest request) throws Exception {

        InboundDto.CreateResponse response = inboundService.createInboundAndItem(request);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

    @PostMapping("/approve")
    public ResponseDto<String> inboundApprove(@RequestBody List<InboundDto.InboundApproveRequest> request){
        System.out.println("request = " + request);
        inboundService.inboundApprove(request);

        return ResponseDto.SuccessResponse("", HttpStatus.OK);
    }
    @PostMapping("/cancel")
    public ResponseDto<String> inboundCancel(@RequestBody List<InboundDto.InboundCancelRequest> request){
        System.out.println("request = " + request);
        inboundService.inboundCancel(request);

        return ResponseDto.SuccessResponse("", HttpStatus.OK);
    }

    @DeleteMapping("/{bizId}/{inboundNo}")
    public ResponseDto<String> deleteInbound(@PathVariable Long bizId, @PathVariable String inboundNo){
        inboundService.deleteInbound(bizId, inboundNo);
        return ResponseDto.SuccessResponse(inboundNo, HttpStatus.OK);
    }
}
