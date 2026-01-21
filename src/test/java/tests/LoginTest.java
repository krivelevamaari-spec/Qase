package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest{

    @BeforeEach
    void openLoginPage() {
        loginPage.openPage("/login");
    }

    @Test
    @DisplayName("Проверка авторизации пользователя при валидных логине и пароле")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void userMustBeAutWithValidLoginAndPassword(){
        loginPage.setValueEmailInput("akytat@mailto.plus")
                 .setValuePasswordInput("20091989Qwe!!!")
                 .clickSignInButton();

        Assertions.assertAll(
                () -> Assertions.assertNotEquals("Projects!!!", projectPage.titleMustHaveText()),
                () -> Assertions.assertTrue(projectPage.titleMustHaveText().startsWith("Proj")),
                () -> Assertions.assertEquals("Projects", projectPage.titleMustHaveText())
        );
    }

    @ParameterizedTest(name = "Проверка отказа в авторизации пользователя без ввода емайл и пароля")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    @CsvSource({
            ", 20091989Qwe!!!",
            "akytat@mailto.plus, ",
            ", "
    })
    void errorMessageShouldBeVisibleWithEnterInvalidData(String email, String password){
        loginPage.setValueEmailInput(email)
                 .setValuePasswordInput(password)
                 .clickSignInButton();

        assertEquals("This field is required",loginPage.getErrorMessage(),
                "Текст сообщения не совпадает");
    }

    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    @CsvFileSource(resources = "/testData/incorrectData.csv" )
    @ParameterizedTest(name = "Проверка отказа в авторизации пользователя с невалидным логином: {0} и паролем: {1}")
    void alertErrorMessageShouldBeVisibleWithEnterInvalidData(String email, String password){
        loginPage.setValueEmailInput(email)
                 .setValuePasswordInput(password)
                 .clickSignInButton()
                 .alertErrorMessage();
    }
}
