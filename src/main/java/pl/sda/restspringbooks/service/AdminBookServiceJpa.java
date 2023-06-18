package pl.sda.restspringbooks.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.sda.restspringbooks.model.Author;
import pl.sda.restspringbooks.model.Book;
import pl.sda.restspringbooks.repository.AuthorRepository;
import pl.sda.restspringbooks.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminBookServiceJpa implements AdminBookService{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AdminBookServiceJpa(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findBookPage(int page, int size) {
        return null;
    }

    @Override
    public List<Author> findAllAuthors() {
        return null;
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteBookById(long id) {

    }

    @Override
    public Optional<Book> createBook(Book book) {
        return Optional.empty();
    }

    @Override
    public void updateBook(Book book) {

    }
}
