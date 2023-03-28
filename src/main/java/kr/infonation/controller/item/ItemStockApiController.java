package kr.infonation.controller.item;

import kr.infonation.dto.item.ItemStockDto;
import kr.infonation.service.item.ItemStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class ItemStockApiController {
    private final ItemStockService stockService;

    @PostMapping
    public List<ItemStockDto.Response> findItemStock (@RequestBody ItemStockDto.Request request){

        return stockService.findItemStock(request);
    }
}
