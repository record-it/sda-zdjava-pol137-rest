package pl.sda.restspringbooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.restspringbooks.model.Author;
import pl.sda.restspringbooks.model.Book;
import pl.sda.restspringbooks.repository.AuthorRepository;
import pl.sda.restspringbooks.repository.BookRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class IntegrationRestSpringBooksAppTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    private Author author1, author2;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        author1 = authorRepository
                .save(
                        Author
                                .builder()
                                .birthDate(LocalDate.of(2000, 10, 10))
                                .firstName("Adam")
                                .lastName("Paduch")
                                .build()
                );
        author2 = authorRepository
                .save(
                        Author
                                .builder()
                                .birthDate(LocalDate.of(2000, 11, 11))
                                .firstName("Ewa")
                                .lastName("Kowal")
                                .build()
                );
        book = bookRepository.save(Book
                .builder()
                .editionYear(2000)
                .title("Java")
                .authors(List.of(author1, author2))
                .build()
        );
    }

    @Test
    public void shouldReturnTwoBooksForPublicGet() throws Exception {
        mvc
                .perform(
                        get("/api/v1/books")
                                .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Java")));
    }

}
