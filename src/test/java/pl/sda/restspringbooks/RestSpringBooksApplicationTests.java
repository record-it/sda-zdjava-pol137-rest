package pl.sda.restspringbooks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.restspringbooks.service.AdminBookServiceJpa;

@SpringBootTest(
        classes = AdminBookServiceJpa.class
)

class RestSpringBooksApplicationTests {

    @Test
    void shouldReturnEmptyListForEmptyBookRepository() {
    }

}
