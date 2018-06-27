package base.hw4;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

public class SelenideTestBase {

    @BeforeClass
    public void configs() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.screenshots = false;
        Configuration.pollingInterval = 200;
        Configuration.collectionsPollingInterval = 200;
    }
}
