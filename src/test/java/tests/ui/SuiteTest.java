package tests.ui;

import io.qameta.allure.*;
import factory.CreateProjectFactory;
import factory.CreateSuiteFactory;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;
import api.steps.ProjectSteps;
import api.steps.SuiteSteps;

import static io.qameta.allure.Allure.step;

@Owner("mkarpovich")
@Feature("Suite")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class SuiteTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации",
                () -> loginPage.openPage("/login"));
    }

    @Test
    @DisplayName("Проверка создания сьюты с валидными данными")
    @Story("Создание сьюты")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    public void suiteMustBeCreatedWithValidData() {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

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
    @DisplayName("Проверка удаления сьюты")
    @Story("Удаление сьюты")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    public void suiteMustBeDeleted() {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

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
