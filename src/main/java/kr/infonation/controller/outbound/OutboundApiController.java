package kr.infonation.controller.outbound;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.dto.outbound.OutboundDto;
import kr.infonation.service.outbound.OutboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/outbound")
public class OutboundApiController {
    private final OutboundService outboundService;

    @PostMapping
    public ResponseDto<OutboundDto.CreateResponse> createOutbound(@RequestBody OutboundDto.CreateRequest request) throws CustomException {

        OutboundDto.CreateResponse outbound = outboundService.createOutbound(request);

        return ResponseDto.SuccessResponse(outbound, HttpStatus.OK);
    }
}
