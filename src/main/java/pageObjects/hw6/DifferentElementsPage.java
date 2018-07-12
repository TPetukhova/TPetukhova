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
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DifferentElementsPage {

    //just test

    private final String title = "Different Element";
    private final String url = "https://epam.github.io/JDI/different-elements.html";

    @FindBy(css = ".label-checkbox")
    private List<SelenideElement> checkboxes;

    @FindBy(css = ".label-radio")
    private List<SelenideElement> radioButtons;

    @FindBy(css = "select[class='uui-form-element']")
    private SelenideElement dropdown;

    @FindBy(css = "select[class='uui-form-element'] option")
    private List<SelenideElement> dropdownOptions;

    @FindBy(css = "button[name='Default Button']")
    private SelenideElement defaultButton;

    @FindBy(css = "input.uui-button")
    private SelenideElement secondButton;

    @FindBy(css = "[name='log-sidebar']")
    private SelenideElement rightSection;

    @FindBy(css = "[name='navigation-sidebar']")
    private SelenideElement leftSection;

    @FindBy(css = ".logs")
    private SelenideElement logs;

    public DifferentElementsPage() {
        page(this);
    }

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
        assertEquals(checkboxes.stream().map(SelenideElement::getText).collect(Collectors.toList()),
                Stream.of(Elements.values()).map(Elements::toString).collect(Collectors.toList()));
        assertEquals(radioButtons.stream().map(SelenideElement::getText).collect(Collectors.toList()),
                Stream.of(Metals.values()).map(Metals::toString).collect(Collectors.toList()));
        dropdown.shouldBe(Condition.visible);
        assertEquals(dropdownOptions.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(Colors.values()).map(Colors::toString).collect(Collectors.toList()));
        defaultButton.shouldBe(Condition.visible);
        secondButton.shouldBe(Condition.visible);
    }

    @Step("Check Different Elements Side Sections")
    @Then("Different Elements page contains side sections")
    public void checkSideSections() {
        leftSection.shouldBe(Condition.visible);
        rightSection.shouldBe(Condition.visible);
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
        for (SelenideElement radio : radioButtons) {
            if (radio.getText().contains(metal.toString())) {
                radio.$("input").click();
                break;
            }
        }
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

    public void setCheckboxState(Elements element, boolean state) {
        for (SelenideElement checkbox : checkboxes) {
            if (checkbox.getText().contains(element.toString())) {
                checkbox.$("input").setSelected(state);
                assertEquals(checkbox.$("input").is(Condition.checked), state);
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
