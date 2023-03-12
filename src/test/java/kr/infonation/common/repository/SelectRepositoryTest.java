package kr.infonation.common.repository;

import kr.infonation.common.dto.SelectDto;
import kr.infonation.config.CustomException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SelectRepositoryTest {

    @Autowired
    private SelectRepository selectRepository;

    @Test
    void selectTableData() throws CustomException {
        List<SelectDto> customer = selectRepository.selectTableData("supplier", 1L, 1L, null, null);
        for (SelectDto selectDto : customer) {
            System.out.println("selectDto = " + selectDto);
        }
    }
}