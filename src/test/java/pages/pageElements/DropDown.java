package pages.pageElements;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class DropDown {

    private static String inputField = "//label[text()='%s']/../div";
    private static String dropDownOption = "//div[@title='%s']";

    public static void selectOption(String label, String option) {
        $x(format(inputField, label)).click();
        $x(format(dropDownOption, option)).click();
    }
}
