package tests.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.request.project.post.ProjectRequestModel;
import models.responce.project.get.EntitiesItem;
import models.responce.project.get.Entity;

import java.util.List;

import static io.restassured.RestAssured.given;
import static tests.api.specs.QASESpec.REQ_SPEC;
import static tests.api.specs.QASESpec.responseWithStatusCode;

public class ProjectSteps {

    static String path = "/project";

    @Step("Создать проект с рандомными данными")
    public static ValidatableResponse createProject(ProjectRequestModel projectRequest, Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .body(projectRequest)
                .post(path)
                .then()
                .spec(responseWithStatusCode(statusCode));
    }

    @Step("Удалить проект с кодом {0}")
    public static ValidatableResponse deleteProject(String projectCode, Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .delete(path + "/" + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(200));

    }

    @Step("Получить проект из списка")
    public static List<EntitiesItem> getProjectFromList() {
        final String JSON_PATH = "result.entities";
        return given()
                .spec(REQ_SPEC)
                .get(path)
                .then()
                .spec(responseWithStatusCode(200))
                .extract()
                .jsonPath()
                .getList(JSON_PATH, EntitiesItem.class);
    }

    @Step("Получить список проектов")
    public static  List<Entity> getListProjects() {
        return given()
                .spec(REQ_SPEC)
                .get(path)
                .then()
                .spec(responseWithStatusCode(200))
                .extract()
                .jsonPath()
                .getList("data", Entity.class);
    }

    @Step("Отправка GET-запроса на получение списка проектов")
    public static ValidatableResponse getProjects() {
        return given()
                .spec(REQ_SPEC)
                .get(path)
                .then()
                .spec(responseWithStatusCode(200));
    }
}
