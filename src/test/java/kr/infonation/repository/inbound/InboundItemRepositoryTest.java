package kr.infonation.repository.inbound;

import kr.infonation.repository.base.SlipNoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

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