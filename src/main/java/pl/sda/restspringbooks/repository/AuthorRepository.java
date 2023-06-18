package pl.sda.restspringbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.restspringbooks.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
