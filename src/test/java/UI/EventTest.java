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

public class EventTest {

    private WebDriver driver ;
    private LoginPage loginPage;
    private EventPage eventPage;

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
        loginPage.loginAsValidUser("my.first.drive.bm@gmail.com", "A102030405060708090a$");
    }

    @Test
    public void testAddEvent() throws InterruptedException {
        eventPage.addNewEvent("Quick Check-in","Brief meeting with the client","50");
        //Verify that the success toast message appears
        boolean isSuccessMessageDisplayed = driver.findElement(eventPage.getToastSuccessMessage()).isDisplayed();
        // Assert that the success message is displayed, indicating the event was created
        assertTrue(isSuccessMessageDisplayed, "Event creation was successful");
    }

    @Test
    public void testExistingAddEvent() throws InterruptedException {

        eventPage.addExistEvent("Consultation","Consultation slots for clients","30");
        //Verify that the success toast message appears
        boolean isFailedMessageDisplayed = driver.findElement(eventPage.getToastFailedMessage()).isDisplayed();
        // Assert that the success message is displayed, indicating the event was created
        assertTrue(isFailedMessageDisplayed, "An event type with this URL already exists ");
    }


    @Test
    public void testPreviewEvent() throws InterruptedException {

        driver.get("https://203b-2a06-c701-78d7-6300-80d2-9ce-d324-253d.ngrok-free.app/baraah99/consultation");

        eventPage.previewAction();
        assertTrue(true);
    }



    @Test
    public void testDeleteEvent() throws InterruptedException {
        eventPage.deletingEvent();
        boolean isDeleteMessageDisplayed = driver.findElement(eventPage.getToastDeleteMessage()).isDisplayed();

        assertTrue(isDeleteMessageDisplayed, "Event Type Deleted Successfully");
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
