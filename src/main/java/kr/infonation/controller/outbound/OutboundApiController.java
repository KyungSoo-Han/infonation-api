package kr.infonation.controller.outbound;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.dto.inbound.InboundDto;
import kr.infonation.dto.inbound.InboundQueryDto;
import kr.infonation.dto.outbound.OutboundDto;
import kr.infonation.dto.outbound.OutboundQueryDto;
import kr.infonation.service.outbound.OutboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/outbound")
public class OutboundApiController {
    private final OutboundService outboundService;
    @GetMapping
    public ResponseDto<List<OutboundQueryDto> > findInbound(@RequestParam String outboundNo) {
        List<OutboundQueryDto> flats = outboundService.findOutbound(outboundNo);

        return ResponseDto.SuccessResponse(flats, HttpStatus.OK);
    }
    @PostMapping
    public ResponseDto<OutboundDto.CreateResponse> createOutbound(@RequestBody OutboundDto.CreateRequest request) throws CustomException {

        OutboundDto.CreateResponse outbound = outboundService.createOutboundAndItem(request);

        return ResponseDto.SuccessResponse(outbound, HttpStatus.OK);
    }
}
