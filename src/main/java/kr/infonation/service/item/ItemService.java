package kr.infonation.service.item;

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
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
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

    public void excelUpload(MultipartFile excelFile) throws IOException {

        Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // 첫번째 시트 사용

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            var test= row.getCell(0);
            System.out.println("test = " + test);
            String itemNm =  getCellValue(row,0);
            System.out.println("itemNm = " + itemNm);
            if(row.getCell(1)!=null){
                String itemSNm =  getCellValue(row,1);
                System.out.println("itemSNm = " + itemSNm);
            }
            if(row.getCell(2)!=null){
                String status =  getCellValue(row,2);
                System.out.println("status = " + status);
            }
            //System.out.println("cellValue2 = " + cellValue2);
            //System.out.println("cellValue3 = " + cellValue3);
            //System.out.println("cellValue4 = " + cellValue4);
            //System.out.println("cellValue5 = " + cellValue5);
        }

        workbook.close();
    }

    private static String getCellValue(Row row, int cellIdx) {
        //return row.cellIterator().next().getStringCellValue();
        return row.getCell(cellIdx).getStringCellValue();
    }

}
