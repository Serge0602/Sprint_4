package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class DriverFactory extends ExternalResource {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        if("firefox".equalsIgnoreCase(System.getProperty("browser")))
            startFirefox();
        else
            startChrome();
    }

    public void startFirefox() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Override
    protected void before() {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }
}