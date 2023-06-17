package pl.sda.restspringbooks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Length(max = 50, min = 2, message = "Tytuł musi mieć od 2 do 50 znaków!")
    private String title;

    @Range(min = 2000, message = "Rok wydania nie może być mniejszy od 2000!")
    private int editionYear;

    private String authors;

    public List<String> getAuthors(){
        return Arrays.stream(authors.split("\\|")).toList();
    }

    public void setAuthors(List<String> authors){
        this.authors = authors.stream().collect(Collectors.joining("|"));
    }
}
