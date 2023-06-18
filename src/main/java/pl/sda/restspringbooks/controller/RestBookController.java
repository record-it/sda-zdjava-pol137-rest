package pl.sda.restspringbooks.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.restspringbooks.model.Book;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@RestController
@RequestMapping("/api/v1/books")
public class RestBookController {
    private List<Book> books = new ArrayList<>(
            List.of(
                    Book
                            .builder()
                            .id(1)
                            .authors("Bloch|Freeman")
                            .title("Java")
                            .editionYear(2020)
                            .build(),
                    Book
                            .builder()
                            .id(2)
                            .title("Spring")
                            .authors("Pivotal")
                            .editionYear(2021)
                            .build()
            )
    );

    @GetMapping("")
    public List<Book> getBooks() {
        return books;
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
        final OptionalLong max = books.stream().mapToLong(b -> b.getId()).max();
        book.setId(max.getAsLong() + 1);
        books.add(book);
        return ResponseEntity
                .created(URI.create("/api/v1/books/"+book.getId()))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @RequestParam long id,
            @Valid @RequestBody Book book
    ){
        if (id != book.getId()){
            return ResponseEntity.badRequest().build();
        }
        final Optional<Book> optionalBook = books
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        if (optionalBook.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        books.set(books.indexOf(optionalBook.get()), book);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        final Optional<Book> optionalBook = books
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        return ResponseEntity.of(optionalBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id){
        final Optional<Book> optionalBook = books
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        if (optionalBook.isPresent()){
            books.remove(optionalBook.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/authors")
    public ResponseEntity<List<String>> getBookAuthors(@RequestParam long id){
        final Optional<Book> optionalBook = books
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        if (optionalBook.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalBook.get().getAuthors());
    }


}
