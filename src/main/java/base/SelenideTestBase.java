package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

public class SelenideTestBase {

    @BeforeClass
    public void configs() {
        Configuration.browser = "chrome";
        Configuration.screenshots = false;
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 100;
        Configuration.collectionsPollingInterval = 200;

    }
}
