package kr.infonation.service.as;

import kr.infonation.domain.as.AfterService;
import kr.infonation.dto.as.AfterServiceDto;
import kr.infonation.repository.as.AfterServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AfterServiceService {

    private final AfterServiceRepository repository;

    @Transactional
    public AfterService createAs(AfterServiceDto.CreateRequest request){
        return repository.save(request.toEntity());
    }

}
