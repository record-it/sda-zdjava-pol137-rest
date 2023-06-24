package pl.sda.restspringbooks.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sda.restspringbooks.dto.RequestAuthorDto;
import pl.sda.restspringbooks.dto.RequestBookDto;
import pl.sda.restspringbooks.error.UnknownAuthorException;
import pl.sda.restspringbooks.model.Author;
import pl.sda.restspringbooks.model.Book;
import pl.sda.restspringbooks.repository.AuthorRepository;
import pl.sda.restspringbooks.repository.BookRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("title"));
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBookById(long id) {
        throw new NotImplementedException();
    }

    @Override
    public Book createBook(RequestBookDto dto) {
        final List<Author> authors = authorRepository
                .findAllById(dto.getAuthors());
        // wyznacz różnicę między listami, aby przekazać listę id tylko autorów spoza bazy
        final Set<Long> collect = authors.stream().map(a -> a.getId()).collect(Collectors.toSet());
        final List<Long> result = dto.getAuthors().stream().filter(id -> !collect.contains(id)).toList();
        if (dto.getAuthors().size() != authors.size()){
            throw new UnknownAuthorException(result);
        }
        return bookRepository.save(
                Book
                        .builder()
                        .editionYear(dto.getEditionYear())
                        .title(dto.getTitle())
                        .authors(authors)
                        .build()
        );

    }

    @Override
    public Author createAuthor(RequestAuthorDto dto) {
        return authorRepository.save(
                Author
                        .builder()
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .birthDate(dto.getBirthDate())
                        .build()
        );
    }

    @Override
    public void updateBook(Book book) {
        throw new NotImplementedException();
    }
}
