package kr.infonation.repository.base;

import kr.infonation.config.CustomException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class SlipNoRepositoryTest {

    @Autowired
    SlipNoRepository slipNoRepository;

    @PersistenceContext
    EntityManager entityManager;
    @AfterEach
    @Rollback(true)
    public void tearDown() {
        entityManager.flush();
    }

    @Test
    void getSlipNo() throws CustomException {
        String slipNo = slipNoRepository.getSlipNo("I", "20230304");
        System.out.println("slipNo = " + slipNo);
    }
}