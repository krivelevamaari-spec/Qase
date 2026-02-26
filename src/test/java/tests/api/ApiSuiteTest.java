package tests.api;

import api.steps.ProjectSteps;
import api.steps.SuiteSteps;
import factory.CreateProjectFactory;
import factory.CreateSuiteFactory;
import io.qameta.allure.*;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import models.responce.suite.delete.SuiteDeleteResponseModel;
import models.responce.suite.get.SuiteGetSuitesResponseModel;
import models.responce.suite.post.SuiteCreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static api.steps.SuiteSteps.deleteSuite;
import static api.steps.SuiteSteps.getSuites;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("mkarpovich")
@Feature("Suite")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ApiSuiteTest extends BaseTest {

    @Test
    @Story("Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    @DisplayName("Создать сьюту")
    void suiteMustBeCreatedWithApi() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteRequest = CreateSuiteFactory.getRandomData();
        var suiteResponse = SuiteSteps.createSuite(projectCode, suiteRequest, 200);
        SuiteCreateResponseModel getSuiteResponse = suiteResponse.extract().as(SuiteCreateResponseModel.class);

        assertThat(getSuiteResponse)
                .isNotNull()
                .extracting(SuiteCreateResponseModel::isStatus)
                .isEqualTo(true);
    }


    @Test
    @Story("Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    @DisplayName("Удалить сьюту")
    void suiteMustBeDeletedWithApi() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteRequest = CreateSuiteFactory.getRandomData();
        var suiteResponse = SuiteSteps.createSuite(projectCode, suiteRequest, 200);

        SuiteCreateResponseModel createResponse = suiteResponse.extract().as(SuiteCreateResponseModel.class);
        Integer suiteId = createResponse.getResult().getId();

        SuiteDeleteResponseModel deleteResponse = deleteSuite(projectCode, 200, suiteId);

        assertThat(deleteResponse)
                .isNotNull()
                .extracting(SuiteDeleteResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @Story("Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    @DisplayName("Получить список всех сьют проекта")
    void getAllSuitesByProjectCode() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteGetSuitesResponseModel suiteResponse = getSuites(projectCode, 200)
                .extract().as(SuiteGetSuitesResponseModel.class);

        assertThat(suiteResponse)
                .isNotNull()
                .extracting(SuiteGetSuitesResponseModel::isStatus)
                .isEqualTo(true);

        assertThat(suiteResponse)
                .extracting(SuiteGetSuitesResponseModel::getResult)
                .isNotNull();
    }
}
