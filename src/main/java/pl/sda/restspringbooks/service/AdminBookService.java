package pl.sda.restspringbooks.service;

import org.springframework.data.domain.Page;
import pl.sda.restspringbooks.dto.RequestAuthorDto;
import pl.sda.restspringbooks.dto.RequestBookDto;
import pl.sda.restspringbooks.model.Author;
import pl.sda.restspringbooks.model.Book;
import pl.sda.restspringbooks.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public interface AdminBookService {

    List<Book> findAllBooks();

    Page<Book> findBookPage(int page, int size);
    List<Author> findAllAuthors();

    Optional<Book> findBookById(long id);

    void deleteBookById(long id);

    Book createBook(RequestBookDto book);

    Author createAuthor(RequestAuthorDto dto);

    void updateBook(Book book);
}
