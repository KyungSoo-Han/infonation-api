package kr.infonation.controller.center;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.service.center.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/center")
public class CenterApiController {
    private final CenterService centerService;

    @GetMapping
    public ResponseDto<List<CenterDto>> findCenter(){
        return ResponseDto.SuccessResponse(centerService.findCenter(), HttpStatus.OK);
    }

}
