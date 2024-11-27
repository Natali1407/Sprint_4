package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    private final WebDriver driver;
    //Locator. About user
    private final By orderAboutUserLabel = By.xpath(".//div[text() = 'Для кого самокат']");
    private final By orderUserNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By orderUserSurnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By orderAddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By orderSubwayStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By orderUserPhoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By orderMoveToRentingInfoButton = By.xpath(".//button[text() = 'Далее']");
    //Locator. About renting
    private final By orderAboutRentingLabel = By.xpath(".//div[text() = 'Про аренду']");
    private final By orderRentalStartDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By orderRentalPeriodField = By.className("Dropdown-placeholder");
    private final By orderScooterColorField = By.className("Checkbox_Input__14A2w");
    private final By orderCommentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderMoveToConfirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    //Locator. Confirmation order
    private final By orderConfirmationLabel = By.xpath(".//div[text() = 'Хотите оформить заказ?']");
    private final By orderConfirmationButton = By.xpath(".//button[text() = 'Да']");
    //Locator. Order confirmed
    private final By orderOrderConfirmedLabel = By.className("Order_ModalHeader__3FDaJ");

    //Constructor
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    //Method. About user
    public By getOrderAboutUserLabel() {
        return orderAboutUserLabel;
    }
    public By getOrderRentalPeriodValue(String value) {
        return By.xpath(".//div[text() = '"+ value +"']");
    }
    public void setOrderUserName(String userName) {
        driver.findElement(orderUserNameField).sendKeys(userName);
    }
    public void setOrderUserSurname(String userSurname) {
        driver.findElement(orderUserSurnameField).sendKeys(userSurname);
    }
    public void setOrderAddress(String address) {
        driver.findElement(orderAddressField).sendKeys(address);
    }
    public void setOrderSubwayStation(String subwayStation) {
        driver.findElement(orderSubwayStationField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(orderSubwayStationField).sendKeys(subwayStation);
        driver.findElement(orderSubwayStationField).sendKeys(Keys.ENTER);
    }
    public void setOrderPhone(String phone) {
        driver.findElement(orderUserPhoneField).sendKeys(phone);
    }
    public void clickMoveToRentingInfoInOrderButton() {
        driver.findElement(orderMoveToRentingInfoButton).click();
    }

    //Method. About renting
    public By getOrderAboutRentingLabel() {
        return orderAboutRentingLabel;
    }
    public void setOrderStartDate(String startDate) {
        driver.findElement(orderRentalStartDateField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(orderRentalStartDateField).sendKeys(startDate);
        driver.findElement(orderRentalStartDateField).sendKeys(Keys.ENTER);
    }
    public void setOrderRentalPeriod(String value) {
        driver.findElement(orderRentalPeriodField).click();
        driver.findElement(getOrderRentalPeriodValue(value)).click();
    }
    public void setOrderScooterColor(String scooterColor) {
        driver.findElement(orderScooterColorField).sendKeys(scooterColor);
    }
    public void setOrderComment(String comment) {
        driver.findElement(orderCommentField).sendKeys(comment);
    }
    public void clickMoveToConfirmationOrderButton() {
        driver.findElement(orderMoveToConfirmOrderButton).click();
    }

    //Method. Confirmation order
    public By getOrderConfirmationLabel() {
        return orderConfirmationLabel;
    }
    public void clickOrderConfirmationButton() {
        driver.findElement(orderConfirmationButton).click();
    }

    //Method. Order confirmed
    public By getOrderConfirmedLabel() {
        return orderOrderConfirmedLabel;
    }

    //Method. Complex
    public void addUserInfoInOrder(String userName, String userSurname, String address, String subwayStation, String phone) {
        setOrderUserName(userName);
        setOrderUserSurname(userSurname);
        setOrderAddress(address);
        setOrderSubwayStation(subwayStation);
        setOrderPhone(phone);
        clickMoveToRentingInfoInOrderButton();
    }

    public void addRentingInfoInOrder(String startDate, String rentalPeriod, String scooterColor, String comment) {
        setOrderStartDate(startDate);
        setOrderRentalPeriod(rentalPeriod);
        setOrderScooterColor(scooterColor);
        setOrderComment(comment);
        clickMoveToConfirmationOrderButton();
    }
}
