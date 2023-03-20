package kr.infonation.common.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    public void sendTo(String message){
        System.out.println("message = " + message);
        this.rabbitTemplate.convertAndSend("CREATE_POST_QUEUE", message);
    }
}
