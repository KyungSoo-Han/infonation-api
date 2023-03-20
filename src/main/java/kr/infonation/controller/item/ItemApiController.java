package kr.infonation.controller.item;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.item.ItemDto;
import kr.infonation.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item")
public class ItemApiController {
    private final ItemService itemService;

    @GetMapping({"/{id}"})
    public ResponseDto<ItemDto.Response> findItem(@PathVariable Long id) {
        ItemDto.Response itemList = itemService.findItem( id);

        return ResponseDto.SuccessResponse(itemList, HttpStatus.OK);
    }

    @GetMapping({"/list/{bizId}/{customerId}"})
    public ResponseDto<List<ItemDto.Response>> findItemList(@PathVariable Long bizId, @PathVariable Long customerId,
                                                        @RequestParam(required = false) String itemName ){
        List<ItemDto.Response> itemList = itemService.findItemList(bizId, customerId, itemName);

        return ResponseDto.SuccessResponse(itemList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseDto<ItemDto.CreateResponse> createItem (@RequestBody ItemDto.CreateRequest request){

        ItemDto.CreateResponse response = itemService.createItem(request);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

    @PostMapping("/excelUpload")
    public ResponseDto<String> uploadExcelFile(@RequestParam Long bizId, @RequestParam Long customerId,
                                                @RequestParam Long supplierId, @RequestParam MultipartFile excelFile) throws IOException {

            itemService.excelUpload(bizId, customerId, supplierId, excelFile);

            return ResponseDto.SuccessResponse("OK", HttpStatus.OK);

    }

    @PutMapping
    public ResponseDto<ItemDto.UpdateResponse> updateItem (@RequestBody ItemDto.UpdateRequest request){

        ItemDto.UpdateResponse updateResponse = itemService.updateItem(request);

        return ResponseDto.SuccessResponse(updateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<ItemDto.DeleteResponse> deleteItem(@PathVariable Long id){

        ItemDto.DeleteResponse deleteResponse = itemService.deleteItem(id);

        return ResponseDto.SuccessResponse(deleteResponse, HttpStatus.OK);
    }


}
