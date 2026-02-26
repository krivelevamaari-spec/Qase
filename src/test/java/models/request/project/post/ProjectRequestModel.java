package models.request.project.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequestModel {

    private String title;
    private String code;
    private String description;
}
