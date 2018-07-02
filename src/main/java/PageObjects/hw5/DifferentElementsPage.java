package PageObjects.hw5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.hw5.Colors;
import enums.hw5.Elements;
import enums.hw5.Metals;
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

    @Step("Verify Page Title")
    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    @Step("Verify Checkboxes")
    public void checkCheckboxes() {
        assertEquals(checkboxes.stream().map(SelenideElement::getText).collect(Collectors.toList()),
                Stream.of(Elements.values()).map(Elements::toString).collect(Collectors.toList()));
    }

    @Step("Verify Radio Buttons")
    public void checkRadioButtons() {
        assertEquals(radioButtons.stream().map(SelenideElement::getText).collect(Collectors.toList()),
                Stream.of(Metals.values()).map(Metals::toString).collect(Collectors.toList()));
    }

    @Step("Verify Dropdowns")
    public void checkDropdown() {
        dropdown.shouldBe(Condition.visible);
        assertEquals(dropdownOptions.stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(Colors.values()).map(Colors::toString).collect(Collectors.toList()));
    }

    @Step("Check Buttons")
    public void checkButtons() {
        defaultButton.shouldBe(Condition.visible);
        secondButton.shouldBe(Condition.visible);
    }

    @Step("Check Side Sections")
    public void checkLeftSection() {
        leftSection.shouldBe(Condition.visible);
    }

    @Step("Check Side Sections")
    public void checkRightSection() {
        rightSection.shouldBe(Condition.visible);
    }

    @Step("Change Checkbox State")
    public void setCheckboxState(Elements element, boolean state) {
        for (SelenideElement checkbox : checkboxes) {
            if (checkbox.getText().contains(element.toString())) {
                checkbox.$("input").setSelected(state);
                assertEquals(checkbox.$("input").is(Condition.checked), state);
                break;
            }
        }
    }

    @Step("Change Radio Button State")
    public void selectRadioButton(Metals metal) {
        for (SelenideElement radio : radioButtons) {
            if (radio.getText().contains(metal.toString())) {
                radio.$("input").click();
                break;
            }
        }
    }

    @Step("Verify Logs")
    public void checkLogs(Elements element, boolean condition) {
        assertTrue(logs.getText().toLowerCase().contains(element.toString().toLowerCase() + ": condition changed to " + condition));
    }

    @Step("Verify Logs")
    public void checkLogs(Object object) {
        if (object instanceof Metals) {
            assertTrue(logs.getText().toLowerCase().contains("metal: value changed to " + object.toString().toLowerCase()));
        } else if (object instanceof Colors) {
            assertTrue(logs.getText().toLowerCase().contains("colors: value changed to " + object.toString().toLowerCase()));
        }
    }

    @Step("Select Dropdown")
    public void selectDropdown(Colors color) {
        dropdown.selectOption(color.toString());
    }

}
