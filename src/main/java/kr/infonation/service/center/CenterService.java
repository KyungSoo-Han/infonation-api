package kr.infonation.service.center;

import kr.infonation.dto.center.CenterDto;
import kr.infonation.repository.center.CenterQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CenterService {

    private final CenterQRepository qRepository;

    public List<CenterDto> findCenter (){
        return qRepository.findCenter();
    }
}
