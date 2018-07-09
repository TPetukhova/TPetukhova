package pageObjects.hw4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.hw4.Colors;
import enums.hw4.Elements;
import enums.hw4.Metals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DifferentElementsPage {

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
