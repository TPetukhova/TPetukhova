package PageObjects.hw4;

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

    SelenideElement userIcon = $(".profile-photo");

    SelenideElement loginInput = $("#Name");

    SelenideElement passwordInput = $("#Password");

    SelenideElement loginButton = $(".fa-sign-in");

    SelenideElement username = $(".profile-photo span");

    List<SelenideElement> images = $$(".icons-benefit");

    List<SelenideElement> textItems = $$(".benefit-txt");

    SelenideElement shortHeader = $("[name='main-title']");

    SelenideElement longHeader = $("[name='jdi-text']");

    SelenideElement serviceDropdown = $("li[class='dropdown']");

    SelenideElement serviceDropdownMenu = $("ul[class='dropdown-menu']");

    // need to create a good way for that!!!!
    List<SelenideElement> serviceMenuDropdownItems = $$("ul[class='dropdown-menu'] li");

    SelenideElement serviceMenuLeftSection = $(".sidebar-menu > li:nth-child(3)");

    List<SelenideElement> serviceMenuDropdownLeftItems = $$(".sidebar-menu > li:nth-child(3) li");

    //SelenideElement differentElementsOption = $(".dropdown-menu li:nth-child(7)");

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
        assertEquals(username.getText(), user.name);
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
        assertEquals(shortHeader.getText(), "EPAM FRAMEWORK WISHES…");
    }

    public void checkLongHeaderText() {
        assertEquals(longHeader.getText(), "LOREM IPSUM DOLOR SIT AMET, " +
                "CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
    }

    public void checkServiceMenuDropdownValuesHeader() {
        serviceDropdown.click();
        assertEquals(serviceMenuDropdownItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(item -> item.toString().toUpperCase()).collect(Collectors.toList()));

    }

    public void checkServiceMenuDropdownValuesLeftSection() {
        serviceMenuLeftSection.click();
        assertEquals(serviceMenuDropdownLeftItems.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(MenuItems::toString).collect(Collectors.toList()));

    }

    public void selectServiceDropdownValue(MenuItems item) {
        serviceDropdown.click();
        serviceDropdownMenu.find(withText(item.toString())).click();

    }


    public DifferentElementsPage openDifferentElementsPage() {
        selectServiceDropdownValue(MenuItems.DIFFERENT_ELEMENTS);
        DifferentElementsPage differentElementsPage = new DifferentElementsPage();
        return differentElementsPage;
    }


    public DatesPage openDatesPage() {
        selectServiceDropdownValue(MenuItems.DATES);
        DatesPage datesPage = new DatesPage();
        return datesPage;
    }

}
