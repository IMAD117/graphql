package com.example.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.graphql.BookPage;
import com.example.graphql.Entity.Book;
import com.example.graphql.service.LibraryService;

@Controller
public class MyController {
	@Autowired
	private LibraryService service;
	
//	========================= QUERIES ==============================

	// == == List of books == ==
	@QueryMapping
	public BookPage listBooks(@Argument Integer page, @Argument Integer size, @Argument Integer year,
			@Argument String language, @Argument Integer categoryId) {
		int p = (page != null) ? page : 0;
		int s = (size != null) ? size : 10;
		Page<Book> result = service.listBooks(p, s, year, language, categoryId);
		return new BookPage(result.getContent(), result.getTotalPages(), (int) result.getTotalElements());
	}

	// == == Books of a selected author == ==
	@QueryMapping
	public List<Book> booksByAuthor(@Argument Integer authorId) {
		return service.booksByAuthor(authorId);
	}
	
	// == == Search for books == ==
    @QueryMapping
    public BookPage search(
            @Argument String keyword,
            @Argument String type,
            @Argument Integer page,
            @Argument Integer size) {

        int p = (page != null) ? page : 0;
        int s = (size != null) ? size : 10;

        Page<Book> result = service.searchBooks(keyword, type, p, s);

        return new BookPage(
                result.getContent(),
                result.getTotalPages(),
                (int) result.getTotalElements());
    }
	
//	========================= MUTATIONS ==============================
	
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
