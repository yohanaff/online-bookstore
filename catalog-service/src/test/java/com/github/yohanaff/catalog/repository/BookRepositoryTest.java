package com.github.yohanaff.catalog.repository;

import com.github.yohanaff.catalog.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setIsbn("9783161484100");
        book.setPrice(BigDecimal.valueOf(9.99));
        book.setPublicationYear(1925);

        entityManager.persist(book);
        entityManager.flush();
    }

    @Test
    void shouldSaveBook() {
        Book savedBook = bookRepository.save(book);

        Assertions.assertThat(savedBook.getId()).isNotNull();
        Assertions.assertThat(savedBook.getTitle()).isEqualTo("The Great Gatsby");
        Assertions.assertThat(savedBook.getAuthor()).isEqualTo("F. Scott Fitzgerald");
        Assertions.assertThat(savedBook.getIsbn()).isEqualTo("9783161484100");
        Assertions.assertThat(savedBook.getPrice()).isEqualTo(BigDecimal.valueOf(9.99));
        Assertions.assertThat(savedBook.getPublicationYear()).isEqualTo(1925);

    }

    @Test
    void shouldFindBookById() {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());

        Assertions.assertThat(optionalBook).isPresent();
        Assertions.assertThat(optionalBook.get().getId()).isEqualTo(book.getId());
        Assertions.assertThat(optionalBook.get().getTitle()).isEqualTo(book.getTitle());
        Assertions.assertThat(optionalBook.get().getAuthor()).isEqualTo(book.getAuthor());
        Assertions.assertThat(optionalBook.get().getIsbn()).isEqualTo(book.getIsbn());
        Assertions.assertThat(optionalBook.get().getPrice()).isEqualTo(book.getPrice());
        Assertions.assertThat(optionalBook.get().getPublicationYear()).isEqualTo(book.getPublicationYear());
    }

    @Test
    void shouldFindByTitle() {

        List<Book> foundBooks = bookRepository.findByTitle("The Great Gatsby");

        Assertions.assertThat(foundBooks).isNotEmpty();
        Assertions.assertThat(foundBooks.get(0).getTitle()).isEqualTo("The Great Gatsby");
    }

    @Test
    void shouldFindByAuthor() {
        List<Book> foundBooks = bookRepository.findByAuthor("F. Scott Fitzgerald");

        Assertions.assertThat(foundBooks).isNotEmpty();
        Assertions.assertThat(foundBooks.get(0).getAuthor()).isEqualTo("F. Scott Fitzgerald");
    }
}