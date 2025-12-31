package com.example.graphql.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBook;

	private String title;
	private int publicationYear;
	private String language;
	private int nbPages;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Author author;
}
