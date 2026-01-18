package pages.pageElements;

import static com.codeborne.selenide.Selenide.$x;

public class Input {

    public static void fillInputWithData(String placeholder, String data){
        $x("//input[@placeholder = '"+placeholder+"']").setValue(data);
    }
}
