package com.damazon.service;

import com.damazon.dto.request.BookOrderRequest;
import com.damazon.dto.response.BookOrderResponse;
import com.damazon.mapper.BookOrderConverter;
import com.damazon.model.BookOrder;
import com.damazon.model.OrderItem;
import com.damazon.repository.BookOrderRepository;
import com.damazon.repository.OrderItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookOrderService {

    private final BookOrderRepository bookOrderRepository;
    private final BookOrderConverter bookOrderConverter;
    private final OrderItemRepository orderItemRepository;

    public BookOrderService(BookOrderRepository bookOrderRepository, BookOrderConverter bookOrderConverter, OrderItemRepository orderItemRepository) {
        this.bookOrderRepository = bookOrderRepository;
        this.bookOrderConverter = bookOrderConverter;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public List<BookOrderResponse> getAllBookOrders() {
        List<BookOrder> retrievedBookOrders = bookOrderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return retrievedBookOrders.stream()
                .map(retrievedBookOrder -> {
                    List<OrderItem> retrievedOrderItems = orderItemRepository.findByBookOrderId(retrievedBookOrder.getId());
                    return bookOrderConverter.bookOrderAndOrderItemsToBookOrderResponse(retrievedBookOrder, retrievedOrderItems);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public BookOrderResponse getBookOrder(Long id) {
        List<OrderItem> retrievedOrderItems = orderItemRepository.findByBookOrderId(id);
        BookOrder retrievedBookOrder = retrievedOrderItems.get(0).getBookOrder();
        return bookOrderConverter.bookOrderAndOrderItemsToBookOrderResponse(retrievedBookOrder, retrievedOrderItems);
    }

    @Transactional
    public BookOrderResponse createBookOrder(BookOrderRequest bookOrderRequest) {
        BookOrder bookOrder = bookOrderConverter.bookOrderRequestToBookOrder(bookOrderRequest);
        BookOrder retrievedBookOrder = bookOrderRepository.save(bookOrder);

        List<OrderItem> orderItems = bookOrderConverter.bookOrderRequestToOrderItemList(bookOrderRequest.items(), retrievedBookOrder);
        List<OrderItem> retrievedOrderItems = orderItemRepository.saveAll(orderItems);

        return bookOrderConverter.bookOrderAndOrderItemsToBookOrderResponse(retrievedBookOrder, retrievedOrderItems);
    }
}
