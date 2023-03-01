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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item" +
        "")
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping
    public ResponseDto<ItemDto.CreateResponse> createItem (@RequestBody ItemDto.CreateRequest request){

        Item item = itemService.createItem(request);
        System.out.println(" =============== ");
        BizDto.Response bizDto = new BizDto.Response(item.getBiz());
        System.out.println(" =============== ");
        CustomerDto.Response customerDto = new CustomerDto.Response(item.getCustomer());
        System.out.println(" =============== ");
        ItemDto.CreateResponse response = new ItemDto.CreateResponse(item, bizDto, customerDto);
        System.out.println(" =============== ");
        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
