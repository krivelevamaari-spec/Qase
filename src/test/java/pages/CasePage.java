package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import factory.model.CaseFactoryModel;
import pages.pageElements.Button;
import pages.pageElements.DropDown;
import pages.pageElements.Input;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CasePage extends BasePage{

    private static final SelenideElement TITLE_CASE_PAGE = $x("//h1[text()='Create test case']");
    private static final SelenideElement STEP_ACTION =
            $x("//div[@class='toastui-editor-ww-container']//div[@contenteditable='true']");
    private static final SelenideElement SECTION_CASE = $x("//h3[text()='Test cases without suite']");
    private static final SelenideElement CHECKBOX = $x("//input[@type='checkbox']");
    private static final SelenideElement BASKET_ICON = $x("//button[@aria-label='Delete']");

    @Step("Открыть форму создания кейса")
    public CasePage openCaseCreation() {
        Button.clickButton("Create new case");
        return this;
    }

    @Step("Убедиться, что открыта страница '{expectedTitle}'")
    public CasePage checkPageTitle(String expectedTitle) {
        TITLE_CASE_PAGE.shouldBe(visible).shouldHave(text(expectedTitle));
        return this;
    }

    @Step("Создать новый тест кейс")
    public CasePage createNewCase(CaseFactoryModel data) {
        Input.fillInputWithData("For example: Authorization", data.getTitle());
        DropDown.selectOption("Status", data.getStatus());
        DropDown.selectOption("Type", data.getType());
        DropDown.selectOption("Severity", data.getSeverity());
        DropDown.selectOption("Priority", data.getPriority());
        DropDown.selectOption("Layer", data.getLayer());
        DropDown.selectOption("Is flaky", data.getIsFlaky());
        DropDown.selectOption("Behavior", data.getBehavior());
        DropDown.selectOption("Automation status", data.getAutomation());
        return this;
    }

    @Step("Добавить шаги в тест кейс")
    public CasePage clickNewStepButton() {
        Button.clickButton("New step");
        return this;
    }

    @Step("Написать шаг тест кейса")
    public CasePage writeNewStep(String stepText) {
        STEP_ACTION.click();
        STEP_ACTION.sendKeys(stepText);
        return this;
    }

    @Step("Сохранить тест кейс")
    public CasePage clickSaveButton() {
        Button.clickButton("Save");
        return this;
    }

    @Step("Убедиться, что тест-кейс создан")
    public CasePage checkThatCaseIsCreated() {
        SECTION_CASE.shouldBe(visible);
        return this;
    }

    @Step("Выбрать чек бокс тест кейса")
    public CasePage clickCheckBox() {
        CHECKBOX.click();
        return this;
    }

    @Step("Удалить тест кейс")
    public CasePage clickDeleteButton() {
        BASKET_ICON.click();
        Input.fillInputWithData("Type CONFIRM to continue", "CONFIRM");
        Button.clickButton("Delete");
        return this;
    }
}
