package PageObjects.hw4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class HomePage {

    private String url = "https://epam.github.io/JDI";

    private String title = "Home Page";

    SelenideElement userIcon = $(".profile-photo");

    SelenideElement loginInput = $("#Name");

    SelenideElement passwordInput = $("#Password");

    SelenideElement loginButton = $(".fa-sign-in");

    SelenideElement username = $(".profile-photo span");

    List<SelenideElement> images = $$(".icons-benefit");

    List<SelenideElement> textItems = $$(".benefit-txt");

    SelenideElement shortHeader = $(By.name("main-title"));

    SelenideElement longHeader = $(By.name("jdi-text"));

    public void openPage() {
        open(url);
    }

    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    public void login(String username, String password) {
        userIcon.click();
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void checkSingedInUsername(String name) {
        assertEquals(username.getText(), name);
    }

    public void checkImagesDisplayed() {
        assertEquals(images.stream().filter(i -> i.isDisplayed()).count(), 4);
    }

    public void checkTextItems(List<String> items) {
        assertEquals(textItems.stream().map(item -> item.getText()).collect(Collectors.toList()),
                items);
    }

    public void checkShortHeaderText(String text) {
        assertEquals(shortHeader.getText(), text);
    }

    public void checkLongHeaderText(String text) {
        assertEquals(longHeader.getText(), text);
    }
}
