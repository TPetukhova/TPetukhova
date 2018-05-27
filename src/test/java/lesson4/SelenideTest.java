package lesson4;

import base.SelenideTestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Users.PITER_CHAILOVSKII;

public class SelenideTest extends SelenideTestBase {

    @Test(invocationCount = 3, threadPoolSize = 3)
    public void SimpleTest() {

        open("https://epam.github.io/JDI/index.html");

        Assert.assertEquals(getWebDriver().getTitle(), "Home Page");

        $(".profile-photo").click();
        $("#Name").sendKeys(PITER_CHAILOVSKII.login);
        $("#Password").sendKeys(PITER_CHAILOVSKII.password);
        $(".fa-sign-in").click();

        SelenideElement userName = $(".profile-photo span");
        userName.should(Condition.visible);

        userName.shouldHave(Condition.text(PITER_CHAILOVSKII.name));

        $$(".benefit-icon").shouldHaveSize(4);
        getWebDriver().close();

    }
}
