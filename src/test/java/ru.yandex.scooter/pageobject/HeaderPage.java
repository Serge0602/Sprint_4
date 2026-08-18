package ru.yandex.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {

    private final WebDriver driver;

    private final By samokatButton = By.xpath("//img[@alt='Scooter']");
    private final By yandexButton = By.xpath("//img[@alt='Yandex']");
    private final String samokatHomeUrl = "https://qa-scooter.praktikum-services.ru/";
    private final String yandexHomeUrl = "https://ya.ru/";

    private final By orderStatusButton = By.xpath("//button[@class='Header_Link__1TAG7']");
    private final By orderNumberInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    private final By goButton =  By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");
    private final By imageNotFound = By.xpath("//img[@alt='Not found']");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSamokatButton() {
        driver.findElement(samokatButton).click();
    }

    public void clickYandexButton() {
        driver.findElement(yandexButton).click();
    }

    public boolean isSamokatMainPage() {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlToBe(samokatHomeUrl));
    }

    public boolean isYandexMainPageNewWindow() {
        String currentWindow = driver.getWindowHandle();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            if (!currentWindow.equals(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlToBe(yandexHomeUrl));
    }

    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    public void inputNumberOfOrder(String number) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(orderNumberInput));

        driver.findElement(orderNumberInput).sendKeys(number);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(goButton)).click();
    }

    public boolean isNotFoundPage() {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(imageNotFound)).isDisplayed();
    }

}
