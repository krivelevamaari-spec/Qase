package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageElements.Input.fillInputWithData;

public class LoginPage {

    private static final SelenideElement EMAIL_INPUT = $x("//input[@name='email']");
    private static final SelenideElement PASSWORD_INPUT = $x("//input[@name='password']");
    private static final SelenideElement SIGN_IN_BUTTON = $("button[type='submit']");
    private static final SelenideElement ERROR_MESSAGE = $x("//small[text()='This field is required']");
    private static final SelenideElement ALERT_ERROR_MESSAGE = $x("//div[@role='alert']/span");

    public LoginPage openPage(){
        open("https://app.qase.io/login");
        return this;
    }

    public LoginPage setValueEmailInput(String email){
        fillInputWithData("Work email", email);
        return this;
    }

    public LoginPage setValuePasswordInput(String password){
        fillInputWithData("Password", password);
        return this;
    }

    public LoginPage clickSignInButton(){
        SIGN_IN_BUTTON.click();
        return this;
    }

    public String getErrorMessage(){
        String errorMessage = ERROR_MESSAGE.getText();
        return errorMessage;
    }

    public LoginPage alertErrorMessage(){
        ALERT_ERROR_MESSAGE.shouldBe(visible);
        return this;
    }
}
