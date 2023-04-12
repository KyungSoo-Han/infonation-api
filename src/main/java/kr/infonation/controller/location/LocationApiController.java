package kr.infonation.controller.location;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.dto.location.LocationDto;
import kr.infonation.dto.zone.ZoneDto;
import kr.infonation.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationApiController {

    private final LocationService locationService;

    @GetMapping("/list")
    public ResponseDto<List<LocationDto.DataResponse>> findAll(){

        List<LocationDto.DataResponse> responseList = locationService.findAll();

        return ResponseDto.SuccessResponse(responseList,HttpStatus.OK);
    }
    @PostMapping
    public ResponseDto<LocationDto.CreateResponse> createLocation(@RequestBody LocationDto.CreateRequest request){
        LocationDto.CreateResponse response = locationService.createLocation(request);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
