package tests.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.request.suite.post.SuiteRequestModel;
import models.responce.suite.delete.SuiteDeleteResponseModel;
import models.responce.suite.get.SuiteGetSuitesResponseModel;

import static io.restassured.RestAssured.given;
import static tests.api.specs.QASESpec.REQ_SPEC;
import static tests.api.specs.QASESpec.responseWithStatusCode;

public class SuiteSteps {

    static String path = "/project/";

    @Step("Заполнить поля сьюты рандомными данными")
    public static SuiteGetSuitesResponseModel fillFieldsToCreateSuite(String projectCode, SuiteRequestModel SuiteRequest) {
        return given()
                .spec(REQ_SPEC)
                .body(SuiteRequest)
                .post(path + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(200))
                .extract().as(SuiteGetSuitesResponseModel.class);
    }

    @Step("Отправка GET-запроса на получение списка сьют")
    public static ValidatableResponse getSuites(String projectCode) {
        return given()
                .spec(REQ_SPEC)
                .get(path + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(200));
    }

    @Step("Получить ID сьюты")
    public static Integer getIdSuite(String projectCode) {
        return given()
                .spec(REQ_SPEC)
                .get(path + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(200))
                .extract()
                .jsonPath()
                .getInt("result.entities[0].id");
    }

    @Step("Удалить сьюту")
    public static SuiteDeleteResponseModel deleteSuite(String projectCode, Integer suiteId) {
        return given()
                .spec(REQ_SPEC)
                .delete(path + projectCode.toUpperCase() + suiteId)
                .then()
                .spec(responseWithStatusCode(200))
                .extract().as(SuiteDeleteResponseModel.class);
    }
}