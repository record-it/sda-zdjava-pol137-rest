package pl.sda.restspringbooks.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagingQuery {

    private int page;

    private int size;

    private String sort;
}
