package kitchenpos.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.order.domain.Order;

public class OrderResponse {
    private final Long id;
    private final Long orderTableId;
    private final String orderStatus;
    private final LocalDateTime orderedTime;
    private final List<OrderLineItemResponse> orderLineItems;

    public OrderResponse(Long id,
                         Long orderTableId,
                         String orderStatus,
                         LocalDateTime orderedTime,
                         List<OrderLineItemResponse> orderLineItems) {
        this.id = id;
        this.orderTableId = orderTableId;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
        this.orderLineItems = orderLineItems;
    }

    public static OrderResponse from(Order order) {
        List<OrderLineItemResponse> orderLineItems = order.getOrderLineItems().getOrderLineItems()
                .stream()
                .map(OrderLineItemResponse::from)
                .collect(Collectors.toList());

        return new OrderResponse(order.getId(),
                order.getOrderTableId(),
                order.getOrderStatus().name(),
                order.getOrderedTime(),
                orderLineItems);
    }

    public static List<OrderResponse> ofList(List<Order> orders) {
        return  orders.stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public List<OrderLineItemResponse> getOrderLineItems() {
        return orderLineItems;
    }
}
