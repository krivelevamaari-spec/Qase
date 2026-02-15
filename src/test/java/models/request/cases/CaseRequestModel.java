package models.request.cases;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CaseRequestModel {
    private String description;
    private String preconditions;
    private String postconditions;
    private String title;
    private Integer severity;
    private Integer priority;
    private Integer behavior;
    private Integer type;
    private Integer layer;
    private Integer is_flaky;
    private Long suite_id;
    private Long milestone_id;
    private Integer automation;
    private Integer status;
}
