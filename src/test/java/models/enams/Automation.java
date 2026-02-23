package models.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Automation {

    AUTOMATED("Automated"),
    MANUAL("Manual");

    private final String value;
}
