package models.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Behavior implements HasValue {

    POSITIVE("Positive"),
    NEGATIVE("Negative"),
    DESTRUCTIVE("Destructive");

    private final String value;
}
