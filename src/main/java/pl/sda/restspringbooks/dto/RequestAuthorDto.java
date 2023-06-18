package pl.sda.restspringbooks.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class RequestAuthorDto {
    @NotEmpty
    @Length(max = 20)
    private String firstName;

    @NotEmpty
    @Length(max = 50)
    private String lastName;

    @NotEmpty
    private LocalDate birthDate;
}
