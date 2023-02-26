package kr.infonation.service.center;

import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.center.CenterRepositoryDsl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CenterService {

    private final CenterRepositoryDsl qRepository;
    private final CenterRepository repository;
    private final BizRepository bizRepository;


    public List<CenterDto> findCenter (){
        return qRepository.findCenter();
    }

    @Transactional
    public Center createCenter(CenterDto.CreateRequest request){
        Biz biz = bizRepository.getReferenceById(request.getBizId());
        Center center = repository.save(request.toEntity(biz));
        return center;
    }
}
