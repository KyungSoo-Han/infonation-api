package kr.infonation.service.center;

import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.center.Center;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.center.CenterRepository;
import kr.infonation.repository.center.CenterQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CenterService {

    private final CenterQueryRepository qRepository;
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
    @Transactional
    public Center updateCenter(CenterDto.UpdateRequest request, Long id) throws CustomException {

        Center center = repository.findById(id)
                .orElseThrow(() -> new CustomException("존재하지 않는 센터입니다."));
        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(()-> new CustomException("잘못된 사업장코드입니다."));

        center.update(request, biz);

        return center;
    }

    @Transactional
    public void deleteCenter(Long id) throws CustomException {
        repository.findById(id)
                .orElseThrow(()-> new CustomException("존재하지 않는 센터입니다."));

        repository.deleteById(id);
    }
}
