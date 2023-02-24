package kr.infonation.service.biz;


import kr.infonation.domain.biz.Biz;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.repository.biz.BizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BizService {
    private final BizRepository bizRepository;

    @Transactional
    public Biz createBiz(BizDto.CreateRequest request) {
        return bizRepository.save(request.toEntity());
    }

    @Transactional
    public Biz updateBiz(Long id, BizDto.UpdateRequest request){
        Biz biz = bizRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사업장입니다."));
        biz.update(request);
        return biz;
    }

}
