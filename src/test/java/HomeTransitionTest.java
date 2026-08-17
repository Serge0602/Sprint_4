import factory.DriverFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.HeaderPage;
import pageObjects.MainPage;

public class HomeTransitionTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void successfulSamokatHomeTransitionTest() {

        WebDriver driver = driverFactory.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        mainPage.clickTopOrderButton();
        headerPage.clickSamokatButton();
        Assert.assertTrue("Не произошло перехода на главную страницу Самоката", headerPage.isSamokatMainPage());
    }

    @Test
    public void successfulYandexHomeTransitionTest() {

        WebDriver driver = driverFactory.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.clickYandexButton();
        Assert.assertTrue("Не произошло перехода на главную страницу Яндекса", headerPage.isYandexMainPageNewWindow());
    }
}
