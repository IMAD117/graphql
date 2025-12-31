package com.example.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.Entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
}