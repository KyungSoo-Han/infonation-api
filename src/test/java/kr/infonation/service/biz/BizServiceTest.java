package kr.infonation.service.biz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.infonation.controller.biz.BizApiController;
import kr.infonation.domain.biz.Biz;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.repository.biz.BizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class BizServiceTest {

    @InjectMocks
    BizApiController bizApiController;
    //private BizRepository bizRepository ;
    @Mock private BizService bizService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp(){
        //this.bizService = new BizService(bizRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(bizApiController).build();
    }

    @Test
    void createBiz() throws Exception {
        BizDto.CreateRequest req = new BizDto.CreateRequest();
        req.setBizItem("TEST");
        req.setName("한경수");
        //Biz biz = req.toEntity();
        //lenient().when(bizRepository.getReferenceById(anyLong())).thenReturn(biz);
        //Biz byId = bizRepository.getReferenceById(biz.getId());
        //System.out.println("byId = " + byId);
       mockMvc.perform(post("/api/biz")
                       .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsBytes(req))).andExpect(status().isOk());

       verify(bizService).createBiz(req);
    }
}