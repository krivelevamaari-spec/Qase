package pages.pageElements;

import models.CreateCaseFactory;
import models.enams.Behavior;
import models.enams.HasValue;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static models.CreateCaseFactory.chooseRandomDropDownOption;

public class DropDown {

    private static String inputField = "//label[normalize-space(text())='%s']/following::div[@role='combobox']";
    private static String dropDownOption = "//div[@title='%s']";

    public static <T extends Enum<T> & HasValue> void selectRandom(String inputField, T[] enumValues) {
        $x(String.format(inputField)).click();

        List<String> options = Arrays.stream(enumValues)
                .map(HasValue::getValue)
                .toList();

        String randomText = CreateCaseFactory.chooseRandomDropDownOption(options);

        $x(String.format(dropDownOption, randomText)).click();
    }
}
