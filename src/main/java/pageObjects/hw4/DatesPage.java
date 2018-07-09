package pageObjects.hw4;

import com.codeborne.selenide.SelenideElement;
import enums.hw4.FromTo;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DatesPage {

    private final String title = "Dates";
    private final String url = "https://epam.github.io/JDI/dates.html";

    @FindBy(css = ".ui-slider-handle")
    private List<SelenideElement> sliders;

    @FindBy(css = ".ui-slider-handle span")
    private List<SelenideElement> sliderValues;

    @FindBy(css = ".logs")
    private SelenideElement logs;

    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
    }

    public void setLeftSlider(int value) {
        int currentValue = Integer.parseInt(sliderValues.get(0).getText());
        if (currentValue >= value) {
            for (int i = 0; i < (currentValue - value); i++) {
                sliders.get(0).sendKeys(Keys.ARROW_LEFT);
            }
        }
        if (currentValue < value) {
            for (int i = 0; i < (value - currentValue); i++) {
                sliders.get(0).sendKeys(Keys.ARROW_RIGHT);
            }
        }
        if (value != 100) {
            sliderValues.get(0).click();
        } else {
            sliderValues.get(1).click();
        }

    }

    public void setRightSlider(int value) {
        int currentValue = Integer.parseInt(sliderValues.get(1).getText());
        if (currentValue >= value) {
            for (int i = 0; i < (currentValue - value); i++) {
                sliders.get(1).sendKeys(Keys.ARROW_LEFT);
            }
        }
        if (currentValue < value) {
            for (int i = 0; i < (value - currentValue); i++) {
                sliders.get(1).sendKeys(Keys.ARROW_RIGHT);
            }
        }
        sliderValues.get(1).click();

    }

    public void checkLogs(FromTo slider, int value) {
        assertTrue(logs.getText().contains(" Range 2(" + slider + "):" + value + " link clicked"));
    }

}
