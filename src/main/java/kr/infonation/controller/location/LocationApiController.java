package kr.infonation.controller.location;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.dto.location.LocationDto;
import kr.infonation.dto.zone.ZoneDto;
import kr.infonation.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationApiController {

    private final LocationService locationService;

    @PostMapping
    public ResponseDto<LocationDto.CreateResponse> createLocation(@RequestBody LocationDto.CreateRequest request){
        LocationDto.CreateResponse response = locationService.createLocation(request);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
