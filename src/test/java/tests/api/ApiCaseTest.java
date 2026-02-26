package tests.api;

import api.steps.CaseSteps;
import api.steps.ProjectSteps;
import factory.CreateCaseFactory;
import factory.CreateProjectFactory;
import io.qameta.allure.*;
import models.request.cases.post.CaseRequestModel;
import models.request.project.post.ProjectRequestModel;
import models.responce.cases.delete.CaseDeleteResponseModel;
import models.responce.cases.get.CaseGetCasesResponseModel;
import models.responce.cases.post.CaseCreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static api.steps.CaseSteps.deleteCase;
import static api.steps.CaseSteps.getCases;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("mkarpovich")
@Feature("Case")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ApiCaseTest extends BaseTest {

    @Test
    @Story("Case")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Case")
    })
    @DisplayName("Создать тест-кейс")
    void caseMustBeCreatedWithApi() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        CaseRequestModel caseRequest = CreateCaseFactory.getRandomApiData();
        var caseResponse = CaseSteps.createCase(projectCode, caseRequest, 200);
        CaseCreateResponseModel getCaseResponse = caseResponse.extract().as(CaseCreateResponseModel.class);

        assertThat(getCaseResponse)
                .isNotNull()
                .extracting(CaseCreateResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @Story("Case")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Case")
    })
    @DisplayName("Удалить тест-кейс")
    void caseMustBeDeletedWithApi() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        CaseRequestModel caseRequest = CreateCaseFactory.getRandomApiData();
        var caseResponse = CaseSteps.createCase(projectCode, caseRequest, 200);
        CaseCreateResponseModel getCaseResponse = caseResponse.extract().as(CaseCreateResponseModel.class);
        Integer caseId = getCaseResponse.getResult().getId();

        CaseDeleteResponseModel deleteResponse = deleteCase(projectCode, 200, caseId);

        assertThat(deleteResponse)
                .isNotNull()
                .extracting(CaseDeleteResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @Story("Case")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Case")
    })
    @DisplayName("Получить список всех тест-кейсов проекта")
    void getAllCasesByProjectCode() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        CaseGetCasesResponseModel caseResponse = getCases(projectCode, 200)
                .extract().as(CaseGetCasesResponseModel.class);

        assertThat(caseResponse)
                .isNotNull()
                .extracting(CaseGetCasesResponseModel::isStatus)
                .isEqualTo(true);

        assertThat(caseResponse)
                .extracting(CaseGetCasesResponseModel::getResult)
                .isNotNull();
    }
}
