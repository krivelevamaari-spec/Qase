package factory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CaseFactoryModel {
    private String title;
    private String severity;
    private String priority;
    private String behavior;
    private String type;
    private String layer;
    @JsonProperty("is_flaky")
    private String isFlaky;
    private String automation;
    private String status;


}
