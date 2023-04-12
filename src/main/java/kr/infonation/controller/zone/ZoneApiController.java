package kr.infonation.controller.zone;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.dto.zone.ZoneDto;
import kr.infonation.service.zone.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zone")
@RequiredArgsConstructor
public class ZoneApiController {
    private final ZoneService zoneService;

    @GetMapping("/list")
    public ResponseDto<List<ZoneDto.DataResponse>> findAll(){

        List<ZoneDto.DataResponse> responseList = zoneService.findAll();

        return ResponseDto.SuccessResponse(responseList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseDto<ZoneDto.CreateResponse> createZone(@RequestBody ZoneDto.CreateRequest request){

        ZoneDto.CreateResponse response = zoneService.createZone(request);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
