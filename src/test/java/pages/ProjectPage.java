package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.request.project.post.ProjectRequestModel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static pages.pageElements.Input.fillInputWithData;

public class ProjectPage extends BasePage {

    private static final SelenideElement PROJECT_PAGE_TITLE = $x("//h1[text()='Projects']");
    private static final SelenideElement CREATE_PROJECT_BUTTON =
            $x("//button[.//span[text()='Create new project']]");
    private static final SelenideElement SAVE_PROJECT_BUTTON =
            $x("//button[.//span[text()='Create project']]");
    private static final SelenideElement DESCRIPTION_TEXT_AREA = $("#description-area");
    private static final SelenideElement TITLE_CREATED_PROJECT = $x("//div[@id='application-content']//h1");
    private static final SelenideElement ERROR_MESSAGE = $(".FKqFlv");
    private static final SelenideElement BURGER_MENU = $("button[aria-label='Open action menu']");
    private static final SelenideElement REMOVE_BUTTON = $x("//*[@data-testid='remove']");
    private static final SelenideElement DELETE_BUTTON = $x("//button[.//span[text()='Delete project']]");
    private static final SelenideElement RADIOBUTTON_PRIVATE =
            $x("//label[.//span[text()='Private']]//input");
    private static final SelenideElement RADIOBUTTON_PUBLIC =
            $x("//label[.//span[text()='Public']]//input");
    String projectProjectsList = "//tr/ancestor::tbody//div/div/a[text()='%s']";

    @Step("Вернуться на страницу Projects")
    public ProjectPage openProjectPage() {
        open("/projects");
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

    @Step("Нажать на кнопку Create New Project")
    public ProjectPage clickCreateProjectButton() {
        CREATE_PROJECT_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Создать новый проект")
    public ProjectPage createProject(ProjectRequestModel data) {
        fillInputWithData("For example: Web Application", data.getTitle());
        fillInputWithData("For example: WA", data.getCode());
        DESCRIPTION_TEXT_AREA.setValue(data.getDescription());
        return this;
    }

    @Step("Нажать на кнопку Create Project")
    public ProjectPage clickSaveProjectButton() {
        SAVE_PROJECT_BUTTON.shouldBe(visible).click();
        return this;
    }
    @Step("Убедиться, что проект создан")
    public ProjectPage checkThatTheProjectHasBeenCreated(String expectedProjectCode) {
        TITLE_CREATED_PROJECT.shouldBe(visible)
                .shouldHave(text(expectedProjectCode));
        return this;
    }
    @Step("Получить сообщение об ошибке при создании проекта с невалидными данными")
    public ProjectPage checkThatTheProjectHasBeenNotCreated() {
        ERROR_MESSAGE.shouldBe(visible);
        return this;
    }

    @Step("Удалить созданный проект")
    public ProjectPage deleteCreatedProject() {
        BURGER_MENU.shouldBe(visible).click();
        REMOVE_BUTTON.shouldBe(visible).click();
        DELETE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Убедиться, что радиокнопка Private выбрана")
    public ProjectPage checkRadioButtonPrivate() {
        RADIOBUTTON_PRIVATE.scrollIntoView(true)
                .shouldBe(selected);
        return this;
    }

    @Step("Убедиться, что радиокнопка Public не выбрана")
    public ProjectPage chekRadioButtonPublic() {
        RADIOBUTTON_PUBLIC.scrollIntoView(true)
                .shouldNotBe(selected);
        return this;
    }

    @Step("Проверка отсутствия проекта в списке")
    public ProjectPage checkThatProjectIsDeleted(String projectTitle){
        $x(format(projectProjectsList,projectTitle)).shouldNotBe(visible);
        return this;
    }
}
