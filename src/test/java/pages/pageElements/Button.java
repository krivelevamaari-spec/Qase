package pages.pageElements;

import static com.codeborne.selenide.Selenide.$x;

public class Button {

    public static void clickButton(String buttonName) {
        $x("//span[text()='" + buttonName + "']").click();
    }
}
