package PageObjects.hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = ".profile-photo")
    private WebElement userIcon;

    @FindBy(id = "Name")
    private WebElement loginInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = ".fa.fa-sign-in")
    private WebElement loginButton;

    public void open() {
        driver.navigate().to("https://epam.github.io/JDI");
    }

    public void checkTitle() {
        assertEquals(driver.getTitle(),"Home Page");
    }

    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
