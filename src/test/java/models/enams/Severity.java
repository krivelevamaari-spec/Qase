package models.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Severity implements HasValue {

    MAJOR("Major"),
    MINOR("Minor"),
    NORMAL("Normal"),
    CRITICAL("Critical"),
    BLOCKER("Blocker"),
    TRIVIAL("Trivial");

    private final String value;
}
