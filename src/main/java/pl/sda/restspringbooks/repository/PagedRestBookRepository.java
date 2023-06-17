package pl.sda.restspringbooks.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.sda.restspringbooks.model.Book;

@RepositoryRestResource(path = "books", collectionResourceRel = "books")
public interface PagedRestBookRepository extends PagingAndSortingRepository<Book, Long> {
    // localhost:9000/books/search/authors?year=2000&sort=title
    @RestResource(path = "edition")
    Page<Book> findBooksByEditionYear(@Param("year") int year, Pageable p);
}
