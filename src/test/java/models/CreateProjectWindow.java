package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectWindow {
    String projectName;
    String projectCode;
    String description;
}
