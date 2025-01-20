package UI;

import org.calcomproject.EventPage;
import org.calcomproject.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.calcomproject.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {

    private WebDriver driver ;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("https://203b-2a06-c701-78d7-6300-80d2-9ce-d324-253d.ngrok-free.app");
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch ( TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        // Perform login
        EventPage eventPage = loginPage.loginAsValidUser("my.first.drive.bm@gmail.com", "A102030405060708090a$");
        assertTrue(eventPage.isLoggedInSuccessfully());
    }

    @Test
    public void testInvalidLogin() {
        // Perform invalid login
        loginPage.loginAsInvalidUser("notvalidemail@gmail.com", "A102030405060708090a$");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until( ExpectedConditions.presenceOfElementLocated( By.xpath("//h3[text()='Email or password is incorrect.']")));
        assertTrue( errorMessage.isDisplayed(), "Error message displayed" );
    }


    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        // Always ensure the browser is closed after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
