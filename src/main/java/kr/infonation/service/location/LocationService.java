package kr.infonation.service.location;

import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.location.Location;
import kr.infonation.domain.zone.Zone;
import kr.infonation.dto.location.LocationDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.location.LocationRepository;
import kr.infonation.repository.zone.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final ZoneRepository zoneRepository;
    private final CenterRepository centerRepository;
    private final BizRepository bizRepository;

    @Transactional
    public LocationDto.CreateResponse createLocation(LocationDto.CreateRequest request){
        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(() -> new EntityNotFoundException("사업장을 찾을 수 없습니다."));
        Center center = centerRepository.findById(request.getCenterId()).orElseThrow(() -> new EntityNotFoundException("센터를 찾을 수 없습니다."));
        Zone zone = zoneRepository.findById(request.getZoneId()).orElseThrow(() -> new EntityNotFoundException("존을 찾을 수 없습니다."));

        Location location = locationRepository.save((request.toEntity(zone, center, biz)));

        LocationDto.CreateResponse response = new LocationDto.CreateResponse(location.getId(), location.getCode(), location.getName(), location.getLocationType()
                , location.getAxisX(), location.getAxisY(), location.getAxisZ(), location.isStatus(), biz.getId(), center.getId(), zone.getId());

        return response;
    }
}
