package pl.sda.restspringbooks.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class Book {
    private long id;

    @Length(max = 50, min = 2, message = "Tytuł musi mieć od 2 do 50 znaków!")
    private String title;

    @Range(min = 2000, message = "Rok wydania nie może być mniejszy od 2000!")
    private int editionYear;
}
