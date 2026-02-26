package tests.ui;

import api.steps.ProjectSteps;
import factory.CreateProjectFactory;
import io.qameta.allure.*;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

@Owner("mkarpovich")
@Feature("Project")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ProjectTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        login(email, password);
    }

    @Test
    @Story("Project")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    @DisplayName("Проверка создания нового проекта с валидными данными")
    public void projectMustBeCreated() {
        projectPage.clickCreateProjectButton();

        ProjectRequestModel createProject = CreateProjectFactory.getRandomData();
        String expectedProjectCode = createProject.getCode();

        projectPage.createProject(createProject)
                .clickSaveProjectButton()
                .checkThatTheProjectHasBeenCreated(expectedProjectCode);
    }

    @Test
    @Story("Project")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    @DisplayName("Проверка валидации полей при создании проекта с некорректными данными")
    public void projectMustBeNotCreated() {
        projectPage.clickCreateProjectButton();

        ProjectRequestModel notCreateProject = CreateProjectFactory.getWrongRandomData();

        projectPage.createProject(notCreateProject)
                .clickSaveProjectButton()
                .checkThatTheProjectHasBeenNotCreated("The code format is invalid.");
    }

    @Test
    @Story("Project")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    @DisplayName("Проверка удаления проекта")
    public void projectMustBeDeleted() {
        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        String projectTitle = projectData.getTitle();

        ProjectSteps.createProject(projectData, 200);

        projectPage.openProjectPage()
                .deleteCreatedProject()
                .checkThatProjectIsDeleted(projectTitle);
    }
}
