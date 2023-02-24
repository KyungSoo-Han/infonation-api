package kr.infonation.controller.as;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.domain.as.AfterService;
import kr.infonation.dto.as.AfterServiceDto;
import kr.infonation.service.as.AfterServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/as")
public class AfterServiceController {

    private final AfterServiceService afterService;

    @PostMapping
    public ResponseDto<AfterServiceDto.Response> createAfterService(
                                                        @RequestBody AfterServiceDto.CreateRequest request){
        AfterService as = afterService.createAs(request);
        AfterServiceDto.Response response= new AfterServiceDto.Response(as);
        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
