package com.damazon.repository;

import com.damazon.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomBookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Book softDelete(Integer id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            book.markAsDeleted();
        }
        return book;
    }
}
