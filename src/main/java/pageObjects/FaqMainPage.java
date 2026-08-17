package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FaqMainPage {

    private final WebDriver driver;

    private final By[] faqQuestions = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7"),
    };

    private final By[] faqAnswers = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7"),
    };

    public FaqMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToQuestions(int index) {
        WebElement element = driver.findElement(faqQuestions[index]);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getQuestionText(int index) {
        return driver.findElement(faqQuestions[index]).getText();
    }

    public void clickQuestions(int index) {
        driver.findElement(faqQuestions[index]).click();
    }

    public String getAnswerText(int index) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(faqAnswers[index]));
        return driver.findElement(faqAnswers[index]).getText();
    }

}