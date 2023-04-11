package kr.infonation.controller.zone;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.dto.zone.ZoneDto;
import kr.infonation.service.zone.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zone")
@RequiredArgsConstructor
public class ZoneApiController {
    private final ZoneService zoneService;

    @PostMapping
    public ResponseDto<ZoneDto.CreateResponse> createZone(@RequestBody ZoneDto.CreateRequest request){

        ZoneDto.CreateResponse response = zoneService.createZone(request);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
