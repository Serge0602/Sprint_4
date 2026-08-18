package ru.yandex.scooter.pageobjects;

import org.openqa.selenium.*;

public class MainPage {

    private final WebDriver driver;

    private final By topOrderButton = By.xpath("//div[contains(@class, 'Header_Nav__AGCXC')]//button[text()='Заказать']");
    private final By botOrderButton = By.xpath("//div[contains(@class, 'Home_FinishButton__1_cWm')]//button[text()='Заказать']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBotOrderButton() {
        WebElement element = driver.findElement(botOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

}