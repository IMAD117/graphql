package com.example.graphql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.Entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
	Page<Author> findByNameContainingIgnoreCase(String keyword,Pageable pageRequest);
}