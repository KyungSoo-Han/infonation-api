package kr.infonation.service.item;

import kr.infonation.domain.item.ItemStock;
import kr.infonation.dto.item.ItemStockDto;
import kr.infonation.repository.item.ItemStockQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemStockService {

    private final ItemStockQueryRepository itemStockQueryRepository;

    public List<ItemStockDto.Response> findItemStock(ItemStockDto.Request request){

        return itemStockQueryRepository.findItemStock(request.getBizId(), request.getCenterId(), request.getCustomerId(),
                request.getItemId(), request.getMakeLotNo(), request.getMakeDate(),
                request.getExpDate(), request.getLocation());
    }

}
