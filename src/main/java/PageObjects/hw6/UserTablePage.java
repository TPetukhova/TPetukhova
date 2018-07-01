package PageObjects.hw6;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.hw6.UserType;
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

    private final String title = "User Table";
    private final String url = "https://epam.github.io/JDI/user-table.html";

    private SelenideElement userTable = $("#user-table");

    private SelenideElement leftSection = $(".info-panel-header");

    private SelenideElement logs = $(".logs");

    private SelenideElement pagination = $(".uui-pagination");

    private List<SelenideElement> userTableHeaders = $$("#user-table th");

    private List<SelenideElement> userTableRaws = $$("#user-table tbody tr");

    private List<SelenideElement> typeDropdownValues = $$("#user-table tbody tr:nth-child(2) option");

    @Then("I am on User Table page")
    public void iAmOnUserTablePage() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    @Then("User Table page is active")
    public void userTablePageIsActive() {
        assertEquals(getWebDriver().getCurrentUrl(), url);
    }

    @Then("User Table's page interface contains correct elements")
    public void userTablePageInterfaceContainsCorrectElements() {
        assertTrue(userTable.isDisplayed());
        assertTrue(leftSection.isDisplayed());
        assertTrue(pagination.isDisplayed());
        checkUsersTableHeaders();
    }

    public void checkUsersTableHeaders() {
        assertEquals(userTableHeaders.stream().map(WebElement::getText).collect(Collectors.toList()),
                Arrays.asList("Number", "Type", "User", "Desciption"));

    }


    @When("I check Number and User columns of Users table")
    public void iCheckNumberAndUserColumnsOfUserTable() {
        System.out.println("when I check table....");
    }

    @Then("User table contains correct values for numbers and users")
    public void userTableContainsCorrectValuesForNumbersAndUsers(DataTable table) {
        List<List<String>> actualUsers = getUsersFromTable();
        List<List<String>> expectedUsers = table.raw();

        assertEquals(actualUsers.size(), expectedUsers.size() - 1);
        for (int i = 1; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).get(0), actualUsers.get(i - 1).get(0));
            assertEquals(expectedUsers.get(i).get(1), actualUsers.get(i - 1).get(2));
        }


    }

    @When("I check Description columns of User table")
    public void iCheckDescriptionColumnOfUserTable() {
        System.out.println("when I check description....");
    }

    @Then("All cells of 'Description' column contain text")
    public void allCellsOfDescriptionColumnContainText(DataTable table) {
        List<List<String>> actualUsers = getUsersFromTable();
        List<List<String>> expectedUsers = table.raw();

        assertEquals(actualUsers.size(), expectedUsers.size() - 1);
        for (int i = 1; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).get(0), actualUsers.get(i - 1).get(0));
            assertEquals(expectedUsers.get(i).get(1), actualUsers.get(i - 1).get(3));
        }


    }

    public int getUserNumber(int tableRaw) {
        return Integer.parseInt(userTableRaws.get(tableRaw + 1).$("td:first-child").getText());
    }

    public String getUserDescription(int tableRaw) {
        return userTableRaws.get(tableRaw + 1).$("span").getText().replace("\n"," ");
    }

    public String getUserName(int tableRaw) {
        return userTableRaws.get(tableRaw + 1).$("a").getText();
}

    public String getUserType(int tableRaw) {
        if (userTableRaws.get(tableRaw + 1).$("[selected]").isDisplayed()) {
            return userTableRaws.get(tableRaw + 1).$("[selected]").getText();
        }
        else {
            return UserType.Admin.toString();
        }

    }

    public boolean getUserVipStatus(int tableRaw) {
        return userTableRaws.get(tableRaw + 1).$("[checked]").isDisplayed();
    }

    public void setVipStatusToUser(int tableRaw) {
        userTableRaws.get(tableRaw + 1).$("input").setSelected(true);
    }

    public void openTypeDropdownForUser(int tableRaw) {
        userTableRaws.get(tableRaw + 1).$("select").click();
    }

    public List<List<String>> getUsersFromTable() {
        List<List<String>> userData = new ArrayList<List<String>>();
        for (int i = 0; i < userTableRaws.size() - 1; i++) {
            List<String> user = Arrays.asList(String.valueOf(getUserNumber(i)), getUserType(i), getUserName(i), getUserDescription(i), String.valueOf(getUserVipStatus(i)));
            userData.add(user);
        }
        return userData;
    }


    @When("I set \'vip\' status to (.+)")
    public void iSetVipStatusToUser(String user) {
        for (int i = 0; i < userTableRaws.size() - 1; i++) {
            if (getUserName(i).equals(user)) {
                setVipStatusToUser(i);
            }
        }
    }

    @Then("'Log' section shows a log row in format: (.+): condition changed to (.+)")
    public void logSectionShowsLogRowForCondition(String fieldname, boolean status) {
        assertTrue(logs.getText().contains(fieldname + ": condition changed to " + String.valueOf(status)));
    }

    @When("I click on dropdown in column Type for user (.+)")
    public void iClickOnDropdownInColumnTypeForUser(String user) {
        for (int i = 0; i < userTableRaws.size() - 1; i++) {
            if (getUserName(i).equals(user)) {
                openTypeDropdownForUser(i);
            }
        }
    }

    @Then("Droplist contains values")
    public void droplistContainValues(DataTable userData) {
        List<String> expectedTypes = userData.asList(String.class);
        for (String item : expectedTypes) {
            System.out.println(item);
        }
                assertEquals(typeDropdownValues.stream().map(option -> option.getText()).collect(Collectors.toList()),
                        expectedTypes.subList(1,expectedTypes.size()-1));

    }
}
