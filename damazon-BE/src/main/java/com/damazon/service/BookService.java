package com.damazon.service;

import com.damazon.dto.request.BookRequest;
import com.damazon.dto.response.BookResponse;
import com.damazon.exception.NotFoundException;
import com.damazon.mapper.BookConverter;
import com.damazon.model.Book;
import com.damazon.model.Category;
import com.damazon.repository.BookRepository;
import com.damazon.repository.CategoryRepository;
import com.damazon.repository.CustomBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final CategoryRepository categoryRepository;
    private final CustomBookRepository customBookRepository;

    public BookService(BookRepository bookRepository, BookConverter bookConverter, CategoryRepository categoryRepository, CustomBookRepository customBookRepository) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.categoryRepository = categoryRepository;
        this.customBookRepository = customBookRepository;
    }

    @Transactional
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAllByDeletedAtIsNull().stream()
                .map(bookConverter::bookToBookResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookResponse getBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));
        return bookConverter.bookToBookResponse(book);
    }

    @Transactional
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = bookConverter.bookRequestToBook(bookRequest);
        book = bookRepository.save(book);
        return bookConverter.bookToBookResponse(book);
    }

    @Transactional
    public BookResponse updateBook(Integer id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));
        book.setTitle(bookRequest.title());
        book.setPrice(bookRequest.price());
        book.setAuthor(bookRequest.author());

        Optional<Category> category = categoryRepository.findById(bookRequest.categoryId());
        book.setCategory(category.get());

        book = bookRepository.save(book);
        return bookConverter.bookToBookResponse(book);
    }

    @Transactional
    public BookResponse deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book not found with id: " + id);
        }
        Book book = customBookRepository.softDelete(id);
        return bookConverter.bookToBookResponse(book);
    }
}
