package com.github.yohanaff.catalog.controller;

import com.github.yohanaff.catalog.dto.BookDTO;
import com.github.yohanaff.catalog.model.Book;
import com.github.yohanaff.catalog.service.BookService;
import com.github.yohanaff.catalog.service.InventoryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private InventoryCache inventoryCache;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        List<Book> books = bookService.findAllBooks(pageable);
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> {
                    BookDTO bookDto = new BookDTO();
                    bookDto.setId(book.getId());
                    bookDto.setTitle(book.getTitle());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setIsbn(book.getIsbn());
                    bookDto.setPrice(book.getPrice());
                    bookDto.setPublicationYear(book.getPublicationYear());
                    bookDto.setCategory(book.getCategory());
                    bookDto.setQuantity(inventoryCache.get(book.getId()));
                    return bookDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else {
            BookDTO bookDto = new BookDTO();
            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setPrice(book.getPrice());
            bookDto.setPublicationYear(book.getPublicationYear());
            bookDto.setCategory(book.getCategory());
            bookDto.setQuantity(inventoryCache.get(book.getId()));
            return ResponseEntity.ok(bookDto);
        }
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDto) {
        Book book = bookService.saveBook(bookDto);
        BookDTO savedBookDto = new BookDTO();
        savedBookDto.setId(book.getId());
        savedBookDto.setTitle(book.getTitle());
        savedBookDto.setAuthor(book.getAuthor());
        savedBookDto.setIsbn(book.getIsbn());
        savedBookDto.setPrice(book.getPrice());
        savedBookDto.setPublicationYear(book.getPublicationYear());
        savedBookDto.setCategory(book.getCategory());
        savedBookDto.setQuantity(inventoryCache.get(book.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDto) {
        Book book = bookService.saveBook(bookDto);
        BookDTO updatedBookDto = new BookDTO();
        updatedBookDto.setId(book.getId());
        updatedBookDto.setTitle(book.getTitle());
        updatedBookDto.setAuthor(book.getAuthor());
        updatedBookDto.setIsbn(book.getIsbn());
        updatedBookDto.setPrice(book.getPrice());
        updatedBookDto.setPublicationYear(book.getPublicationYear());
        updatedBookDto.setCategory(book.getCategory());
        updatedBookDto.setQuantity(inventoryCache.get(book.getId()));
        return ResponseEntity.ok(updatedBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}