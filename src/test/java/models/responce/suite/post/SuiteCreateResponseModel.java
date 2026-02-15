package models.responce.suite.post;

import lombok.Data;
import models.responce.project.post.Result;

@Data
public class SuiteCreateResponseModel {
    private boolean status;
    private Result result;
}
