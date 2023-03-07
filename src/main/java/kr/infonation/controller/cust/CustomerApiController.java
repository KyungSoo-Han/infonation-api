package kr.infonation.controller.cust;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.CustomException;
import kr.infonation.domain.cust.Customer;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.service.cust.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseDto<List<CustomerDto.Response>> findCustomer(@RequestParam(required = false) Long bizId ){
        List<CustomerDto.Response> res = customerService.findCustomer(bizId);

        return ResponseDto.SuccessResponse(res, HttpStatus.OK);
    }

    @Value("${file.dir}")
    private String fileDir;
    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam MultipartFile excelFile, HttpServletRequest request) {
        try {

            Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0); // 첫번째 시트 사용
            MultipartResolver multipartResolver = new StandardServletMultipartResolver();
            multipartResolver.isMultipart(request);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String cellValue1 = row.getCell(0).getStringCellValue();
                String cellValue2 = String.valueOf(row.getCell(1).getNumericCellValue());
                String cellValue3 = row.getCell(2).getStringCellValue();

                System.out.println("cellValue1 = " + cellValue1);
                System.out.println("cellValue2 = " + cellValue2);
                System.out.println("cellValue3 = " + cellValue3);
            }

            workbook.close();

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping
    public ResponseDto<CustomerDto.CreateResponse> createCustomer(@RequestBody CustomerDto.CreateRequest request) throws CustomException {
        System.out.println("request = " + request);
        Customer customer = customerService.createCustomer(request);

        CustomerDto.CreateResponse response = new CustomerDto.CreateResponse(customer);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

}
