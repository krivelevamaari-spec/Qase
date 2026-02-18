package tests.ui;

import io.qameta.allure.*;
import models.CreateProjectFactory;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;
import api.specs.steps.ProjectSteps;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Owner("mkarpovich")
@Feature("Project")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ProjectTest extends BaseTest {

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

        projectFactory.deleteProject(expectedProjectCode, 200);
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
                .checkThatTheProjectHasBeenNotCreated();
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
