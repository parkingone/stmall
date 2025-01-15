package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderCanclled extends AbstractEvent {

    private Long id;

    public OrderCanclled(Order aggregate) {
        super(aggregate);
    }

    public OrderCanclled() {
        super();
    }
}
//>>> DDD / Domain Event
