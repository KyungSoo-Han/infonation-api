package kr.infonation.service.location;

import kr.infonation.dto.location.LocationDto;
import kr.infonation.repository.location.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    public LocationDto.CreateResponse createLocation(){

        return new LocationDto.CreateResponse();
    }
}
