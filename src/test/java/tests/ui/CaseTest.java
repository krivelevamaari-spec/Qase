package tests.ui;

import io.qameta.allure.*;
import models.CreateProjectFactory;
import models.enams.Behavior;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import pages.pageElements.DropDown;
import tests.BaseTest;
import api.specs.steps.ProjectSteps;

import static io.qameta.allure.Allure.step;

@Owner("mkarpovich")
@Feature("Case")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class CaseTest extends BaseTest {

    @BeforeEach
    void deleteAllProjectsIfNeed() {
        step("Удалить все существующие проекты",
                ()-> projectPage.deleteAllProjects());
    }

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации",
                ()-> loginPage.openPage("/login"));
    }

    @Test
    @DisplayName("Проверка создания тест кейса")
    @Story("Создание тест кейса")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Case")
    })
    public void caseMustBeCreated() {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        casePage.openCaseCreation()
                .checkPageTitle("Create test case");

        DropDown.selectRandom("behavior", Behavior.values());
        DropDown.selectRandom("flaky", models.enams.Flaky.values());
    }
}
