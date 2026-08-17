import factory.DriverFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.OrderPage;

public class ErrorFieldTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void errorFieldTest() {

        WebDriver driver = driverFactory.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.clickTopOrderButton();
        orderPage.fillFieldsWithIncorrectData(
                "John",
                "Doe",
                "Va",
                "",
                "012"
        );

        orderPage.clickNextButton();

        Assert.assertEquals("Ошибка под полем 'Имя' не совпадает", "Введите корректное имя", orderPage.getNameErrorText());
        Assert.assertEquals("Ошибка под полем 'Фамилия' не совпадает", "Введите корректную фамилию", orderPage.getSurnameErrorText());
        Assert.assertEquals("Ошибка под полем 'Адрес' не совпадает", "Введите корректный адрес", orderPage.getAddressErrorText());
        Assert.assertEquals("Ошибка под полем 'Станция метро' не совпадает", "Выберите станцию", orderPage.getStationDropErrorText());
        Assert.assertEquals("Ошибка под полем 'Телефон' не совпадает", "Введите корректный номер", orderPage.getPhoneErrorText());
    }
}
