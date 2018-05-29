package PageObjects.hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {

    private WebDriver driver;
    private String url = "https://epam.github.io/JDI";
    private String title = "Home Page";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = ".profile-photo")
    private WebElement userIcon;

    @FindBy(id = "Name")
    private WebElement loginInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = ".fa-sign-in")
    private WebElement loginButton;

    @FindBy(css = ".profile-photo span")
    private WebElement username;

    @FindBy(css = ".uui-navigation.nav > li > a")
    private List<WebElement> menuItems;

    @FindBy(css =".icons-benefit")
    private List<WebElement> images;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> textItems;

    @FindBy(name = "main-title")
    private WebElement shortHeader;

    @FindBy(name = "jdi-text")
    private WebElement longHeader;

    @FindBy(css = ".main-content a")
    private WebElement subHeader;

    @FindBy(name = "navigation-sidebar")
    private WebElement leftSection;

    @FindBy(tagName = "footer")
    private WebElement footer;

    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void open() {
        driver.navigate().to(url);
    }

    public void checkTitle() {
        assertEquals(driver.getTitle(),title);
    }

    public void checkSingedInUsername(String name) {
        assertEquals(username.getText(),name);
    }

    public void checkMenuItemsText(List<String> items) {
        assertEquals(menuItems.stream().map(item -> item.getText()).collect(Collectors.toList()),
                items);
    }

    public void checkImagesDisplayed() {
        assertEquals(images.stream().filter(i -> i.isDisplayed()).count(),4);
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

    public void checkSubHeaderText(String text) {
        assertEquals(subHeader.getText(),text);
    }

    public void checkSubHeaderLink(String link) {
        assertEquals(subHeader.getAttribute("href"), link);
    }

    public void checkLeftSectionShown() {
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooterShown() {
        assertTrue(footer.isDisplayed());
    }

}
