package PageObjects.hw4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.hw4.MenuItems;
import enums.hw4.Users;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class HomePage {

    private final String url = "https://epam.github.io/JDI";
    private final String title = "Home Page";

    private SelenideElement userIcon = $(".profile-photo");

    private SelenideElement loginInput = $("#Name");

    private SelenideElement passwordInput = $("#Password");

    private SelenideElement loginButton = $(".fa-sign-in");

    private SelenideElement username = $(".profile-photo span");

    private List<SelenideElement> images = $$(".icons-benefit");

    private List<SelenideElement> textItems = $$(".benefit-txt");

    private SelenideElement shortHeader = $("[name='main-title']");

    private SelenideElement longHeader = $("[name='jdi-text']");

    private SelenideElement serviceDropdown = $("li[class='dropdown']");

    private SelenideElement serviceMenu = $("ul[class='dropdown-menu']");

    private List<SelenideElement> serviceMenuItems = serviceMenu.$$("li");

    private SelenideElement serviceMenuOnLeftSection = $(".sidebar-menu > li:nth-child(3)");

    private List<SelenideElement> getServiceMenuOnLeftSectionItems = serviceMenuOnLeftSection.$$("li");

    public void openPage() {
        open(url);
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

    public void checkSingedInUsername(Users user) {
        username.shouldHave(Condition.text(user.name));
    }

    public void checkImagesDisplayed() {
        assertEquals(images.stream().filter(WebElement::isDisplayed).count(), 4);
    }

    public void checkTextItems() {
        assertEquals(textItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Arrays.asList("To include good practices\n" + "and ideas from successful\n" +
                                "EPAM project", "To be flexible and\n" + "customizable", "To be multiplatform",
                        "Already have good base\n" + "(about 20 internal and\n" +
                                "some external projects),\n" + "wish to get more…"));
    }

    public void checkShortHeaderText() {
        shortHeader.shouldHave(Condition.text("EPAM FRAMEWORK WISHES…"));
    }

    public void checkLongHeaderText() {
        longHeader.shouldHave(Condition.text("LOREM IPSUM DOLOR SIT AMET, " +
                "CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."));
    }

    public void checkServiceMenuItems() {
        serviceDropdown.click();
        assertEquals(serviceMenuItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(item -> item.toString().toUpperCase()).collect(Collectors.toList()));

    }

    public void checkServiceMenuItemsOnLeftSection() {
        serviceMenuOnLeftSection.click();
        assertEquals(getServiceMenuOnLeftSectionItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(MenuItems::toString).collect(Collectors.toList()));

    }

    public void selectServiceMenuItem(MenuItems item) {
        serviceDropdown.click();
        serviceMenu.find(withText(item.toString())).click();

    }

    public DifferentElementsPage openDifferentElementsPage() {
        selectServiceMenuItem(MenuItems.DIFFERENT_ELEMENTS);
        return new DifferentElementsPage();
    }

    public DatesPage openDatesPage() {
        selectServiceMenuItem(MenuItems.DATES);
        return new DatesPage();
    }

}
