package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import pages.pageElements.Button;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageElements.Input.fillInputWithData;

public class SuitePage extends BasePage {

    private static final SelenideElement SUITE_LABEL = $x("//h3[text()='Suites']");
    private static final SelenideElement PRECONDITIONS_INPUT = $(".ProseMirror-trailingBreak");
    private static final SelenideElement DESCRIPTION = $("#description");
    private static final SelenideElement DELETE_SUITE_BUTTON = $x("//button[@aria-label='Delete suite']");
    private static final SelenideElement CREATE_NEW_SUITE_BUTTON = $x("//button[.//span[normalize-space()='Create new suite']]");

    @Step("Вернуться на страницу Suite")
    public SuitePage openSuitePage(String ProjectCode) {
        open("/project/" + ProjectCode);
        return this;
    }

    @Step("Создать сьюту")
    public SuitePage fillFieldsToCreateSuite(SuiteRequestModel data) {
        fillInputWithData("For example: Web Application", data.getTitle());
        PRECONDITIONS_INPUT.setValue(data.getPreconditions());
        DESCRIPTION.setValue(data.getDescription());
        return this;
    }

    @Step("Нажать на кнопку Create")
    public SuitePage clickCreateButton() {
        Button.clickButton("Create");
        return this;
    }

    @Step("Убедиться, что сьюта создана")
    public SuitePage checkTheSuiteIsCreated() {
        SUITE_LABEL.shouldBe(visible);
        return this;
    }

    @Step("Удалить сьюту")
    public SuitePage deleteSuite() {
        DELETE_SUITE_BUTTON.shouldBe(visible).click();
        Button.clickButton("Delete");
        return this;
    }

    @Step("Нажать на кнопку создать сьюту")
    public SuitePage clickButtonCreateNewSuite() {
        CREATE_NEW_SUITE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Убедиться, что сьюта удалена")
    public SuitePage checkTheSuiteIsDeleted() {
        SUITE_LABEL.shouldBe(visible);
        return this;
    }
}