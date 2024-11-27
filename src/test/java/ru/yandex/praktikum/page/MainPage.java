package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;
    //Locator. Cookie
    private final By cookieAcceptButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");
    //Locator. Accordeon
    private By faqAccordionItemHeading;
    private By faqAccordionItemPanel;
    private By faqAccordionItemPanelText;
    //Locator. To order
    private final By moveToOrderUpperButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    private final By moveToOrderLowerButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    //Constructor
    public MainPage(WebDriver driver, String accordionItemId){
        this.driver = driver;
        this.faqAccordionItemHeading = By.xpath(".//div[@id='"+ accordionItemId +"']");
        this.faqAccordionItemPanel = By.xpath(".//div[@aria-labelledby='"+ accordionItemId +"']");
        this.faqAccordionItemPanelText = By.xpath(".//div[@aria-labelledby='"+ accordionItemId +"']/p");
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Method. Cookie
    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }

    //Method. Accordion
    public void clickFaqAccordionItemHeading() {
        WebElement element = driver.findElement(faqAccordionItemHeading);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(faqAccordionItemHeading));
        new Actions(driver).moveToElement(element).click().perform();
    }

    public WebElement getFaqAccordionItemPanel() {
        return driver.findElement(faqAccordionItemPanel);
    }

    public WebElement getFaqAccordionItemPanelText() {
        return driver.findElement(faqAccordionItemPanelText);
    }

    //Method. Move to placing order
    public void clickOrderUpperButton() {
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(moveToOrderUpperButton));
        driver.findElement(moveToOrderUpperButton).click();
    }

    public void clickOrderLowerButton() {
        WebElement element = driver.findElement(moveToOrderLowerButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(moveToOrderLowerButton));
        driver.findElement(moveToOrderLowerButton).click();
    }
}
