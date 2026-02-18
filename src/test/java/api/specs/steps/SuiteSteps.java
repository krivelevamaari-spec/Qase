package api.specs.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.request.suite.post.SuiteRequestModel;
import models.responce.suite.delete.SuiteDeleteResponseModel;

import static io.restassured.RestAssured.given;
import static api.specs.QASESpec.REQ_SPEC;
import static api.specs.QASESpec.responseWithStatusCode;

public class SuiteSteps {

    static String path = "/suite/";

    @Step("Заполнить поля сьюты рандомными данными")
    public static ValidatableResponse createSuite(String projectCode, SuiteRequestModel suiteRequest,
                                                  Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .body(suiteRequest)
                .post(path + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(statusCode));
    }

    @Step("Отправка GET-запроса на получение списка сьют")
    public static ValidatableResponse getSuites(String projectCode, Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .get(path + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(statusCode));
    }

    @Step("Удалить сьюту")
    public static SuiteDeleteResponseModel deleteSuite(String projectCode, Integer statusCode, Integer suiteId) {
        return given()
                .spec(REQ_SPEC)
                .delete(path + projectCode.toUpperCase() + "/" + suiteId)
                .then()
                .spec(responseWithStatusCode(statusCode))
                .extract().as(SuiteDeleteResponseModel.class);
    }
}
