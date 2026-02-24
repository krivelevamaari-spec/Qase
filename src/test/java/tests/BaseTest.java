package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.Credentials;
import driver.UIDriver;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import factory.CreateProjectFactory;
import models.responce.project.get.Entity;
import models.responce.project.get.ProjectGetResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.CasePage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.SuitePage;

import static api.steps.ProjectSteps.deleteProject;
import static api.steps.ProjectSteps.getProjects;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;

public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectPage projectPage;
    protected CreateProjectFactory projectFactory;
    protected SuitePage suitePage;
    protected CasePage casePage;
    protected String email = Credentials.config.getEmail();
    protected String password = Credentials.config.getPassword();

    @BeforeAll
    public static void configuration() {
        UIDriver.configuration();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        projectPage = new ProjectPage();
        projectFactory = new CreateProjectFactory();
        suitePage = new SuitePage();
        casePage = new CasePage();
    }

    @BeforeEach
    void deleteAllProjectsIfNeed() {
        ProjectGetResponseModel response = getProjects(200)
                .extract().as(ProjectGetResponseModel.class);

        if (response.getResult().getTotal() > 0) {
            response.getResult().getEntities().stream()
                    .map(Entity::getCode)
                    .forEach(code -> deleteProject(code, 200));
        }
    }

    @Step("Аутентификация пользователя")
    public void login(String email, String password) {
        loginPage.openPage("/login");
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}