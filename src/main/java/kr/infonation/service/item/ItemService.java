package kr.infonation.service.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.infonation.common.rabbitmq.Producer;
import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.cust.Supplier;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.cust.SupplierDto;
import kr.infonation.dto.item.ItemDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.cust.SupplierRepository;
import kr.infonation.repository.item.ItemQueryRepository;
import kr.infonation.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemQueryRepository itemQueryRepository;
    private final BizRepository bizRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final Producer producer;
    private final ObjectMapper objectMapper;

    public List<ItemDto.Response> findItemList(Long bizId, Long customerId, String itemName){
       return itemQueryRepository.findItemList(bizId, customerId, itemName)
               .stream()
               .map(o-> new ItemDto.Response(o))
               .collect(Collectors.toList());
    }

    @Transactional
    public ItemDto.CreateResponse createItem (ItemDto.CreateRequest request){

        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(() -> new EntityNotFoundException("잘못된 사업장 아이디입니다."));
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("잘못된 공급사 아이디입니다."));

        Item item = itemRepository.save(request.toEntity(biz, customer,supplier));

        BizDto.Response bizDto = new BizDto.Response(item.getBiz());
        CustomerDto.Response customerDto = new CustomerDto.Response(item.getCustomer());
        SupplierDto.Response supplierDto = new SupplierDto.Response(item.getSupplier());
        ItemDto.CreateResponse response = new ItemDto.CreateResponse(item, bizDto, customerDto,supplierDto);

        return response;
    }

    @Transactional
    public ItemDto.UpdateResponse updateItem (ItemDto.UpdateRequest request){

        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(() -> new EntityNotFoundException("잘못된 사업장 아이디입니다."));
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));

        Item item = itemRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException("잘못된 품목 아이디입니다."));
        item.update(request,biz,customer,supplier);

        return new ItemDto.UpdateResponse(item, biz.getId(), customer.getId(), supplier.getId());
    }

    @Transactional
    public ItemDto.DeleteResponse deleteItem(Long id) {

        itemRepository.deleteById(id);

        return new ItemDto.DeleteResponse(id);
    }

    public ItemDto.Response findItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("잘못된 품목 아이디입니다."));

        return new ItemDto.Response(item);

    }
    @Transactional
    public void excelUpload(ItemDto.ExcelUploadRequest request, MultipartFile excelFile) throws IOException {

        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(() -> new EntityNotFoundException("잘못된 사업장 아이디입니다."));
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));


        Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // 첫번째 시트 사용
        List<Item> itemList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            String  itemNm =  getStrCellValue(row,0);
            String  itemSNm =  getStrCellValue(row,1);
            boolean status = getStrCellValue(row,2).equals("Y") ? true : false ;
            boolean isSet = getStrCellValue(row,3).equals("Y") ? true : false ;
            boolean isMakeDay = getStrCellValue(row,4).equals("Y") ? true : false ;
            Integer fromMakeDay = getNumCellValue(row,5) > 0 ?  getNumCellValue(row,5).intValue() : 0;
            String  description = getStrCellValue(row,6);

            itemList.add(new ItemDto.ExcelUpload(itemNm,itemSNm, status, isSet, isMakeDay, fromMakeDay,description)
                                 .toEntity(biz,customer,supplier));
        }

        itemRepository.saveAll(itemList);
        workbook.close();
    }

    private  String getStrCellValue(Row row, int cellIdx) {
        try {
            if (row.getCell(cellIdx) != null)
                return row.getCell(cellIdx).getStringCellValue();
            else
                return "";
        }
        catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            return "";
        }
    }

    private  Double getNumCellValue(Row row, int cellIdx) {
        try {
            if (row.getCell(cellIdx) != null)
                return row.getCell(cellIdx).getNumericCellValue();
            else
                return 0D;
        }

        catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            return 0D;
        }
    }
    @Transactional
    public void excelUploadByRabbitMq(ItemDto.ExcelUploadRequest request, MultipartFile excelFile) throws IOException {

        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(() -> new EntityNotFoundException("잘못된 사업장 아이디입니다."));
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));

        Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // 첫번째 시트 사용

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            String  itemNm =  getStrCellValue(row,0);
            String  itemSNm =  getStrCellValue(row,1);
            boolean status = getStrCellValue(row,2).equals("Y") ? true : false ;
            boolean isSet = getStrCellValue(row,3).equals("Y") ? true : false ;
            boolean isMakeDay = getStrCellValue(row,4).equals("Y") ? true : false ;
            Integer fromMakeDay = getNumCellValue(row,5) > 0 ?  getNumCellValue(row,5).intValue() : 0;
            String  description = getStrCellValue(row,6);

            Item item = new ItemDto.ExcelUpload(itemNm, itemSNm, status, isSet, isMakeDay, fromMakeDay, description).toEntity(biz,customer,supplier);
            String targetData = objectMapper.writeValueAsString(item);
            producer.sendToItem(targetData);
        }

        workbook.close();

    }
}
