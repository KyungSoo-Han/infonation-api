package kr.infonation.controller.outbound;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.dto.outbound.OutboundQueryDto;
import kr.infonation.dto.outbound.OutboundSrchCond;
import kr.infonation.dto.outbound.OutboundDto;
import kr.infonation.dto.outbound.OutboundQueryDto;
import kr.infonation.service.outbound.OutboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/outbound")
public class OutboundApiController {
    private final OutboundService outboundService;
    @GetMapping
    public ResponseDto<List<OutboundDto.Response>> findOutbound(@RequestParam String outboundNo) {
        List<OutboundQueryDto> flats = outboundService.findOutbound(outboundNo);

        Map<OutboundDto.Response, List<OutboundDto.ItemResponse>> listMap = flats.stream()
                .collect(groupingBy(o -> new OutboundDto.Response(o.getBizId(), o.getBizName(), o.getCenterId(), o.getCenterName(), o.getCustomerId(), o.getCustomerName(),
                                o.getDestinationId(), o.getDestinationName(), o.getRemark(), o.getOutboundDate(), o.getOutboundNo(), o.getOutboundGbn(), o.getOutboundExpDate()),
                        mapping(o -> new OutboundDto.ItemResponse(o.getOutboundNo(), o.getOutboundSeq(), o.getItemId(), o.getItemName(),
                                o.getQty(), o.getPrice(), o.isStatus(), o.getSubRemark(), o.getExpDate(), o.getMakeLotNo(), o.getMakeDate()), toList())
                ));

        List<OutboundDto.Response> responseList = listMap.entrySet().stream()
                .map(e -> new OutboundDto.Response(e.getKey().getBizId(), e.getKey().getBizName(), e.getKey().getCenterId(),
                        e.getKey().getCenterName(), e.getKey().getCustomerId(), e.getKey().getCustomerName(), e.getKey().getDestinationId(), e.getKey().getDestinationName(),
                        e.getKey().getRemark(), e.getKey().getOutboundDate(), e.getKey().getOutboundNo(), e.getKey().getOutboundGbn(),e.getKey().getOutboundExpDate(), e.getValue()))
                .collect(toList());

        return ResponseDto.SuccessResponse(responseList, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseDto<List<OutboundQueryDto>> findOutboundList(@RequestBody OutboundSrchCond srchCond){

        List<OutboundQueryDto> responseList = outboundService.findOutboundList(srchCond);

        return ResponseDto.SuccessResponse(responseList , HttpStatus.OK);
    }

    
    @PostMapping
    public ResponseDto<OutboundDto.CreateResponse> createOutbound(@RequestBody OutboundDto.CreateRequest request) throws CustomException {

        OutboundDto.CreateResponse outbound = outboundService.createOutboundAndItem(request);

        return ResponseDto.SuccessResponse(outbound, HttpStatus.OK);
    }

    @DeleteMapping("/{bizId}/{outboundNo}")
    public ResponseDto<String> deleteOutbound(@PathVariable Long bizId, @PathVariable String outboundNo){
        outboundService.deleteOutbound(bizId, outboundNo);
        return ResponseDto.SuccessResponse(outboundNo, HttpStatus.OK);
    }
}
