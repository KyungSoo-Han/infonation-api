package kr.infonation.controller.item;

import kr.infonation.dto.item.ItemStockDto;
import kr.infonation.service.item.ItemStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class ItemStockApiController {
    private final ItemStockService stockService;

    public List<ItemStockDto.Response> findItemStock (@RequestBody ItemStockDto.Request request){

        return stockService.findItemStock(request);
    }
}
