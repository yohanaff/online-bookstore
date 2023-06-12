package com.github.yohanaff.catalog.service;

import com.github.yohanaff.catalog.dto.BookDTO;
import com.github.yohanaff.catalog.model.Book;
import com.github.yohanaff.catalog.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final InventoryCache inventoryCache;

    public BookService(BookRepository bookRepository, InventoryCache inventoryCache) {
        this.bookRepository = bookRepository;
        this.inventoryCache = inventoryCache;
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    public Book saveBook(BookDTO bookDto) {
        Book book = toBook(bookDto);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private Book toBook(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setPublicationYear(dto.getPublicationYear());
        book.setCategory(dto.getCategory());
        return book;
    }
}

