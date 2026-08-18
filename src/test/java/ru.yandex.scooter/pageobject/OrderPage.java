package ru.yandex.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private final WebDriver driver;

    // Поле "Для кого самокат"
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By stationDrop = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Ошибки полей заказа
    private final By nameError = By.xpath("//div[contains(@class, 'Input_ErrorMessage__3HvIb') and contains(text(), 'Введите корректное имя')]");
    private final By surnameError = By.xpath("//div[contains(@class, 'Input_ErrorMessage__3HvIb') and contains(text(), 'Введите корректную фамилию')]");
    private final By addressError = By.xpath("//div[contains(@class, 'Input_ErrorMessage__3HvIb') and contains(text(), 'Введите корректный адрес')]");
    private final By stationError = By.xpath("//div[contains(@class, 'Order_MetroError__1BtZb') and contains(text(), 'Выберите станцию')]");
    private final By phoneError = By.xpath("//div[contains(@class, 'Input_ErrorMessage__3HvIb') and contains(text(), 'Введите корректный номер')]");

    // Поле "Про аренду"
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDrop = By.className("Dropdown-placeholder");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");

    // Всплывающее подтверждение
    private final By orderConfirmationButton = By.xpath("//button[text()='Да']");

    // Заказ оформлен
    private final By orderSuccess = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение персональных данных
    public void fillPersonalData(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stationDrop).sendKeys(metroStation);
        driver.findElement(By.xpath("//div[text()='" + metroStation + "']")).click();
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Заполнение данных аренды
    public void fillRentData(String date, String rentalPeriod, String color, String comment) {
        driver.findElement(dateInput).sendKeys(date, Keys.ENTER);
        driver.findElement(rentalPeriodDrop).click();
        driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='" + rentalPeriod + "']")).click();

        if (color != null && !color.isEmpty()) {
            driver.findElement(By.xpath("//label[contains(text(), '" + color + "')]/input")).click();
        }

        if (comment != null) {
            driver.findElement(commentInput).sendKeys(comment);
        }

        // Кнопка "Заказать"
        driver.findElement(orderButton).click();

        // Подтверждение кнопка "Да"
        driver.findElement(orderConfirmationButton).click();
    }

    // Проверка появления окна успешного заказа
    public boolean isOrderCreated() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccess));
        return driver.findElement(orderSuccess).isDisplayed();
    }

    // Заполнение некорректных данных
    public void fillFieldsWithIncorrectData(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stationDrop).sendKeys(metroStation);
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public String getNameErrorText() {
        return driver.findElement(nameError).getText();
    }

    public String getSurnameErrorText() {
        return driver.findElement(surnameError).getText();
    }

    public String getAddressErrorText() {
        return driver.findElement(addressError).getText();
    }

    public String getStationDropErrorText() {
        return driver.findElement(stationError).getText();
    }

    public String getPhoneErrorText() {
        return driver.findElement(phoneError).getText();
    }
}