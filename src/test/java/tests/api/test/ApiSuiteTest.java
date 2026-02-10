package tests.api.test;

import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import models.CreateProjectFactory;
import models.CreateSuiteFactory;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import models.responce.project.get.ProjectGetResponseModel;
import models.responce.suite.delete.Result;
import models.responce.suite.delete.SuiteDeleteResponseModel;

import models.responce.suite.get.SuiteGetSuitesResponseModel;
import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import tests.api.steps.ProjectSteps;
import tests.api.steps.SuiteSteps;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.steps.ProjectSteps.getProjects;
import static tests.api.steps.SuiteSteps.getSuites;

@Owner("mkarpovich")
@Feature("Suite")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ApiSuiteTest extends BaseTest {

    @Test
    @DisplayName("Создать сьюту")
    @Story("Список Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    void suiteMustBeCreatedWithApi() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteRequest = CreateSuiteFactory.getRandomData();
        SuiteGetSuitesResponseModel suiteResponse = SuiteSteps.fillFieldsToCreateSuite(projectCode, suiteRequest);

        assertThat(suiteResponse)
                .isNotNull()
                .extracting(SuiteGetSuitesResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Удалить сьюту")
    @Story("Список Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    void suiteMustBeDeletedWithApi() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteRequest = CreateSuiteFactory.getRandomData();
        SuiteGetSuitesResponseModel suiteResponse = SuiteSteps.fillFieldsToCreateSuite(projectCode, suiteRequest);

        Integer suiteId = SuiteSteps.getIdSuite(projectCode);
        SuiteDeleteResponseModel deleteSuiteResponse = SuiteSteps.deleteSuite(projectCode, suiteId);

        assertThat(deleteSuiteResponse)
                .isNotNull()
                .extracting(SuiteDeleteResponseModel::getResult)
                .extracting(Result::getId)
                .isEqualTo(suiteId);
    }

    @Test
    @DisplayName("Получение списка всех тест-сьютов проекта")
    @Story("Список Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    void getAllSuitesByProjectCode() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteGetSuitesResponseModel suiteResponse = getSuites(projectCode)
                .extract().as(SuiteGetSuitesResponseModel.class);

        AbstractObjectAssert<?, Integer> count = assertThat(suiteResponse)
                .extracting(SuiteGetSuitesResponseModel::getResult)
                .extracting(models.responce.suite.get.Result::getCount);
    }
}
