package com.github.yohanaff.catalog.service;

import com.github.yohanaff.catalog.dto.BookDTO;
import com.github.yohanaff.catalog.model.Book;
import com.github.yohanaff.catalog.model.Category;
import com.github.yohanaff.catalog.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        book1 = new Book(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "9783161484100",
                BigDecimal.valueOf(9.99),
                1925,
                Category.FICTION
        );

        book2 = new Book(
                "To Kill a Mockingbird",
                "Harper Lee",
                "9783161484100",
                BigDecimal.valueOf(9.99),
                1960,
                Category.FICTION
        );
    }

    @Test
    void shouldFindBookByTitle() {
        when(bookRepository.findByTitle("The Great Gatsby")).thenReturn(Arrays.asList(book1, book2));

        List<BookDTO> books = bookService.findBooksByTitle("The Great Gatsby");

        assertEquals(2, books.size());
        assertEquals("The Great Gatsby", books.get(0).getTitle());
        assertEquals("To Kill a Mockingbird", books.get(1).getTitle());
    }

    @Test
    void shouldFindByAuthor() {
        when(bookRepository.findByAuthor("F. Scott Fitzgerald")).thenReturn(Arrays.asList(book1));

        List<Book> books = bookService.findBooksByAuthor("F. Scott Fitzgerald");

        assertEquals(1, books.size());
        assertEquals("The Great Gatsby", books.get(0).getTitle());
    }

    @Test
    void shouldFindAllBooks() {
        Pageable pageable = PageRequest.of(0, 10);
        when(bookRepository.findAll(pageable)).thenReturn(new PageImpl<>(Arrays.asList(book1, book2)));

        List<Book> books = bookService.findAllBooks(pageable);

        assertEquals(2, books.size());
        assertEquals("The Great Gatsby", books.get(0).getTitle());
        assertEquals("To Kill a Mockingbird", books.get(1).getTitle());
    }

    @Test
    void shouldFindBookById() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(book1));

        Book book = bookService.findBookById(1L).get();

        assertEquals("The Great Gatsby", book.getTitle());
    }

    @Test
    void shouldSaveBook() {
        when(bookRepository.save(book1)).thenReturn(book1);

        Book book = bookService.saveBook(book1);

        assertEquals("The Great Gatsby", book.getTitle());
    }

    @Test
    void shouldDeleteBook() {
        bookService.deleteBook(1L);
    }
}
