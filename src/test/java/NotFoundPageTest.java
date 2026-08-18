import factory.DriverFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.scooter.pageobjects.HeaderPage;

public class NotFoundPageTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void NotFoundPageTest() {

        WebDriver driver = driverFactory.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.clickOrderStatusButton();
        headerPage.inputNumberOfOrder("111");

        Assert.assertTrue("Картинка 'Такого заказа нет' не отображается", headerPage.isNotFoundPage());
    }
}
