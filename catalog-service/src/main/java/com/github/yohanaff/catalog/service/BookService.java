package com.github.yohanaff.catalog.service;

import com.github.yohanaff.catalog.model.Book;
import com.github.yohanaff.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findBooksByTitle(String title) {

        return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {

        return bookRepository.findByAuthor(author);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}