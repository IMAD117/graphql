package com.example.graphql.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.graphql.Entity.Author;
import com.example.graphql.Entity.Book;
import com.example.graphql.Entity.Category;
import com.example.graphql.repository.AuthorRepo;
import com.example.graphql.repository.BookRepo;
import com.example.graphql.repository.CategRepo;

@Service
public class LibraryService {

	private BookRepo bookRepo;
	private AuthorRepo authorRepo;
	private CategRepo categoryRepo;

	public LibraryService(BookRepo bookRepo, AuthorRepo authorRepo, CategRepo categoryRepo) {
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
		this.categoryRepo = categoryRepo;
	}
	
//	List of books
    public Page<Book> listBooks(int page , int size,Integer year,String language,Integer categoryId) {
    	PageRequest pageRequest = PageRequest.of(page, size);
    	
        if (year != null) {
            return bookRepo.findByPublicationYear(year, pageRequest);
        }

        if (language != null) {
            return bookRepo.findByLanguage(language, pageRequest);
        }

        if (categoryId != null) {
            return bookRepo.findByCategory_IdC(categoryId, pageRequest);
        }

        return bookRepo.findAll(pageRequest);
    }
    
//  Books of a selected author
	public List<Book> booksByAuthor(int authorId) {
		return bookRepo.findAll().stream().filter(b -> b.getAuthor().getIdA() == authorId).toList();
	}

//  add book
	public Book addBook(String title, int year, String language, int pages, int authorId, int categoryId) {

		Author author = authorRepo.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found"));

		Book book = new Book();
		book.setTitle(title);
		book.setPublicationYear(year);
		book.setLanguage(language);
		book.setNbPages(pages);
		book.setAuthor(author);
		book.setCategory(category);
		return bookRepo.save(book);
	}

//  delete author
	public void deleteAuthor(int authorId) {
		authorRepo.deleteById(authorId);
	}
}
