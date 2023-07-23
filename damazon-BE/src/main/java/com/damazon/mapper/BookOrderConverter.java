package com.damazon.mapper;

import com.damazon.dto.request.BookOrderRequest;
import com.damazon.dto.request.OrderItemRequest;
import com.damazon.dto.response.BookOrderResponse;
import com.damazon.dto.response.OrderItemResponse;
import com.damazon.model.Book;
import com.damazon.model.BookOrder;
import com.damazon.model.Customer;
import com.damazon.model.OrderItem;
import com.damazon.repository.BookRepository;
import com.damazon.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookOrderConverter {

    private CustomerRepository customerRepository;
    private BookRepository bookRepository;

    public BookOrderConverter(CustomerRepository customerRepository, BookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public BookOrder bookOrderRequestToBookOrder(BookOrderRequest bookOrderRequest) {
        BookOrder bookOrder = new BookOrder();

        Optional<Customer> customer = customerRepository.findById(bookOrderRequest.customerId());
        bookOrder.setCustomer(customer.get());
        bookOrder.setStatus(bookOrderRequest.status());
        return bookOrder;

    }

    public List<OrderItem> bookOrderRequestToOrderItemList(List<OrderItemRequest> bookOrderRequest, BookOrder bookOrder) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : bookOrderRequest) {
            Optional<Book> book = bookRepository.findById(orderItemRequest.bookId());

            OrderItem orderItem = new OrderItem();
            orderItem.setBookOrder(bookOrder);
            orderItem.setBook(book.get());
            orderItem.setQuantity(orderItemRequest.quantity());
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    public BookOrderResponse bookOrderAndOrderItemsToBookOrderResponse(BookOrder bookOrder, List<OrderItem> orderItems) {
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItemResponse orderItemResponse = new OrderItemResponse(orderItem.getBook().getTitle(), orderItem.getQuantity(), orderItem.getBook().getPrice());
            orderItemResponses.add(orderItemResponse);
        }
        BookOrderResponse bookOrderResponse = new BookOrderResponse(bookOrder.getCustomer().getName(), bookOrder.getStatus(), orderItemResponses, bookOrder.getCreatedAt());
        return bookOrderResponse;
    }
}
