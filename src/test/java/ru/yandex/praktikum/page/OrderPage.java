package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private final WebDriver driver;
    private final By orderUserNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By orderUserSurnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By orderAddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By orderSubwayStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By orderUserPhoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By orderMoveToRentingInfoButton = By.xpath(".//button[text() = 'Далее']");
    private final By orderAboutUserLabel = By.xpath(".//div[text() = 'Для кого самокат']");
    private final By orderAboutRentingLabel = By.xpath(".//div[text() = 'Про аренду']");
    private final By orderRentalStartDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By orderCommentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderMoveToConfirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    private final By orderConfirmationLabel = By.xpath(".//div[text() = 'Хотите оформить заказ?']");
    private final By orderConfirmationButton = By.xpath(".//button[text() = 'Да']");
    private final By orderConfirmedLabel = By.className("Order_ModalHeader__3FDaJ");
    private final By subwayDropdownOption = By.className("select-search__option");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");





    public OrderPage(WebDriver driver) {
        this.driver = driver;
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
        WebElement stationInput = driver.findElement(orderSubwayStationField);
        stationInput.sendKeys(subwayStation);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(subwayDropdownOption));
        dropdownOption.click();
    }

    public void setOrderPhone(String phone) {
        driver.findElement(orderUserPhoneField).sendKeys(phone);
    }

    public void clickMoveToRentingInfoInOrderButton() {
        driver.findElement(orderMoveToRentingInfoButton).click();
    }

    public void addUserInfoInOrder(String userName, String userSurname, String address, String subwayStation, String phone) {
        setOrderUserName(userName);
        setOrderUserSurname(userSurname);
        setOrderAddress(address);
        setOrderSubwayStation(subwayStation);
        setOrderPhone(phone);
        clickMoveToRentingInfoInOrderButton();
    }

    public void waitForOrderAboutUserLabel() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderAboutUserLabel));
    }

    public void waitForOrderAboutRentingLabel() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderAboutRentingLabel));
    }

    public void setOrderStartDate(String startDate) {
        driver.findElement(orderRentalStartDateField).sendKeys(startDate);
        driver.findElement(orderRentalStartDateField).sendKeys(Keys.ENTER);
    }

    // Метод для выбора периода аренды
    public void setOrderRentalPeriod(String rentalPeriod) {
        WebElement dropdown = driver.findElement(rentalPeriodDropdown);
        dropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='Dropdown-menu']//div[text()='" + rentalPeriod + "']")));
        option.click();
    }

    public void setOrderComment(String comment) {
        driver.findElement(orderCommentField).sendKeys(comment);
    }

    public void clickMoveToConfirmationOrderButton() {
        driver.findElement(orderMoveToConfirmOrderButton).click();
    }

    public void addRentingInfoInOrder(String startDate, String rentalPeriod, String scooterColor, String comment) {
        try {
            setOrderStartDate(startDate);
            setOrderRentalPeriod(rentalPeriod);
            selectScooterColor(scooterColor);
            setOrderComment(comment);
            Thread.sleep(1000); // Пауза 2 секунды

            clickMoveToConfirmationOrderButton();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ошибка во время паузы в addRentingInfoInOrder", e);
        }
    }

    public void selectScooterColor(String color) {
        By colorLocator = By.xpath("//label[contains(text(), '" + color + "')]");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement colorCheckbox = wait.until(ExpectedConditions.elementToBeClickable(colorLocator));
        if (!colorCheckbox.isSelected()) {
            colorCheckbox.click();
        }
    }

    public void waitForOrderConfirmationLabel() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationLabel));
    }

    public void clickOrderConfirmationButton() {
        driver.findElement(orderConfirmationButton).click();
    }

    public boolean isOrderConfirmed() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmedLabel));
            return driver.findElement(orderConfirmedLabel).isDisplayed();
        } catch (Exception e) {
            return false; // Возвращает false, если элемент не найден или не отображён
        }
    }

}
