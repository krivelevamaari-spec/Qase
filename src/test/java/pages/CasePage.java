package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.pageElements.Button;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CasePage extends BasePage{

    private static final SelenideElement TITLE_CASE_PAGE = $x("//h1[text()='Create test case']");

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
}
