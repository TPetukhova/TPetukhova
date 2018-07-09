package pageObjects.hw6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.hw6.Colors;
import enums.hw6.Elements;
import enums.hw6.Metals;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DifferentElementsPage {

    private final String title = "Different Element";
    private final String url = "https://epam.github.io/JDI/different-elements.html";

    private List<SelenideElement> checkboxes = $$(".label-checkbox");

    private List<SelenideElement> radioButtons = $$(".label-radio");

    private SelenideElement dropdown = $("select[class='uui-form-element']");

    private List<SelenideElement> dropdownOptions = dropdown.$$("option");

    private SelenideElement defaultButton = $("button[name='Default Button']");

    private SelenideElement secondButton = $("input.uui-button");

    private SelenideElement rightSection = $("[name='log-sidebar']");

    private SelenideElement leftSection = $("[name='navigation-sidebar']");

    private SelenideElement logs = $(".logs");

    @Step("Check Different Elements Page open")
    @Then("Different Elements page is displayed")
    public void checkPageIsDisplayed() {
        assertEquals(getWebDriver().getCurrentUrl(), url);
    }

    @Step("Check Title of Different Elements page")
    @Then("Page Title is Different Element")
    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    @Step("Check Different Elements page layout")
    @Then("Different Elements page contains correct elements")
    public void checkPageInterface() {
        checkCheckboxes();
        checkRadioButtons();
        checkDropdown();
        checkButtons();
    }

    @Step("Check Different Elements Side Sections")
    @Then("Different Elements page contains side sections")
    public void checkSideSections() {
        checkLeftSection();
        checkRightSection();
    }

    @Step("Select checkbox")
    @When("I select (.+) checkbox")
    public void selectCheckbox(Elements element) {
        setCheckboxState(element, true);
    }

    @Step("Unselect checkbox")
    @When("I unselect (.+) checkbox")
    public void unselectCheckbox(Elements element) {
        setCheckboxState(element, false);
    }

    @Step("Check logs")
    @Then("Logs contain entry: Element (.+) set to (.+)")
    public void checkLogs(Elements element, boolean condition) {
        assertTrue(logs.getText().toLowerCase().contains(element.toString().toLowerCase() + ": condition changed to " + condition));
    }

    @Step("Select radiobutton")
    @When("Radiobutton (.+) is selected")
    public void selectRadiobutton(Metals metal) {
        selectRadioButton(metal);
    }

    @Step("Check logs")
    @Then("Logs contain entry: Radiobutton (.+) is selected")
    public void checkLogsRadiobutton(Metals metal) {
        checkLogs(metal);
    }

    @Step("Select Dropdown value")
    @When("Dropdown (.+) is chosen")
    public void selectDropdownValue(Colors color) {
        selectDropdown(color);
    }

    @Step("Check logs")
    @Then("Logs contain entry: (.+) is chosen")
    public void checkLogsDropdown(Colors color) {
        checkLogs(color);
    }

    public void checkCheckboxes() {
        assertEquals(checkboxes.stream().map(SelenideElement::getText).collect(Collectors.toList()),
                Stream.of(Elements.values()).map(Elements::toString).collect(Collectors.toList()));
    }

    public void checkRadioButtons() {
        assertEquals(radioButtons.stream().map(SelenideElement::getText).collect(Collectors.toList()),
                Stream.of(Metals.values()).map(Metals::toString).collect(Collectors.toList()));
    }

    public void checkDropdown() {
        dropdown.shouldBe(Condition.visible);
        assertEquals(dropdownOptions.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(Colors.values()).map(Colors::toString).collect(Collectors.toList()));
    }

    public void checkButtons() {
        defaultButton.shouldBe(Condition.visible);
        secondButton.shouldBe(Condition.visible);
    }

    public void checkLeftSection() {
        leftSection.shouldBe(Condition.visible);
    }

    public void checkRightSection() {
        rightSection.shouldBe(Condition.visible);
    }

    public void setCheckboxState(Elements element, boolean state) {
        for (SelenideElement checkbox : checkboxes) {
            if (checkbox.getText().contains(element.toString())) {
                checkbox.$("input").setSelected(state);
                assertEquals(checkbox.$("input").is(Condition.checked), state);
                break;
            }
        }
    }

    public void selectRadioButton(Metals metal) {
        for (SelenideElement radio : radioButtons) {
            if (radio.getText().contains(metal.toString())) {
                radio.$("input").click();
                break;
            }
        }
    }

    public void checkLogs(Object object) {
        if (object instanceof Metals) {
            assertTrue(logs.getText().toLowerCase().contains("metal: value changed to " + object.toString().toLowerCase()));
        } else if (object instanceof Colors) {
            assertTrue(logs.getText().toLowerCase().contains("colors: value changed to " + object.toString().toLowerCase()));
        }
    }

    public void selectDropdown(Colors color) {
        dropdown.selectOption(color.toString());
    }

}
