package api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.request.cases.CaseRequestModel;
import models.request.suite.post.SuiteRequestModel;
import models.responce.cases.delete.CaseDeleteResponseModel;
import models.responce.suite.delete.SuiteDeleteResponseModel;

import static api.specs.QASESpec.REQ_SPEC;
import static api.specs.QASESpec.responseWithStatusCode;
import static io.restassured.RestAssured.given;

public class CaseSteps {

    static String path = "/case/";

    @Step("Заполнить поля тест кейса рандомными данными")
    public static ValidatableResponse createCase(String projectCode, CaseRequestModel caseRequest,
                                                  Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .body(caseRequest)
                .post(path + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(statusCode));
    }

    @Step("Удалить тест кейс")
    public static CaseDeleteResponseModel deleteCase(String projectCode, Integer statusCode, Integer caseId) {
        return given()
                .spec(REQ_SPEC)
                .delete(path + projectCode.toUpperCase() + "/" + caseId)
                .then()
                .spec(responseWithStatusCode(statusCode))
                .extract().as(CaseDeleteResponseModel.class);
    }

    @Step("Отправка GET-запроса на получение списка тест кейсов")
    public static ValidatableResponse getCases(String projectCode, Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .get(path + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(statusCode));
    }
}
