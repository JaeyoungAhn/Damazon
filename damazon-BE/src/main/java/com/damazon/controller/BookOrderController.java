package com.damazon.controller;

import com.damazon.dto.request.BookOrderRequest;
import com.damazon.dto.response.BookOrderResponse;
import com.damazon.service.BookOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookorders")
public class BookOrderController {

    private final BookOrderService bookOrderService;

    public BookOrderController(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @GetMapping
    public List<BookOrderResponse> getAllBookOrders() {
        return bookOrderService.getAllBookOrders();
    }

    @GetMapping("/{id}")
    public BookOrderResponse getBookOrder(@PathVariable Long id) {
        return bookOrderService.getBookOrder(id);
    }

    @PostMapping
    public BookOrderResponse createBookOrder(@Valid @RequestBody BookOrderRequest bookOrderRequest) {
        return bookOrderService.createBookOrder(bookOrderRequest);
    }
}
