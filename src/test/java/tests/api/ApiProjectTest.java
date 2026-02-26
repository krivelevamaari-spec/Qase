package tests.api;

import api.steps.ProjectSteps;
import factory.CreateProjectFactory;
import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import models.request.project.post.ProjectRequestModel;
import models.responce.project.delete.ProjectDeleteResponseModel;
import models.responce.project.get.ProjectGetResponseModel;
import models.responce.project.post.ErrorWhileCreateProjectWithInvalidData;
import models.responce.project.post.ProjectCreateResponseModel;
import models.responce.project.post.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static api.steps.ProjectSteps.deleteProject;
import static api.steps.ProjectSteps.getProjects;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("mkarpovich")
@Feature("Project")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ApiProjectTest extends BaseTest {

    @Test
    @Story("Project")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test"),
            @Tag("Project")
    })
    @DisplayName("Создать проект")
    void projectMustBeCreatedWithApi() {
        ProjectRequestModel data = CreateProjectFactory.getRandomData();
        ProjectCreateResponseModel response = ProjectSteps.createProject(data, 200)
                .extract()
                .as(ProjectCreateResponseModel.class);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectCreateResponseModel::getResult)
                .extracting(Result::getCode)
                .isEqualTo(data.getCode().toUpperCase());
    }

    @Test
    @Story("Project")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test"),
            @Tag("Project")
    })
    @DisplayName("Удалить проект")
    void projectMustBeDeletedWithApi() {
        ProjectRequestModel data = CreateProjectFactory.getRandomData();
        ValidatableResponse response = ProjectSteps.createProject(data, 200);

        ProjectCreateResponseModel projectCreateResponseModel = response.extract().as(ProjectCreateResponseModel.class);
        ProjectDeleteResponseModel deleteResponse = deleteProject(projectCreateResponseModel.getResult().getCode(), 200)
                .extract()
                .as(ProjectDeleteResponseModel.class);

        assertThat(deleteResponse)
                .isNotNull()
                .extracting(ProjectDeleteResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @Story("Project")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Project")
    })
    @DisplayName("Проверка создания проекта с невалидным телом запроса")
    void projectMustBeNotCreatedWithApi() {
        ProjectRequestModel invalidData = CreateProjectFactory.getWrongRandomData();
        ErrorWhileCreateProjectWithInvalidData errorResponse = ProjectSteps.createProject(invalidData, 400)
                .extract()
                .as(ErrorWhileCreateProjectWithInvalidData.class);

        assertThat(errorResponse)
                .isNotNull()
                .extracting(ErrorWhileCreateProjectWithInvalidData::getErrorMessage)
                .isEqualTo(errorResponse.getErrorMessage());
    }

    @Test
    @Story("Project")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Project")
    })
    @DisplayName("Проверка удаления проекта с невалидным или пустым идентификатором")
    void projectMustBeNotDeletedWithInvalidOrEmptyProjectCode() {
        ErrorWhileCreateProjectWithInvalidData deleteResponse = deleteProject("LOL", 404)
                .extract()
                .as(ErrorWhileCreateProjectWithInvalidData.class);

        assertThat(deleteResponse)
                .isNotNull()
                .extracting(ErrorWhileCreateProjectWithInvalidData::getErrorMessage)
                .isEqualTo("Project not found");
    }

    @Test
    @Story("Project")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Project")
    })
    @DisplayName("Получить список всех проектов")
    void getAllProjectsAndVerifyStatus() {
        ProjectGetResponseModel response = getProjects(200)
                .extract().as(ProjectGetResponseModel.class);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectGetResponseModel::isStatus)
                .isEqualTo(true);
    }
}
