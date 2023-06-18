package pl.sda.restspringbooks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.restspringbooks.model.Author;
import pl.sda.restspringbooks.model.Book;
import pl.sda.restspringbooks.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestSpringBooksApplication implements CommandLineRunner {
    private final BookRepository bookRepository;

    public RestSpringBooksApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(RestSpringBooksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() < 1) {
            bookRepository.saveAll(
                    List.of(
                            Book
                                    .builder()
                                    .title("AAA")
                                    .editionYear(2020)
                                    .authors(List.of(
                                            Author
                                                    .builder()
                                                    .birthDate(LocalDate.of(1995, 10, 10))
                                                    .firstName("Eva")
                                                    .lastName("Freeman")
                                                    .build()
                                    ))
                                    .build(),
                            Book
                                    .builder()
                                    .title("BBB")
                                    .editionYear(2000)
                                    .authors(List.of(
                                                    Author
                                                            .builder()
                                                            .birthDate(LocalDate.of(1990, 10, 10))
                                                            .firstName("Adam")
                                                            .lastName("Freeman")
                                                            .build(),
                                                    Author
                                                            .builder()
                                                            .birthDate(LocalDate.of(1986, 11, 11))
                                                            .firstName("Karol")
                                                            .lastName("May")
                                                            .build()
                                            )
                                    )
                                    .build()
                    )
            );
//            final Book book = bookRepository.findById(1L).orElseThrow(() -> new RuntimeException("Brak takiej książki"));
//            bookRepository.save(
//                    Book
//                            .builder()
//                            .title("CCC")
//                            .editionYear(2019)
//                            .authors(
//                                    book.getAuthors()
//                            )
//                            .build()
//            );
        }
    }
}
