package pageObjects.hw5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.hw5.MenuItems;
import enums.hw5.Users;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class HomePage {

    private final String url = "https://epam.github.io/JDI";
    private final String title = "Home Page";

    @FindBy(css = ".profile-photo")
    private SelenideElement userIcon;

    @FindBy(id = "Name")
    private SelenideElement loginInput;

    @FindBy(id = "Password")
    private SelenideElement passwordInput;

    @FindBy(css = ".fa-sign-in")
    private SelenideElement loginButton;

    @FindBy(css = ".profile-photo span")
    private SelenideElement username;

    @FindBy(css = ".icons-benefit")
    private List<SelenideElement> images;

    @FindBy(css = ".benefit-txt")
    private List<SelenideElement> textItems;

    @FindBy(css = "[name='main-title']")
    private SelenideElement shortHeader;

    @FindBy(css = "[name='jdi-text']")
    private SelenideElement longHeader;

    @FindBy(css = "li[class='dropdown']")
    private SelenideElement serviceDropdown;

    @FindBy(css = "ul[class='dropdown-menu']")
    private SelenideElement serviceMenu;

    @FindBy(css = "ul[class='dropdown-menu'] li")
    private List<SelenideElement> serviceMenuItems;

    @FindBy(css = ".sidebar-menu > li:nth-child(3)")
    private SelenideElement serviceMenuOnLeftSection;

    @FindBy(css = ".sidebar-menu > li:nth-child(3) li")
    private List<SelenideElement> getServiceMenuOnLeftSectionItems;

    public void openPage() {
        open(url, HomePage.class);
    }

    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    public void login(Users user) {
        userIcon.click();
        loginInput.sendKeys(user.login);
        passwordInput.sendKeys(user.password);
        loginButton.click();
    }

    public void checkUsername(Users user) {
        username.shouldHave(Condition.text(user.name));
    }

    public void checkImages() {
        assertEquals(images.stream().filter(WebElement::isDisplayed).count(), 4);
    }

    public void checkTextItems() {
        assertEquals(textItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Arrays.asList("To include good practices\n" + "and ideas from successful\n" +
                                "EPAM project", "To be flexible and\n" + "customizable", "To be multiplatform",
                        "Already have good base\n" + "(about 20 internal and\n" +
                                "some external projects),\n" + "wish to get more…"));
    }

    public void checkShortHeader() {
        shortHeader.shouldHave(Condition.text("EPAM FRAMEWORK WISHES…"));
    }

    public void checkLongHeader() {
        longHeader.shouldHave(Condition.text("LOREM IPSUM DOLOR SIT AMET, " +
                "CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."));
    }

    public void openServiceMenu() {
        serviceDropdown.click();
    }

    public void checkServiceMenu() {
        assertEquals(serviceMenuItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(item -> item.toString().toUpperCase()).collect(Collectors.toList()));

    }

    public void openServiceMenuLeft() {
        serviceMenuOnLeftSection.click();
    }

    public void checkServiceMenuLeft() {
        assertEquals(getServiceMenuOnLeftSectionItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(MenuItems::toString).collect(Collectors.toList()));

    }

    public void selectServiceMenuItem(MenuItems item) {
        serviceDropdown.click();
        serviceMenu.find(withText(item.toString())).click();

    }

    public DifferentElementsPage openDifferentElementsPage() {
        selectServiceMenuItem(MenuItems.DIFFERENT_ELEMENTS);
        return page(DifferentElementsPage.class);
    }

    public DatesPage openDatesPage() {
        selectServiceMenuItem(MenuItems.DATES);
        return page(DatesPage.class);
    }

}
