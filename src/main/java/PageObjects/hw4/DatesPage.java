package PageObjects.hw4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class DatesPage {

    private final String title = "Dates";
    private final String url = "https://epam.github.io/JDI/dates.html";

    private List<SelenideElement> sliders = $$(".ui-slider-handle");

    private List<SelenideElement> sliderValues = $$(".ui-slider-handle span");

    public void checkPageTitle() {
        assertEquals(getWebDriver().getTitle(), title);
    }

 /*   public void checkLogs(int newValue, String toFrom) {
        //assertTrue(logs.getText().contains("Range 2(" + toFrom + "):" + newValue + " link clicked"));
        System.out.println(logs.getText());
    }
*/

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
    }

    public void checkLeftSliderValue(int value) {
        assertEquals(Integer.parseInt(sliderValues.get(0).getText()), value);
    }

    public void checkRightSliderValue(int value) {
        assertEquals(Integer.parseInt(sliderValues.get(1).getText()), value);
    }
}
