package pl.sda.restspringbooks.error;

import java.util.List;

public class UnknownAuthorException extends RuntimeException{
        private final List<Long> unknownIds;

    public UnknownAuthorException(List<Long> unknownIds) {
        this.unknownIds = unknownIds;
    }

    @Override
    public String getMessage() {
        return "Lista id nieznanych autorów: " + unknownIds.toString();
    }
}
