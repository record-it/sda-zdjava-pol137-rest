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

    }
}
