package com.example.graphql.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.graphql.Entity.Book;
import com.example.graphql.service.LibraryService;

@Controller
public class MyController {

	private LibraryService service;

	// == == Books of a selected author == ==
	@QueryMapping
	public List<Book> booksByAuthor(@Argument Integer authorId) {
		return service.booksByAuthor(authorId);
	}

    // == == add book == ==
	@MutationMapping
	public Book addBook(@Argument String title, @Argument int publicationYear, @Argument String language,
			@Argument int nbPages, @Argument int authorId, @Argument int categoryId) {

		return service.addBook(title, publicationYear, language, nbPages, authorId, categoryId);
	}

	// == == delete author == ==
	@MutationMapping
	public Boolean deleteAuthor(@Argument int authorId) {
		service.deleteAuthor(authorId);
		return true;
	}
}
