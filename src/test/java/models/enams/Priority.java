package models.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority implements HasValue {

    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String value;
}
