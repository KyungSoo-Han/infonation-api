package kr.infonation.controller.biz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.service.biz.BizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/biz")
public class BizApiController {
    private final BizService bizService;

    @GetMapping
    public ResponseDto<List<BizDto.Response>> findBiz(){
        List<Biz> bizList = bizService.findBiz();
        List<BizDto.Response> response = bizList
                                            .stream()
                                            .map(b -> new BizDto.Response(b))
                                            .collect(Collectors.toList());
        //Map<String, Object> data = new HashMap<>();
        //data.put("data", response);
        //data.put("size", response.size());
        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseDto<BizDto.CreateResponse> createBiz(@RequestBody BizDto.CreateRequest request) {
        Biz biz = bizService.createBiz(request);
        BizDto.CreateResponse response = new BizDto.CreateResponse(biz);
        System.out.println("==========================" );
        System.out.println("response = " + response);
        System.out.println("==========================" );
        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseDto<BizDto.UpdateResponse>updateBiz(@PathVariable Long id, @RequestBody BizDto.UpdateRequest request){
        Biz biz = bizService.updateBiz(id, request);
        BizDto.UpdateResponse response = new BizDto.UpdateResponse(biz);

        return ResponseDto.SuccessResponse(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<BizDto.DeleteResponse> deleteBiz (@PathVariable Long id) throws CustomException {
        bizService.deleteBiz(id);

        BizDto.DeleteResponse response = new BizDto.DeleteResponse(id);
        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
    /*
    @PostMapping
    public ResponseEntity<CreateBiz.Response> createBiz(@RequestBody CreateBiz.Request request) throws CustomException {

        CreateBiz.Response response = bizService.createBiz(request);
        System.out.println("==========================" );
        System.out.println("response = " + response);
        System.out.println("==========================" );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
}
