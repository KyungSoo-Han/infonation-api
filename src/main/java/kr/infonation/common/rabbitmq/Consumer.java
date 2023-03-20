package kr.infonation.common.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.infonation.domain.item.Item;
import kr.infonation.dto.item.ItemDto;
import kr.infonation.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {
    private final ObjectMapper objectMapper;
    private final ItemRepository itemRepository;

    @RabbitListener(queues = "CREATE_ITEM_QUEUE")
    public void handler(String message) throws JsonProcessingException {
        Item item = objectMapper.readValue(message, Item.class);
        System.out.println("item = " + item);
        itemRepository.save(item);
    }


}
