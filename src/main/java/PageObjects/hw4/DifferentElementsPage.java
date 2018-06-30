package PageObjects.hw4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.hw4.Colors;
import enums.hw4.Elements;
import enums.hw4.Metals;
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

    private SelenideElement defaultButton = $("button[name='Default Button']");

    private SelenideElement secondButton = $("input.uui-button");

    private SelenideElement rightSection = $("[name='log-sidebar']");

    private SelenideElement leftSection = $("[name='navigation-sidebar']");

    private SelenideElement logs = $(".logs");

    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
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
        assertTrue(dropdown.isDisplayed());
        assertEquals(dropdown.$$("option").stream().map(WebElement::getText).collect(Collectors.toList()),
                Stream.of(Colors.values()).map(Colors::toString).collect(Collectors.toList()));
    }

    public void checkButtons() {
        assertTrue(defaultButton.isDisplayed());
        assertTrue(secondButton.isDisplayed());

    }

    public void checkLeftSection() {
        assertTrue(leftSection.isDisplayed());
    }

    public void checkRightSection() {
        assertTrue(rightSection.isDisplayed());
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

    public void checkLogs(Elements element, boolean condition) {
            assertTrue(logs.getText().toLowerCase().contains(element.toString().toLowerCase() + ": condition changed to " + condition));
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
