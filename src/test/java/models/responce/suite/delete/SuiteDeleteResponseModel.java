package models.responce.suite.delete;

import lombok.Data;
import models.responce.suite.post.Result;

@Data
public class SuiteDeleteResponseModel {

    private boolean status;
    private Result result;
}
