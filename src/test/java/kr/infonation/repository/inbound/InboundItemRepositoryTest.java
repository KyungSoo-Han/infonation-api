package kr.infonation.repository.inbound;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class InboundItemRepositoryTest {

    @Autowired
    InboundItemRepository inboundItemRepository;

    @Test
    void getInboundMaxSeq() {
        //inboundItemRepository.getInboundMaxSeq("1").;



    }
}