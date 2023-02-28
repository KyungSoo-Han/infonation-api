package kr.infonation.controller.item;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.item.ItemDto;
import kr.infonation.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping
    public ResponseDto<ItemDto.CreateResponse> createItem (@RequestBody ItemDto.CreateRequest request){

        Item item = itemService.createItem(request);
        BizDto.Response bizDto = new BizDto.Response(item.getBiz());
        CustomerDto.Response customerDto = new CustomerDto.Response(item.getCustomer());

        ItemDto.CreateResponse response = new ItemDto.CreateResponse(item, bizDto, customerDto);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
