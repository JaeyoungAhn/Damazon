package com.damazon.mapper;

import com.damazon.dto.request.BookRequest;
import com.damazon.dto.response.BookResponse;
import com.damazon.exception.NotFoundException;
import com.damazon.model.Book;
import com.damazon.repository.BookRepository;
import com.damazon.repository.CategoryRepository;
import com.damazon.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    private CustomerRepository customerRepository;
    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;

    public BookConverter(CustomerRepository customerRepository, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public BookResponse bookToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .price(book.getPrice())
                .author(book.getAuthor())
                .category(book.getCategory().getName())
                .updatedAt(book.getUpdatedAt())
                .deletedAt(book.getDeletedAt())
                .build();
    }

    public Book bookRequestToBook(BookRequest bookRequest) {
        return Book.builder()
                .author(bookRequest.author())
                .category(categoryRepository.findById(bookRequest.categoryId())
                        .orElseThrow(() -> new NotFoundException("category not found with id: " + bookRequest.categoryId())))
                .title(bookRequest.title())
                .price(bookRequest.price())
                .build();
    }
}
