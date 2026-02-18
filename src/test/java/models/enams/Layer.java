package models.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Layer implements HasValue {

    API("API"),
    UNIT("Unit"),
    E2E("E2E");

    private final String value;
}
