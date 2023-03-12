package kr.infonation.common.service;

import kr.infonation.common.dto.SelectDto;
import kr.infonation.common.repository.SelectRepository;
import kr.infonation.config.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SelectService {
    private final SelectRepository selectRepository;

    public List<SelectDto> selectTableData( String gbn, Long bizId, Long parentId, Long codeId, String codeName) throws CustomException {
        return selectRepository.selectTableData(gbn, bizId, parentId, codeId, codeName);
    }
}
