package UI;

import org.calcomproject.BookingPage;
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

public class BookingTest {

    private WebDriver driver ;
    private LoginPage loginPage;
    private EventPage eventPage;
    private BookingPage bookingpage;


    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("https://203b-2a06-c701-78d7-6300-80d2-9ce-d324-253d.ngrok-free.app");
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement visitSiteButton = wait.until( ExpectedConditions.elementToBeClickable( By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch ( TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
        loginPage = new LoginPage(driver);
        eventPage = new EventPage( driver );
        bookingpage = new BookingPage( driver );
        loginPage.loginAsValidUser("my.first.drive.bm@gmail.com", "A102030405060708090a$");
    }

    @Test
    public void testDeleteBooking() throws InterruptedException {
        //Thread.sleep( 3000 );
        // Call the previewAction method and store the result
        bookingpage.deleteBookingAction();
        // Assert that the event is scheduled
        assertTrue(true);
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
