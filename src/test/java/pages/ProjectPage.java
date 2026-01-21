package pages;

import com.codeborne.selenide.SelenideElement;
import models.CreateProjectWindow;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static pages.pageElements.Input.fillInputWithData;

public class ProjectPage extends BasePage {

    private static final SelenideElement PROJECT_PAGE_TITLE = $x("//h1[text()='Projects']");
    private static final SelenideElement CREATE_PROJECT_BUTTON = $x("//button[.//span[text()='Create new project']]");
    private static final SelenideElement SAVE_PROJECT_BUTTON = $x("//button[.//span[text()='Create project']]");

    public void openProjectPage(){
        open("/projects");
    }

    public String titleMustHaveText(){
        return PROJECT_PAGE_TITLE.getText();
    }

    public void clickCreateProjectButton(){
        CREATE_PROJECT_BUTTON.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public void createProject(CreateProjectWindow data) {
        fillInputWithData("For example: Web Application", data.getProjectName());
        fillInputWithData("For example: WA", data.getProjectCode());
        fillInputWithData("Write a few sentences about your project", data.getDescription());
    }

    public void clickSaveProjectButton(){
        SAVE_PROJECT_BUTTON.click();
    }
}
