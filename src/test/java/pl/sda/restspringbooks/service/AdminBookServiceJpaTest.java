package pl.sda.restspringbooks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.restspringbooks.model.Author;
import pl.sda.restspringbooks.model.Book;
import pl.sda.restspringbooks.repository.AuthorRepository;
import pl.sda.restspringbooks.repository.BookRepository;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AdminBookServiceJpa.class
)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AdminBookServiceJpaTest {

    AdminBookService bookService;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    BookRepository bookRepository;
    Author testAuthor1 = Author
            .builder()
            .id(1)
            .lastName("TEST1")
            .firstName("TEST1")
            .birthDate(LocalDate.of(2000,10,10))
            .build();
    Author testAuthor2 = Author
            .builder()
            .id(1)
            .lastName("TEST2")
            .firstName("TEST2")
            .birthDate(LocalDate.of(1990,10,10))
            .build();
    Book testBook1 = Book
            .builder()
            .id(1)
            .authors(List.of(testAuthor1))
            .title("TEST1")
            .editionYear(2000)
            .build();
    Book testBook2 = Book
            .builder()
            .id(2)
            .authors(List.of(testAuthor1, testAuthor2))
            .title("TEST2")
            .editionYear(2010)
            .build();

    @BeforeEach
    public void setup(){
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(testBook1, testBook2));
        Mockito.when(authorRepository.findAll()).thenReturn(List.of(testAuthor1, testAuthor2));
        bookService = new AdminBookServiceJpa(bookRepository, authorRepository);
    }

    @Test
    void shouldReturnListWithTwoBooks() {
        final List<Book> allBooks = bookService.findAllBooks();
        assertThat(allBooks.size()).isEqualTo(2);
        assertThat(allBooks.get(0).getTitle()).isEqualTo(testBook1.getTitle());
    }

    @Test
    void shouldReturnTestBook1ForIdOne(){
        final Optional<Book> optionalBook = bookService.findBookById(1L);
        // wpisz assercje sprawdzającą , czy w optionalBook jest obiekt

    }
}