package models.responce.project.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorWhileCreateProjectWithInvalidData {

    private boolean status;
    private String errorMessage;
}
