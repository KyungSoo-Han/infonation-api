package kr.infonation.service.zone;

import kr.infonation.dto.zone.ZoneDto;
import kr.infonation.repository.zone.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ZoneService {
    private final ZoneRepository zoneRepository;


    public ZoneDto.CreateResponse createZone(ZoneDto.CreateRequest request){

        return new ZoneDto.CreateResponse();
    }
}
