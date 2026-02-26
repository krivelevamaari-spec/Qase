package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import tests.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("mkarpovich")
@Feature("Authorization")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class LoginTest extends BaseTest {

    @Test
    @Story("Authorization")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Authorization")
    })
    @DisplayName("Проверка успешной авторизации пользователя при валидных логине и пароле")
    public void userMustBeAuthWithValidLoginAndPassword() {
        login(email,password);

        step("Ожидаемый результат: открыта страница Projects", () -> {
            Assertions.assertAll(
                    () -> Assertions.assertTrue(projectPage.isTitleVisible()),
                    () -> Assertions.assertEquals("Projects", projectPage.titleMustHaveText())
            );
        });
    }

    @Story("Authorization")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("UI-test"),
            @Tag("Authorization")
    })
    @CsvSource({
            ", 20091989Qwe!!!",
            "akytat@mailto.plus, ",
            ", "
    })
    @ParameterizedTest(name = "Проверка получения сообщения об ошибке при попытке авторизации пользователя " +
            "без ввода логина: {0}, пароля: {1} и с отсутствующими данными")
    void errorMessageMustBeVisibleWithEnterInvalidData(String email, String password) {
        loginPage.openPage("/login");
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        step("Ожидаемый результат: получено сообщение об ошибке", () ->
                assertEquals("This field is required", loginPage.getErrorMessage(),
                        "Текст сообщения не совпадает")
        );
    }

    @Story("Authorization")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("UI-test"),
            @Tag("Authorization")
    })
    @CsvFileSource(resources = "/testData/loginTestData/incorrectDates.scv")
    @ParameterizedTest(name = "Проверка получения сообщения об ошибке при попытке авторизации пользователя " +
            "с помощью некорректного логина: {0} и пароля: {1}")
    void alertErrorMessageMustBeVisibleWithEnterIncorrectData(String email, String password) {
        loginPage.openPage("/login");
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        step("Ожидаемый результат: получено сообщение об ошибке", () ->
                loginPage.alertErrorMessage());
    }
}
