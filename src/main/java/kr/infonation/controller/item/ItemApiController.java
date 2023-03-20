package kr.infonation.controller.item;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

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

    @PostMapping(value="/excelUpload")
    public ResponseDto<String> uploadExcelFile(  @RequestPart(name = "excelUploadRequest") ItemDto.ExcelUploadRequest excelUploadRequest,
                                                 @RequestPart(name = "excelFile") MultipartFile excelFile,
                                                 HttpServletRequest request) throws IOException {
        System.out.println("request = " + excelUploadRequest);
        System.out.println("excelFile = " + excelFile);

        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        multipartResolver.isMultipart(request);

        itemService.excelUpload(excelUploadRequest, excelFile);

        return ResponseDto.SuccessResponse("OK", HttpStatus.OK);

    }

    @PostMapping("/rabbitmq")
    public ResponseDto<String> uploadExcelFileByRabbitMq(  @RequestPart(name = "excelUploadRequest") ItemDto.ExcelUploadRequest excelUploadRequest,
                                                          @RequestPart(name = "excelFile") MultipartFile excelFile,
                                                          HttpServletRequest request) throws IOException {
        System.out.println("request = " + excelUploadRequest);
        System.out.println("excelFile = " + excelFile);

        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        multipartResolver.isMultipart(request);

        itemService.excelUploadByRabbitMq(excelUploadRequest, excelFile);

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
