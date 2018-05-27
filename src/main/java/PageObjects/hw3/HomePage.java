package PageObjects.hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = ".profile-photo span")
    private WebElement username;

    @FindBy(css = ".uui-navigation.nav > li > a")
    private List<WebElement> menuItems;

    @FindBy(css =".benefit .icons-benefit")
    private List<WebElement> images;

    @FindBy(css = ".benefit .benefit-txt")
    private List<WebElement> textItems;

    @FindBy(name = "main-title")
    private WebElement shortHeader;

    @FindBy(name = "jdi-text")
    private WebElement longHeader;

    @FindBy(css = ".main-content a")
    private WebElement subHeader;

    @FindBy(name = "navigation-sidebar")
    private WebElement leftSection;

    @FindBy(tagName = "footer")
    private WebElement footer;

    public void checkTitle() {
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void checkSingedInUsername(String name) {
        assertEquals(username.getText(),name);
    }

    public void checkMenuItemsText() {
        assertEquals(menuItems.stream().map(item -> item.getText()).collect(Collectors.toList()),
                Arrays.asList("HOME", "CONTACT FORM", "SERVICE","METALS & COLORS"));
    }

    public void checkImagesDisplayed() {
        assertEquals(images.stream().filter(i -> i.isDisplayed()).count(),4);
    }

    public void checkTextItems() {
        assertEquals(textItems.stream().map(item -> item.getText()).collect(Collectors.toList()),
                Arrays.asList("To include good practices\n" + "and ideas from successful\n" +
                                "EPAM project", "To be flexible and\n" + "customizable","To be multiplatform",
                        "Already have good base\n" + "(about 20 internal and\n" +
                                "some external projects),\n" + "wish to get more…") );
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

    public void checkSubHeaderText() {
        assertEquals(subHeader.getText(), "JDI GITHUB");
    }

    public void checkSubHeaderLink() {
        assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");
    }

    public void checkLeftSectionShown() {
        assertTrue(leftSection.isDisplayed());
    }

    public void checkFooterShown() {
        assertTrue(footer.isDisplayed());
    }

    public void closePage() {
        driver.close();
    }
}
