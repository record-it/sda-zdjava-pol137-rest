package pl.sda.restspringbooks.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.restspringbooks.configuration.AppConfiguration;
import pl.sda.restspringbooks.dto.PagingQuery;
import pl.sda.restspringbooks.dto.RequestAuthorDto;
import pl.sda.restspringbooks.dto.RequestBookDto;
import pl.sda.restspringbooks.model.Author;
import pl.sda.restspringbooks.model.Book;
import pl.sda.restspringbooks.service.AdminBookService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/books")
public class AdminRestBookController {
    private final AdminBookService bookService;

    private final AppConfiguration appConfiguration;

    public AdminRestBookController(AdminBookService bookService, AppConfiguration appConfiguration) {
        this.bookService = bookService;
        this.appConfiguration = appConfiguration;
    }

    @GetMapping("")
    public Page<Book> allBooks(@RequestParam(required = false) PagingQuery pageQuery){
        if (pageQuery == null){
            pageQuery = PagingQuery
                    .builder()
                    .page(appConfiguration.defaultPageNumber)
                    .size(appConfiguration.defaultPageSize)
                    .build();
        }
        return bookService.findBookPage(pageQuery.getPage(), pageQuery.getSize());
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@Valid @RequestBody RequestBookDto dto){
        final Book book =  bookService.createBook(dto);
        return ResponseEntity
                .created(URI.create("/api/v1/admin/books/" + book.getId()))
                .build();
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody RequestAuthorDto dto){
        final Author author = bookService.createAuthor(dto);
        return ResponseEntity
                .created(URI.create("/api/v1/admin/books/authors/"+author.getId()))
                .build();
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return bookService.findAllAuthors();
    }
}
