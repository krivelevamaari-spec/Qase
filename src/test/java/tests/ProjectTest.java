package tests;

import models.CreateProjectWindow;
import org.junit.jupiter.api.*;

public class ProjectTest extends BaseTest{

    @BeforeEach
    void openLoginPage() {
        loginPage.openPage("/login");
    }

    @Test
    @DisplayName("Проверка создания нового проекта")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void projectMustBeCreated(){
        loginPage.setValueEmailInput("akytat@mailto.plus")
                .setValuePasswordInput("20091989Qwe!!!")
                .clickSignInButton();

        projectPage.clickCreateProjectButton();

        CreateProjectWindow data = CreateProjectWindow.builder()
                .projectName("New Project")
                .projectCode("NP")
                .description("Welcome")
                .build();
        projectPage.createProject(data);
        projectPage.clickSaveProjectButton();
    }
}
