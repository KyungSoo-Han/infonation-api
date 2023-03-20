package kr.infonation.common.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    public void sendToItem(String message){
        System.out.println("message = " + message);
        this.rabbitTemplate.convertAndSend("CREATE_ITEM_QUEUE", message);
    }
}
