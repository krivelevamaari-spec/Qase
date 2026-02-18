package models.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AutomationStatus implements HasValue {

    AUTOMATED("Automated"),
    MANUAL("Manual");

    private final String value;
}
