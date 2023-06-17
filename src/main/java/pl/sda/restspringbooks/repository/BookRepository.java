package pl.sda.restspringbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.restspringbooks.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
