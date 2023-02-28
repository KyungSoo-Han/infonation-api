package kr.infonation.controller.center;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.center.Center;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.service.biz.BizService;
import kr.infonation.service.center.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/center")
public class CenterApiController {
    private final CenterService centerService;
    private final BizService bizService ;

    @GetMapping
    public ResponseDto<List<CenterDto>> findCenter(){
        return ResponseDto.SuccessResponse(centerService.findCenter(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseDto<CenterDto.CreateResponse> createCenter(@RequestBody CenterDto.CreateRequest request){

        Center center = centerService.createCenter(request);
        BizDto.Response bizDto = new BizDto.Response(center.getBiz());

        CenterDto.CreateResponse response = new CenterDto.CreateResponse(center.getId(),center.getName(), center.getAddress(), bizDto );

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseDto<CenterDto.UpdateResponse> updateCenter(@PathVariable Long id, @RequestBody CenterDto.UpdateRequest request) throws CustomException {

        Center center = centerService.updateCenter(request, id);
        BizDto.Response bizDto = new BizDto.Response(center.getBiz());

        CenterDto.UpdateResponse response = new CenterDto.UpdateResponse(center.getId(), center.getName(), center.getAddress(), bizDto);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
