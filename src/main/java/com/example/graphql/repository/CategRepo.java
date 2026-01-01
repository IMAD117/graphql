package com.example.graphql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.Entity.Category;

public interface CategRepo extends JpaRepository<Category, Integer> {
	Page<Category> findByCategoryNameContainingIgnoreCase(String keyword,Pageable pageRequest);
}
