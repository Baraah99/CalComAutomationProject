package org.calcomproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingPage {

    private WebDriver driver;

    private By cancelBtn = By.cssSelector("a[data-testid='cancel']");
    private By bookingBtn = By.cssSelector("a[data-test-id='bookings']");
    private By bookingLabel = By.cssSelector("[data-testid='bookings-test']");
    private By confirmCancel = By.cssSelector("button[data-testid='confirm_cancel']");
    private By backToBookingbtn = By.cssSelector("[data-testid='back-to-bookings']");


    // Constructor
    public BookingPage( WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(40));
    }


    public void clickCancelEventButton() {
        // Use an explicit wait to wait for the 'newEventButton' to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement cancelClickBtn = wait.until( ExpectedConditions.elementToBeClickable(cancelBtn));

        // Click the 'newEventButton'
        cancelClickBtn.click();
    }
    public void clickBookingButton() {
        // Use an explicit wait to wait for the 'newEventButton' to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement bookinglClickBtn = wait.until( ExpectedConditions.elementToBeClickable(bookingBtn));

        // Click the 'newEventButton'
        bookinglClickBtn.click();
    }

    public void confirmdeleteBooking() {
        // Use an explicit wait to wait for the 'newEventButton' to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement confirmDeleteBookinglClick = wait.until( ExpectedConditions.elementToBeClickable(confirmCancel));

        // Click the 'newEventButton'
        confirmDeleteBookinglClick.click();
    }

    public void enterBooking() {
        // Use an explicit wait to wait for the 'newEventButton' to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement bookingLabelClick = wait.until( ExpectedConditions.elementToBeClickable(bookingLabel));

        // Click the 'newEventButton'
        bookingLabelClick.click();
    }

    public void backToBookingButton() {
        // Use an explicit wait to wait for the 'newEventButton' to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement backToBookinglClickBtn = wait.until( ExpectedConditions.elementToBeClickable(backToBookingbtn));

        // Click the 'newEventButton'
        backToBookinglClickBtn.click();
    }


    public void deleteBookingAction(){
        enterBooking();
        clickCancelEventButton();
        confirmdeleteBooking();
        backToBookingButton();

    }





}
