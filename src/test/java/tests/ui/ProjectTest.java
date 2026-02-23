package tests.ui;

import api.steps.ProjectSteps;
import factory.CreateProjectFactory;
import io.qameta.allure.*;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

import static io.qameta.allure.Allure.step;

@Owner("mkarpovich")
@Feature("Project")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ProjectTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации",
                () -> loginPage.openPage("/login"));
    }

    @Test
    @DisplayName("Проверка создания нового проекта с валидными данными")
    @Story("Создание нового проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    public void projectMustBeCreated() {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        projectPage.clickCreateProjectButton();

        ProjectRequestModel createProject = CreateProjectFactory.getRandomData();
        String expectedProjectCode = createProject.getCode();

        projectPage.createProject(createProject)
                .clickSaveProjectButton()
                .checkThatTheProjectHasBeenCreated(expectedProjectCode);
    }

    @Test
    @DisplayName("Проверка валидации полей при создании проекта с некорректными данными")
    @Story("Обработка ошибок при создании проекта")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    public void projectMustBeNotCreated() {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        projectPage.clickCreateProjectButton();

        ProjectRequestModel notCreateProject = CreateProjectFactory.getWrongRandomData();

        projectPage.createProject(notCreateProject)
                .clickSaveProjectButton()
                .checkThatTheProjectHasBeenNotCreated("The code format is invalid.");
    }

    @Test
    @DisplayName("Проверка удаления проекта")
    @Story("Удаление проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    public void projectMustBeDeleted() {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        String projectTitle = projectData.getTitle();

        ProjectSteps.createProject(projectData, 200);

        projectPage.openProjectPage()
                .deleteCreatedProject()
                .checkThatProjectIsDeleted(projectTitle);
    }
}
