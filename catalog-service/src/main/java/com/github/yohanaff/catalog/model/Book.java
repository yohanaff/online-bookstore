package com.github.yohanaff.catalog.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private Integer publicationYear;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    private Category category;

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Book(String title, String author, String isbn, BigDecimal price, Integer publicationYear, Category category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.publicationYear = publicationYear;
        this.category = category;
    }

}
