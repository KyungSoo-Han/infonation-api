package kr.infonation.service.item;

import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.biz.BizDto;
import kr.infonation.dto.cust.CustomerDto;
import kr.infonation.dto.item.ItemDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.item.ItemQueryRepository;
import kr.infonation.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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


    public List<ItemDto.Response> findItemList(Long bizId, Long customerId, String itemName){
       return itemQueryRepository.findItemList(bizId, customerId, itemName)
               .stream()
               .map(o-> new ItemDto.Response(o))
               .collect(Collectors.toList());
    }

    @Transactional
    public ItemDto.CreateResponse createItem (ItemDto.CreateRequest request){

        Biz biz = bizRepository.getReferenceById(request.getBizId());
        Customer customer = customerRepository.getReferenceById(request.getCustomerId());
        Item item = itemRepository.save(request.toEntity(biz, customer));

        BizDto.Response bizDto = new BizDto.Response(item.getBiz());
        CustomerDto.Response customerDto = new CustomerDto.Response(item.getCustomer());
        ItemDto.CreateResponse response = new ItemDto.CreateResponse(item, bizDto, customerDto);

        return response;
    }

    @Transactional
    public ItemDto.UpdateResponse updateItem (ItemDto.UpdateRequest request){

        Biz biz = bizRepository.findById(request.getBizId()).orElseThrow(() -> new EntityNotFoundException("잘못된 사업장 아이디입니다."));
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("잘못된 화주사 아이디입니다."));

        Item item = itemRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException("잘못된 품목 아이디입니다."));
        item.update(request,biz,customer);

        return new ItemDto.UpdateResponse(item, biz.getId(), customer.getId());
    }

    @Transactional
    public ItemDto.DeleteResponse deleteItem(Long id) {

        itemRepository.deleteById(id);

        return new ItemDto.DeleteResponse(id);
    }
}
