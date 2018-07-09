package hw5;

import pageObjects.hw5.DifferentElementsPage;
import pageObjects.hw5.HomePage;
import base.hw5.SelenideTestBase;
import enums.hw5.Colors;
import enums.hw5.Elements;
import enums.hw5.Metals;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.hw5.AllureAttachmentListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static enums.hw5.Users.PITER_CHAILOVSKII;

@Story("Buttons on Different Elements Page")
@Feature("Different Elements Page")
@Listeners({AllureAttachmentListener.class})
public class DifferentElementsPageLayout extends SelenideTestBase {

    private HomePage homePage;
    private DifferentElementsPage differentElementsPage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
    }

    @Test
    public void HomePageAndDifferentElementsLayoutsTest() {
        // 1 - open web page in browser and check page title
        homePage.openPage();
        homePage.checkPageTitle();

        // 2 - perform login
        homePage.login(PITER_CHAILOVSKII);
        homePage.checkSingedInUsername(PITER_CHAILOVSKII);

        // 3 - check images and text on the page
        homePage.checkImagesDisplayed();
        homePage.checkTextItems();
        homePage.checkLongHeaderText();
        homePage.checkShortHeaderText();

        // 4 - check Service menu on the header
        homePage.checkServiceMenuItems();

        // 5 - check Service menu on the Left Panel
        homePage.checkServiceMenuItemsOnLeftSection();

        // 6 - open Different Elements page
        differentElementsPage = homePage.openDifferentElementsPage();
        differentElementsPage.checkPageTitle();

        // 7 - check Checkboxes
        differentElementsPage.checkCheckboxes();

        // 8 - check radio buttons
        differentElementsPage.checkRadioButtons();

        // 9 - check dropdown values and buttons
        differentElementsPage.checkDropdown();
        differentElementsPage.checkButtons();

        // 10 - check right and left sections
        differentElementsPage.checkRightSection();
        differentElementsPage.checkLeftSection();

        // 11 - select Water and Wind checkboxes, check logs
        differentElementsPage.setCheckboxState(Elements.Water, true);
        differentElementsPage.setCheckboxState(Elements.Wind, true);
        differentElementsPage.checkLogs(Elements.Water, true);
        differentElementsPage.checkLogs(Elements.Wind, true);

        // 12 - select radio buttons, check logs
        differentElementsPage.selectRadioButton(Metals.Selen);
        differentElementsPage.checkLogs(Metals.Selen);

        // 13 - select value in dropdown
        differentElementsPage.selectDropdown(Colors.Yellow);
        differentElementsPage.checkLogs(Colors.Yellow);

        // 14 - undo selection of checkboxes, check logs
        differentElementsPage.setCheckboxState(Elements.Water, false);
        differentElementsPage.setCheckboxState(Elements.Wind, false);
        differentElementsPage.checkLogs(Elements.Water, false);
        differentElementsPage.checkLogs(Elements.Wind, false);

    }

}
