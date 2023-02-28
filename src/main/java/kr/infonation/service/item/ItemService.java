package kr.infonation.service.item;

import kr.infonation.domain.biz.Biz;
import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.item.ItemDto;
import kr.infonation.repository.biz.BizRepository;
import kr.infonation.repository.cust.CustomerRepository;
import kr.infonation.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final BizRepository bizRepository;
    private final CustomerRepository customerRepository;



    @Transactional
    public Item createItem (ItemDto.CreateRequest request){

        Biz biz = bizRepository.getReferenceById(request.getBizId());
        Customer customer = customerRepository.getReferenceById(request.getCustomerId());

        return itemRepository.save(request.toEntity(biz, customer));
    }
}
