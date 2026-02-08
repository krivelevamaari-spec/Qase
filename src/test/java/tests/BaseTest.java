package tests;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Credentials;
import driver.UIDriver;
import io.qameta.allure.selenide.AllureSelenide;
import models.CreateProjectFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;
import pages.ProjectPage;
import pages.SuitePage;
import tests.api.steps.ProjectSteps;

public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectPage projectPage;
    protected CreateProjectFactory projectFactory;
    protected SuitePage suitePage;
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
    }
}
