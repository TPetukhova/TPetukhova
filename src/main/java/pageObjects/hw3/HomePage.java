package pageObjects.hw3;

import enums.hw3.Users;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {

    private final String url = "https://epam.github.io/JDI";
    private final String title = "Home Page";

    @FindBy(css = ".profile-photo")
    private WebElement userIcon;

    @FindBy(css = "#Name")
    private WebElement loginInput;

    @FindBy(css = "#Password")
    private WebElement passwordInput;

    @FindBy(css = ".fa-sign-in")
    private WebElement loginButton;

    @FindBy(css = ".profile-photo span")
    private WebElement username;

    @FindBy(css = ".uui-navigation.nav > li > a")
    private List<WebElement> menuItems;

    @FindBy(css = ".icons-benefit")
    private List<WebElement> images;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> textItems;

    @FindBy(css = "[name='main-title']")
    private WebElement shortHeader;

    @FindBy(css = "[name='jdi-text']")
    private WebElement longHeader;

    @FindBy(css = ".main-content a")
    private WebElement subHeader;

    @FindBy(css = "[name='navigation-sidebar']")
    private WebElement leftSection;

    @FindBy(css = "footer")
    private WebElement footer;

    public void login(Users user) {
        userIcon.click();
        loginInput.sendKeys(user.login);
        passwordInput.sendKeys(user.password);
        loginButton.click();
    }

    public void open(WebDriver driver) {
        driver.navigate().to(url);
    }

    public void checkTitle(WebDriver driver) {
        assertEquals(driver.getTitle(), title);
    }

    public void checkSingedInUsername(Users user) {
        assertEquals(username.getText(), user.name);
    }

    public void checkMenuItemsText() {
        assertEquals(menuItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"));
    }

    public void checkImagesDisplayed() {
        assertEquals(images.stream().filter(i -> i.isDisplayed()).count(), 4);
    }

    public void checkTextItems() {
        assertEquals(textItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Arrays.asList("To include good practices\n" + "and ideas from successful\n" +
                                "EPAM project", "To be flexible and\n" + "customizable", "To be multiplatform",
                        "Already have good base\n" + "(about 20 internal and\n" +
                                "some external projects),\n" + "wish to get more…"));
    }

    public void checkShortHeaderText() {
        assertEquals(shortHeader.getText(), "EPAM FRAMEWORK WISHES…");
    }

    public void checkLongHeaderText() {
        assertEquals(longHeader.getText(), "LOREM IPSUM DOLOR SIT AMET, " +
                "CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
    }

    public void checkSubHeaderText() {
        assertEquals(subHeader.getText(), "JDI GITHUB");
    }

    public void checkSubHeaderLink() {
        assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");
    }

    public void checkLeftSectionShown() {
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooterShown() {
        assertTrue(footer.isDisplayed());
    }

}
