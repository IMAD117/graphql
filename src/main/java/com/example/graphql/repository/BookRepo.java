package com.example.graphql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.Entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
    Page<Book> findByLanguage(String language, Pageable pageable);

    Page<Book> findByPublicationYear(int year, Pageable pageable);

    Page<Book> findByCategory_IdC(Integer categoryId, Pageable pageable);
    
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
