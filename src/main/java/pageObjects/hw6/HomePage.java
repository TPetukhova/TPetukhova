package pageObjects.hw6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.hw6.MenuItems;
import enums.hw6.Users;
import io.qameta.allure.Step;
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

    @Step("Open Home Page")
    @Given("I am on Home Page")
    public void openHomePage() {
        open(url);
    }

    @Step("Verify Page Title")
    @Then("Page Title is " + title)
    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    @Step("Login")
    @When("I login as (.+) user")
    public void login(Users user) {
        userIcon.click();
        loginInput.sendKeys(user.login);
        passwordInput.sendKeys(user.password);
        loginButton.click();
    }

    @Step("Login")
    @Given("I am logged in as (.+)")
    public void loginAndCheckUsername(String name) {
        for (Users user : Users.values()) {
            if (user.name.equals(name.toUpperCase())) {
                login(user);
                checkShownUsername(user);
            }
        }
    }

    @Step("Check page layout")
    @Then("Home Page contains correct elements")
    public void homePageContainsCorrectElements() {
        checkImagesDisplayed();
        checkTextItems();
        checkShortHeaderText();
        checkLongHeaderText();
    }

    @Step("Check correct username")
    @Then("Correct username of (.+) is shown")
    public void checkShownUsername(Users user) {
        username.shouldHave(Condition.text(user.name));
    }

    @Step("Check service menu")
    @Then("Service Menu is available and contains correct items")
    public void checkServiceMenus() {
        checkServiceMenuItems();
        checkServiceMenuItemsOnLeftSection();
    }

    @Step("Open Different Elements page")
    @When("Different Elements page is open from Header menu")
    public DifferentElementsPage openDifferentElementsPage() {
        selectServiceMenuItem(MenuItems.DIFFERENT_ELEMENTS);
        return new DifferentElementsPage();
    }

    @Step("Open User Table page")
    @When("I open User Table page through the header menu Service -> User Table")
    public UserTablePage openUserTablePage() {
        selectServiceMenuItem(MenuItems.USER_TABLE);
        return new UserTablePage();
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


}

