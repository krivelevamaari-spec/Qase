package tests.ui;

import api.steps.CaseSteps;
import api.steps.ProjectSteps;
import factory.CreateCaseFactory;
import factory.CreateProjectFactory;
import factory.model.CaseFactoryModel;
import io.qameta.allure.*;
import models.request.cases.post.CaseRequestModel;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

@Owner("mkarpovich")
@Feature("Case")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class CaseTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        login(email, password);
    }

    @Test
    @Story("Case")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Case")
    })
    @DisplayName("Проверка создания тест-кейса")
    public void caseMustBeCreated() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        CaseFactoryModel data = CreateCaseFactory.getRandomUiData();

        projectPage.openProjectByCode(projectCode);

        casePage.openCaseCreation()
                .checkPageTitle("Create test case")
                .createNewCase(data)
                .clickNewStepButton()
                .writeNewStep("Открыть страницу авторизации")
                .clickSaveButton()
                .checkThatCaseIsCreated();
    }

    @Test
    @Story("Case")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("UI-test"),
            @Tag("Case")
    })
    @DisplayName("Проверка удаления тест кейса")
    public void caseMustBeDeleted() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        CaseRequestModel caseRequest = CreateCaseFactory.getRandomApiData();
        var caseResponse = CaseSteps.createCase(projectCode, caseRequest, 200);

        projectPage.openProjectByCode(projectCode);

        casePage.clickCheckBox()
                .clickDeleteButton();
    }
}
