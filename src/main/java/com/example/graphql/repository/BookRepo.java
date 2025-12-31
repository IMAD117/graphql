package com.example.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.Entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
