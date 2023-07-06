package com.sakila.orderservice.service;

import com.sakila.orderservice.dto.OrderLineItemsDto;
import com.sakila.orderservice.dto.OrderRequest;
import com.sakila.orderservice.dto.OrderResponse;
import com.sakila.orderservice.model.Order;
import com.sakila.orderservice.model.OrderLineItems;
import com.sakila.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return mapToDtoList(orders);
    }

    private List<OrderResponse> mapToDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    private OrderResponse mapOrderToDto(Order order) {
        List<OrderLineItemsDto> orderLineItemsDtoList = order.getOrderLineItemsList()
                .stream()
                .map(this::mapOrderLineItemsToDto)
                .collect(Collectors.toList());

        return new OrderResponse(orderLineItemsDtoList);
    }

    private OrderLineItemsDto mapOrderLineItemsToDto(OrderLineItems orderLineItems) {
        OrderLineItemsDto orderLineItemsDto = new OrderLineItemsDto();
        orderLineItemsDto.setId(orderLineItems.getId());
        orderLineItemsDto.setSkuCode(orderLineItems.getSkuCode());
        orderLineItemsDto.setPrice(orderLineItems.getPrice());
        orderLineItemsDto.setQuantity(orderLineItems.getQuantity());
        return orderLineItemsDto;
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
