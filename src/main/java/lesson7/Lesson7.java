package lesson7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Lesson7 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL = "https://s1.demo.opensourcecms.com/s/44";


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // инициализация драйвера
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testSiteTest() {
        driver.get(URL);
        driver.switchTo().frame("preview-frame");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@id,'txtUsername')]")))).sendKeys("test");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@id,'txtPassword')]")))).sendKeys("test");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@id,'btnLogin')]")))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@id,'spanMessage')]")))).getAttribute("Invalid credentials");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@id,'btnLogin')]")))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@id,'spanMessage')]")))).getAttribute("Username cannot be empty");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@id,'txtUsername')]")))).sendKeys("test");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@id,'btnLogin')]")))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@id,'spanMessage')]")))).getAttribute("Password cannot be empty");
        driver.switchTo().parentFrame();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(.,'Remove Frame')]")))).click();
        List<WebElement> frame = driver.findElements(By.xpath("//iframe[@name='preview-frame']"));
        if (frame.size() == 0) {
            System.out.println("Success");
        }
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
