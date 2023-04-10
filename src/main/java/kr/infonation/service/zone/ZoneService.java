package kr.infonation.service.zone;

import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.domain.zone.Zone;
import kr.infonation.dto.zone.ZoneDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.zone.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ZoneService {
    private final ZoneRepository zoneRepository;
    private final BizRepository bizRepository;
    private final CenterRepository centerRepository;

    @Transactional
    public ZoneDto.CreateResponse createZone(ZoneDto.CreateRequest request){
        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(() -> new EntityNotFoundException("사업장이 존재하지 않습니다."));
        Center center = centerRepository.findById(request.getCenterId()).orElseThrow(() -> new EntityNotFoundException("센터가 존재하지 않습니다."));

        Zone zone = zoneRepository.save(request.toEntity(biz, center, request.getCode(), request.getName(), request.getZoneType(),
                request.getKeepType(), request.isStatus(), request.isStagy()));

        ZoneDto.CreateResponse response = new ZoneDto.CreateResponse(zone.getId(), zone.getCode(), zone.getName(), biz.getId(), center.getId(),
                                                            zone.getZoneType(), zone.getKeepType(), zone.isStagy(), zone.isStatus());

        return response;
    }
}
