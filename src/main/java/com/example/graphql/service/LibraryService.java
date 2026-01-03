package com.example.graphql.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
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

// Search for books
    public Page<?> search(String keyword, String type, int page, int size) {
        List<Object> combined = new ArrayList<>();
        String key = keyword.toLowerCase();


        if (type == null || type.equalsIgnoreCase("all") || type.equalsIgnoreCase("book")) {
            combined.addAll(bookRepo.findAll().stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(key))
                    .toList());
        }

        if (type == null || type.equalsIgnoreCase("all") || type.equalsIgnoreCase("author")) {
            combined.addAll(authorRepo.findAll().stream()
                    .filter(a -> a.getName().toLowerCase().contains(key))
                    .toList());
        }

        if (type == null || type.equalsIgnoreCase("all") || type.equalsIgnoreCase("category")) {
            combined.addAll(categoryRepo.findAll().stream()
                    .filter(c -> c.getCategoryName().toLowerCase().contains(key))
                    .toList());
        }

        int start = page * size;
        int end = Math.min((start + size), combined.size());

        List<Object> subList = new ArrayList<>();
        if (start < combined.size()) {
            subList = combined.subList(start, end);
        }
        return new PageImpl<>(subList, PageRequest.of(page, size), combined.size());
    }
    
    
//  Books of a selected author
	public List<Book> booksByAuthor(int authorId) {
		return bookRepo.findAll().stream().filter(b -> b.getAuthor().getIdA() == authorId).toList();
	}

//  add book
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
	public void deleteAuthor(int authorId) {
		authorRepo.deleteById(authorId);
	}
}
