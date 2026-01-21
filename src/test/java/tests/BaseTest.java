package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;
import pages.ProjectPage;

public class BaseTest {

    LoginPage loginPage;
    ProjectPage projectPage;

    @BeforeEach
    public void setUp(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://app.qase.io";

        loginPage = new LoginPage();
        projectPage = new ProjectPage();
    }
}
