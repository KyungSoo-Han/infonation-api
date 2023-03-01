package kr.infonation.controller.cust;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Destination;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.DestinationDto;
import kr.infonation.service.cust.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/destination")
public class DestinationApiController {

    private final DestinationService destinationService;

    @PostMapping
    public ResponseDto<DestinationDto.CreateResponse> createDestination
                    (@RequestBody DestinationDto.CreateRequest request) throws CustomException {

        Map<String, Object> objectMap = destinationService.createDestination(request);
        Biz biz  = (Biz) objectMap.get("biz");
        Customer customer  = (Customer) objectMap.get("customer");
        Destination destination  = (Destination) objectMap.get("destination");

        BizDto.Response bizDto = new BizDto.Response(biz);
        CustomerDto.Response customerDto = new CustomerDto.Response(customer);
        DestinationDto.CreateResponse response = new DestinationDto.CreateResponse(destination, bizDto, customerDto);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}