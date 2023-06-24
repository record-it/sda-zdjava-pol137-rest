package pl.sda.restspringbooks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.restspringbooks.dto.RequestBookDto;
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

    RequestBookDto requestBookDtoEmptyAuthors = RequestBookDto
            .builder()
            .title("NEW")
            .editionYear(2000)
            .authors(Collections.emptyList())
            .build();
    @BeforeEach
    public void setup(){
        // for shouldReturnListWithTwoBooks()
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(testBook1, testBook2));
        Mockito.when(authorRepository.findAll()).thenReturn(List.of(testAuthor1, testAuthor2));
        // for shouldReturnTestBook1ForIdOne()
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook1));
        Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.of(testBook2));
        // for shouldCreateBookForEmptyAuthorsList()
        Mockito.when(authorRepository.findAllById(Collections.emptyList())).thenReturn(Collections.emptyList());
        Mockito.when(bookRepository.save(
                Book
                        .builder()
                        .authors(Collections.emptyList())
                        .title(requestBookDtoEmptyAuthors.getTitle())
                        .editionYear(requestBookDtoEmptyAuthors.getEditionYear())
                        .build()
        )).thenReturn(
                Book
                        .builder()
                        .id(3)
                        .authors(Collections.emptyList())
                        .title(requestBookDtoEmptyAuthors.getTitle())
                        .editionYear(requestBookDtoEmptyAuthors.getEditionYear())
                        .build()
        );
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
        final long  id = 1;
        final Optional<Book> optionalBook = bookService.findBookById(id);
        assertThat(optionalBook.isPresent()).isEqualTo(true);
        assertThat(optionalBook.get().getId()).isEqualTo(id);
    }

    @Test
    void shouldCreateBookForEmptyAuthorsList(){
        final Book book = bookService.createBook(requestBookDtoEmptyAuthors);
        assertThat(book.getId()).isEqualTo(3);
    }
}