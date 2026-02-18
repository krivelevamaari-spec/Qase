package models.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Flaky implements HasValue {

    YES("Yes"),
    NO("No");

    private final String value;
}
