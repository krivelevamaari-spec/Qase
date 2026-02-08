package pages.pageElements;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Input {

    public static void fillInputWithData(String placeholder, String data){
        $x("//input[@placeholder = '" + placeholder + "']")
                .shouldBe(visible, Duration.ofSeconds(10))
                .setValue(data);
    }
}
