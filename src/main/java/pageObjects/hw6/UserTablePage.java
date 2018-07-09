package pageObjects.hw6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.hw6.UserType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserTablePage {

    // user enums not strings where possible

    // make 1 method from 2 login methods - with enums

    private final String title = "User Table";
    private final String url = "https://epam.github.io/JDI/user-table.html";

    private SelenideElement userTable = $("#user-table");

    private SelenideElement leftSection = $(".info-panel-header");

    private SelenideElement logs = $(".logs");

    private SelenideElement pagination = $(".uui-pagination");

    private List<SelenideElement> userTableHeaders = $$("#user-table th");

    private List<SelenideElement> userTableRaws = $$("#user-table tbody tr");

    private List<SelenideElement> typeDropdownValues = $$("#user-table tbody tr:nth-child(2) option");

    private By userNameSelectorRaw = By.cssSelector("td:first-child");

    private By descriptionSelectionRaw = By.cssSelector("span");

    private By usernameSelectorRaw = By.cssSelector("a");

    private By userTypeSelectorRaw = By.cssSelector("[selected]");

    private By vipStatusSelectorRaw = By.cssSelector("[checked]");

    private By vipInputSelectorRaw = By.cssSelector("input");

    private By dropdownSelectorRaw = By.cssSelector("select");

    @Step("Check User Table open")
    @Then("I am on User Table page")
    public void checkUserTablePageOpen() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    @Step("Check User Table page url")
    @Then("User Table page is active")
    public void userTablePageIsActive() {
        assertEquals(getWebDriver().getCurrentUrl(), url);
    }

    @Step("Check User Table page layout")
    @Then("User Table's page interface contains correct elements")
    public void checkUserTablePageInterface() {
        userTable.shouldBe(Condition.visible);
        leftSection.shouldBe(Condition.visible);
        pagination.shouldBe(Condition.visible);
    }

    @Step("Check Users Table Columns")
    @When("I check Number and User columns of Users table")
    public void checkUserTableHeaders() {
        assertEquals(userTableHeaders.stream().map(WebElement::getText).collect(Collectors.toList()),
                Arrays.asList("Number", "Type", "User", "Desciption"));
    }

    @Step("Check User Table Description column")
    @When("I check Description columns of User table")
    public void checkDescriptionColumn() {
        userTableHeaders.get(3).shouldHave(Condition.text("Desciption"));
    }

    @Step("Check users in User Table")
    @Then("User table contains correct values for numbers and users")
    public void checkUsersInTable(DataTable table) {
        List<List<String>> actualUsers = getUsersFromWebTable();
        List<List<String>> expectedUsers = table.raw();

        assertEquals(actualUsers.size(), expectedUsers.size() - 1);
        for (int i = 1; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).get(0), actualUsers.get(i - 1).get(0));
            assertEquals(expectedUsers.get(i).get(1), actualUsers.get(i - 1).get(2));
        }
    }

    @Step("Check description in User Table")
    @Then("All cells of 'Description' column contain text")
    public void checkDescriptionInTable(DataTable table) {
        List<List<String>> actualUsers = getUsersFromWebTable();
        List<List<String>> expectedUsers = table.raw();

        assertEquals(actualUsers.size(), expectedUsers.size() - 1);
        for (int i = 1; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).get(0), actualUsers.get(i - 1).get(0));
            assertEquals(expectedUsers.get(i).get(1), actualUsers.get(i - 1).get(3));
        }
    }

    @Step("Check logs")
    @Then("'Log' section shows a log row in format: (.+): condition changed to (.+)")
    public void logSectionShowsLogRowForCondition(String fieldname, boolean status) {
        assertTrue(logs.getText().contains(fieldname + ": condition changed to " + String.valueOf(status)));
    }

    @Step("Open dropdown")
    @When("I click on dropdown in column Type for user (.+)")
    public void openDropdown(String user) {
        for (int i = 0; i < userTableRaws.size() - 1; i++) {
            if (getUserName(i).equals(user)) {
                openTypeDropdownForUser(i);
            }
        }
    }

    @Step("Check Dropdown values")
    @Then("Droplist contains values")
    public void checkDropdownValues(DataTable userData) {
        List<String> expectedTypes = userData.asList(String.class);
        assertEquals(typeDropdownValues.stream().map(option -> option.getText()).collect(Collectors.toList()),
                expectedTypes.subList(1, expectedTypes.size()));

    }

    @Step("Select Vip status")
    @When("I set \'vip\' status to (.+)")
    public void setVipStatusToUser(String user) {
        for (int i = 0; i < userTableRaws.size() - 1; i++) {
            if (getUserName(i).equals(user)) {
                setVipStatusToUser(i);
            }
        }
    }

    public int getUserNumber(int tableRaw) {
        return Integer.parseInt(userTableRaws.get(tableRaw + 1).find(userNameSelectorRaw).getText());
    }

    public String getUserDescription(int tableRaw) {
        return userTableRaws.get(tableRaw + 1).find(descriptionSelectionRaw).getText().replace("\n", " ");
    }

    public String getUserName(int tableRaw) {
        return userTableRaws.get(tableRaw + 1).find(usernameSelectorRaw).getText();
    }

    public String getUserType(int tableRaw) {
        if (userTableRaws.get(tableRaw + 1).find(userTypeSelectorRaw).isDisplayed()) {
            return userTableRaws.get(tableRaw + 1).find(userTypeSelectorRaw).getText();
        } else {
            return UserType.Admin.toString();
        }
    }

    public boolean getUserVipStatus(int tableRaw) {
        return userTableRaws.get(tableRaw + 1).find(vipStatusSelectorRaw).isDisplayed();
    }

    public void setVipStatusToUser(int tableRaw) {
        userTableRaws.get(tableRaw + 1).find(vipInputSelectorRaw).setSelected(true);
    }

    public void openTypeDropdownForUser(int tableRaw) {
        userTableRaws.get(tableRaw + 1).find(dropdownSelectorRaw).click();
    }

    public List<List<String>> getUsersFromWebTable() {
        List<List<String>> userData = new ArrayList<List<String>>();
        for (int i = 0; i < userTableRaws.size() - 1; i++) {
            List<String> user = Arrays.asList(String.valueOf(getUserNumber(i)), getUserType(i), getUserName(i), getUserDescription(i), String.valueOf(getUserVipStatus(i)));
            userData.add(user);
        }
        return userData;
    }
}
