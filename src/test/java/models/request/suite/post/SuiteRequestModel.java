package models.request.suite.post;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuiteRequestModel {
    private String title;
    private String preconditions;
    private String description;
}
