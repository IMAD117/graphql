package com.example.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.Entity.Category;

public interface CategRepo extends JpaRepository<Category, Integer> {
}
