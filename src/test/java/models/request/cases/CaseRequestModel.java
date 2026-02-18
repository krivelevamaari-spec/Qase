package models.request.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CaseRequestModel {
    private String title;
    private Integer severity;
    private Integer priority;
    private Integer behavior;
    private Integer type;
    private Integer layer;
    @JsonProperty("is_flaky")
    private Integer isFlaky;
    @JsonProperty("milestone_id")
    private Long milestoneId;
    private Integer automation;
    private Integer status;
}
