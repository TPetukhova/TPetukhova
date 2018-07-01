package PageObjects.hw6;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
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

    // add Step annotations

    // user enums not strings where possible

    // make 1 method from 2 login methods - with enums

    private final String url = "https://epam.github.io/JDI";
    private final String title = "Home Page";

    public HomePage() {
        page(this);
    }

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

    private SelenideElement serviceMenuOnLeftSection = $(".sidebar-menu > li:nth-child(3)");

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

    @Step("Verify Service Menu")
    public void checkServiceMenuItems() {
        serviceDropdown.click();
        assertEquals(serviceMenu.$$("li").stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(item -> item.toString().toUpperCase()).collect(Collectors.toList()));

    }

    @Step("Verify Left Service Menu")
    public void checkServiceMenuItemsOnLeftSection() {
        serviceMenuOnLeftSection.click();
        assertEquals(serviceMenuOnLeftSection.$$("li").stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(MenuItems.values()).map(MenuItems::toString).collect(Collectors.toList()));

    }

    @Step("Verify Service Menu")
    public void selectServiceMenuItem(MenuItems item) {
        serviceDropdown.click();
        serviceMenu.find(withText(item.toString())).click();

    }

    @Step("Login")
    @When("I login as (.+) user")
    public void iLoginAsUser(Users user) {
        login(user);
    }

    @Given("I am logged in as (.+)")
    public void iAmLoggedInAs(String name) {
        for (Users user : Users.values())
        {
            if (user.name.equals(name.toUpperCase())) {
                login(user);
                singedInUsernameIsShown(user);
            }
        }
    }

    @And("Home Page contains correct elements")
    public void homePageContainsCorrectElements() {
        checkImagesDisplayed();
        checkTextItems();
        checkShortHeaderText();
        checkLongHeaderText();
    }

    @Then("Correct username of (.+) is shown")
    public void singedInUsernameIsShown(Users user) {
        assertEquals(username.getText(), user.name);
    }

    @And("Service Menu is available and contains correct items")
    public void serviceMenuIsAvailable() {
        checkServiceMenuItems();
        checkServiceMenuItemsOnLeftSection();
    }

    @When("Different Elements page is open from Header menu")
    public DifferentElementsPage differentElementsPageIsOpenFromHeaderMenu() {
        selectServiceMenuItem(MenuItems.DIFFERENT_ELEMENTS);
        return new DifferentElementsPage();
    }

    @When("I open User Table page through the header menu Service -> User Table")
    public UserTablePage iOpenUserTablePageThroughTheHeader() {
        selectServiceMenuItem(MenuItems.USER_TABLE);
        return new UserTablePage();
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

    public void login(Users user) {
        userIcon.click();
        loginInput.sendKeys(user.login);
        passwordInput.sendKeys(user.password);
        loginButton.click();
    }

}

