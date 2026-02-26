package tests.ui;

import api.steps.ProjectSteps;
import api.steps.SuiteSteps;
import factory.CreateProjectFactory;
import factory.CreateSuiteFactory;
import io.qameta.allure.*;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

@Owner("mkarpovich")
@Feature("Suite")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class SuiteTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        login(email, password);
    }

    @Test
    @Story("Suite")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    @DisplayName("Проверка создания сьюты")
    public void suiteMustBeCreatedWithValidData() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteData = CreateSuiteFactory.getRandomData();

        suitePage.openSuitePage(projectCode.toUpperCase())
                .clickButtonCreateNewSuite()
                .fillFieldsToCreateSuite(suiteData)
                .clickCreateButton()
                .checkTheSuiteIsCreated();
    }

    @Test
    @Story("Suite")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    @DisplayName("Проверка удаления сьюты")
    public void suiteMustBeDeleted() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteRequest = CreateSuiteFactory.getRandomData();
        var suiteResponse = SuiteSteps.createSuite(projectCode, suiteRequest, 200);

        suitePage.openSuitePage(projectCode.toUpperCase())
                .deleteSuite()
                .checkTheSuiteIsDeleted();
    }
}
