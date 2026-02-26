package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.request.project.post.ProjectRequestModel;
import pages.pageElements.Button;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static pages.pageElements.Button.clickButton;
import static pages.pageElements.Input.fillInputWithData;

public class ProjectPage extends BasePage {

    private static final SelenideElement PROJECT_PAGE_TITLE = $x("//h1[text()='Projects']");
    private static final SelenideElement DESCRIPTION_TEXT_AREA = $("#description-area");
    private static final SelenideElement TITLE_CREATED_PROJECT = $x("//div[@id='application-content']//h1");
    private static final SelenideElement ERROR_MESSAGE = $x("//*[@class='gJWbRK']");
    private static final SelenideElement BURGER_MENU = $("button[aria-label='Open action menu']");
    private static final SelenideElement REMOVE_BUTTON = $x("//*[@data-testid='remove']");
    private static final SelenideElement DELETE_BUTTON = $x("//button[.//span[text()='Delete project']]");

    String projectProjectsList = "//tr/ancestor::tbody//div/div/a[text()='%s']";

    @Step("Вернуться на страницу Projects")
    public ProjectPage openProjectPage() {
        open("/projects");
        return this;
    }

    @Step("Открыть страницу проекта {projectCode}")
    public ProjectPage openProjectByCode(String projectCode) {
        open("/project/" + projectCode.toUpperCase());
        return this;
    }

    @Step("Получить заголовок страницы")
    public boolean isTitleVisible() {
        PROJECT_PAGE_TITLE.shouldBe(visible);
        return true;
    }

    @Step("Получить заголовок страницы проекта")
    public String titleMustHaveText() {
        return PROJECT_PAGE_TITLE.shouldBe(visible).getText();
    }

    @Step("Создать проект")
    public ProjectPage clickCreateProjectButton() {
        Button.clickButton("Create new project");
        return this;
    }

    @Step("Заполнить поля проекта данными")
    public ProjectPage createProject(ProjectRequestModel data) {
        fillInputWithData("For example: Web Application", data.getTitle());
        fillInputWithData("For example: WA", data.getCode());
        DESCRIPTION_TEXT_AREA.setValue(data.getDescription());
        return this;
    }

    @Step("Сохранить проект")
    public ProjectPage clickSaveProjectButton() {
        clickButton("Create project");
        return this;
    }

    @Step("Убедиться, что проект создан")
    public ProjectPage checkThatTheProjectHasBeenCreated(String expectedProjectCode) {
        TITLE_CREATED_PROJECT.shouldBe(visible)
                .shouldHave(text(expectedProjectCode));
        return this;
    }

    @Step("Получить сообщение об ошибке при создании проекта с невалидными данными")
    public ProjectPage checkThatTheProjectHasBeenNotCreated(String expectedErrorMessage) {
        ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text(expectedErrorMessage));
        return this;
    }

    @Step("Удалить проект")
    public ProjectPage deleteCreatedProject() {
        BURGER_MENU.shouldBe(visible).click();
        REMOVE_BUTTON.shouldBe(visible).click();
        DELETE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Проверка отсутствия проекта в списке")
    public ProjectPage checkThatProjectIsDeleted(String projectTitle) {
        $x(format(projectProjectsList, projectTitle)).shouldNotBe(visible);
        return this;
    }
}
