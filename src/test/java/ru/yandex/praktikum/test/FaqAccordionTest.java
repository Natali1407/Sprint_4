package ru.yandex.praktikum.test;

import ru.yandex.praktikum.page.MainPage;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


@RunWith(Parameterized.class)
public class FaqAccordionTest {

    private WebDriver driver;
    private final String faqAccordionItemId;
    private final boolean faqAccordionItemPanelHiddenExpected; //true - element is hidden, false - element isn't hidden
    private final String faqAccordionItemPanelTextExpected;

    public FaqAccordionTest(String accordionItemId, boolean faqAccordionItemPanelHiddenExpected, String faqAccordionItemPanelText){
        this.faqAccordionItemId = accordionItemId;
        this.faqAccordionItemPanelHiddenExpected = faqAccordionItemPanelHiddenExpected;
        this.faqAccordionItemPanelTextExpected = faqAccordionItemPanelText;
    }

    @Parameterized.Parameters
    public static Object[][] getInputData() {
        return new Object[][] {
                {"accordion__heading-0", false, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-3", false, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void checkAccordionExpand() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objMainPage = new MainPage(driver, faqAccordionItemId);
        //Cookie
        objMainPage.clickCookieAcceptButton();
        //Go to FAQ accordeon's item and click
        objMainPage.clickFaqAccordionItemHeading();
        //Check panel is visible
        WebElement element = objMainPage.getFaqAccordionItemPanel();
        boolean visibleActual = Boolean.parseBoolean(element.getAttribute("hidden"));
        assertEquals(visibleActual, faqAccordionItemPanelHiddenExpected);
//        //Check panel text
        String textActual = objMainPage.getFaqAccordionItemPanelText().getText();
        MatcherAssert.assertThat(textActual, containsString(faqAccordionItemPanelTextExpected));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
