package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:config/driver.properties"})

public interface DriverConfig extends Config {

    @Key("browser")
    String getBrowser();

    @Key("browserSize")
    String getBrowserSize();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("baseApiUri")
    String getBaseApiUri();
}
