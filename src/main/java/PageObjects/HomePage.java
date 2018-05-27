package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class HomePage {

    @FindBy(css = ".profile-photo")
    private WebElement userIcon;

    @FindBy(id = "Name")
    private WebElement loginInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = ".fa.fa-sign-in")
    private WebElement loginButton;

    public void open(WebDriver driver) {
        driver.navigate().to("https://epam.github.io/JDI");
    }

    public void login(String name, String password) {
        userIcon.click();
        loginInput.sendKeys(name);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void checkHomePageTitle(WebDriver driver) {
        assertEquals(driver.getTitle(), "Home Page");
    }
}
