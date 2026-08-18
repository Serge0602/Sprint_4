import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import ru.yandex.scooter.pageobjects.MainPage;
import ru.yandex.scooter.pageobjects.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String buttonPosition;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String buttonPosition, String name, String surname, String address, String metroStation,
                     String phone, String date, String rentalPeriod, String color, String comment) {
        this.buttonPosition = buttonPosition;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Заказ через кнопку: {0}, Имя: {1}")
    public static Object[][] getTestData() {
        return new Object[][] {
                { "top", "Серж", "Сосенко", "Москва, Хамовники, 1-й Обыденский переулок, 5", "Кропоткинская",
                        "+79593332221", "18.08.2026", "сутки", "чёрный жемчуг", ""},

                { "bottom", "Дарья", "Лил", "Москва, Таганский, Гончарная улица, 2", "Таганская", "+76952223334",
                        "20.08.2026", "двое суток", "серая безысходность", "Оставить у двери"}
        };
    }

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void successfulOrderCreationTest() {
        WebDriver driver = driverFactory.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        if ("top".equalsIgnoreCase(buttonPosition)) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBotOrderButton();
        }

        orderPage.fillPersonalData(name, surname, address, metroStation, phone);
        orderPage.fillRentData(date, rentalPeriod, color, comment);

        Assert.assertTrue("Окно не отобразилось", orderPage.isOrderCreated());
    }

}