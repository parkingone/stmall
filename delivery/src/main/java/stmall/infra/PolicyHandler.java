package stmall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_StartShipping(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener StartShipping : " + orderPlaced + "\n\n"
        );

        // Comments //
        //1. 택배사 전문전송
        // 2. 고객에게 배송알림
        // 3. 우리 장부에도 keeping

        // Sample Logic //
        Delivery.startShipping(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCanclled'"
    )
    public void wheneverOrderCanclled_CalcelDelivery(
        @Payload OrderCanclled orderCanclled
    ) {
        OrderCanclled event = orderCanclled;
        System.out.println(
            "\n\n##### listener CalcelDelivery : " + orderCanclled + "\n\n"
        );

        // Sample Logic //
        Delivery.calcelDelivery(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
