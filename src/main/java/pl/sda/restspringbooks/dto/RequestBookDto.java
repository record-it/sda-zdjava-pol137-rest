package pl.sda.restspringbooks.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class RequestBookDto {
    @Length(max = 50, min = 2, message = "Tytuł musi mieć od 2 do 50 znaków!")
    private String title;

    @Range(min = 2000, message = "Rok wydania nie może być mniejszy od 2000!")
    private int editionYear;

    private List<Long> authors;

    public List<Long> getAuthors(){
        return authors == null ? Collections.emptyList() : authors;
    }
}
