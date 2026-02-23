package driver;

import com.codeborne.selenide.Configuration;
import config.Driver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UIDriver {


    public static void configuration() {
        Configuration.browserSize = Driver.config.getBrowserSize();
        Configuration.baseUrl = Driver.config.getBaseUrl();
        Configuration.browser = Driver.config.getBrowser();
        Configuration.timeout = 30000;
        Configuration.pollingInterval = 200;
        MutableCapabilities capabilities = new DesiredCapabilities();

        switch (Driver.config.getBrowser()){
            case "chrome":
                setChromeOptions(capabilities);
                break;
            case "edge":
                setEdgeOptions(capabilities);
            default:
                Configuration.browserCapabilities = capabilities;
        }
    }

    public static void setChromeOptions(MutableCapabilities capabilities) {
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-infobars")
                .addArguments("--lang=en-US")
                .merge(capabilities);
    }

    public static void setEdgeOptions(MutableCapabilities capabilities) {
        Configuration.browserCapabilities = new EdgeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--inprivate")
                .addArguments("--lang=en-US")
                .addArguments("--start-maximized")
                .merge(capabilities);
    }
}
