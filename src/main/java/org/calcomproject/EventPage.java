package org.calcomproject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class EventPage {

    private WebDriver driver;

    // adding new event
    private By newEventButton = By.cssSelector("button[data-testid='new-event-type']");
    private By quickChatInput = By.cssSelector("input[data-testid='event-type-quick-chat']");
    private By editorParagraph = By.cssSelector(".editor-input");
    private By durationField = By.cssSelector("input[data-testid='undefined-input'][name='length']");
    private By continueButton = By.cssSelector("button[type='submit']");
    private By toastSuccessMessage = By.cssSelector("p[data-testid='toast-success']");
    private By toastFailedMessage = By.cssSelector( "p[data-testid='toast-error']");


    // deleting event
    private By eventTypeOptionsButton = By.cssSelector("[data-testid^='event-type-options']");
    private By dropDownButton = By.cssSelector("div[data-radix-popper-content-wrapper]");
    private By deleteBtn = By.xpath("//button[contains(@class, 'hover:bg-error') and div[text()='Delete']]");
    private By confirmationDeleteButton =By.cssSelector("button[data-testid='dialog-confirmation']");
    private By toastDeleteMessage = By.cssSelector("p[data-testid='toast-success']");

    // preview and select time event
    private By dayButton = By.xpath("//button[@data-testid='day' and text()='21']");
    private By timeButton = By.xpath("//button[@data-testid='time' and @data-disabled='false']");
    private By confirmBookButton = By.cssSelector("[data-testid='confirm-book-button']");
    private By headline = By.id("modal-headline");
    private By bookingbtn = By.cssSelector("[data-testid='back-to-bookings']");
    private By svgIcon = By.xpath("//button[@data-testid='incrementMonth']");
    private By nameField = By.name("name");
    private By emailField = By.id("email");

    // Constructor
    public EventPage( WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(40));
        System.out.print(this.driver.getTitle());
    }

    public void clickNewButton() {
        // Use an explicit wait to wait for the 'newEventButton' to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement addNewBtn = wait.until(ExpectedConditions.elementToBeClickable(newEventButton));

        // Click the 'newEventButton'
        addNewBtn.click();
    }

    public void enterQuickChat(String text) {
        // Use an explicit wait to wait for the 'quickChatInput' to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement quickChatField = wait.until(ExpectedConditions.visibilityOfElementLocated(quickChatInput));

        // Clear and enter the text into the 'quickChatInput' field
        quickChatField.clear();
        quickChatField.sendKeys(text);
    }

    public void enterDescription(String text) {
        // Wait until the 'editorParagraph' element is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(editorParagraph));

        // Click, clear, and enter the text into the 'editorParagraph' field
        descriptionField.click();
        descriptionField.clear();
        descriptionField.sendKeys(text);
    }

    public void setDuration(String text) {
        // Wait until the 'durationField' element is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement durationSetField = wait.until(ExpectedConditions.elementToBeClickable(durationField));

        // Clear and enter the text into the 'durationField'
        durationSetField.clear();
        durationSetField.sendKeys(text);
    }

    public void clickContinueButton() {
        // Wait until the 'continueButton' element is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));

        // Click the 'continueButton'
        continueBtn.click();
    }

    public void addNewEvent(String quickChat, String description, String duration) {
        // Click the "New Event" button
        clickNewButton();
        // Enter Quick Chat, Description, and Duration
        enterQuickChat(quickChat);
        enterDescription(description);
        setDuration(duration);
        // Click Continue Button
        clickContinueButton();
        // Explicit wait to ensure that the success toast message is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));  // Set wait time as needed
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccessMessage));
    }

    public void addExistEvent(String quickChat, String description, String duration) {
        // Click the "New Event" button
        clickNewButton();
        // Enter Quick Chat, Description, and Duration
        enterQuickChat(quickChat);
        enterDescription(description);
        setDuration(duration);
        // Click Continue Button
        clickContinueButton();
        // Explicit wait to ensure that the success toast message is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));  // Set wait time as needed
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastFailedMessage));
    }

    public void deletingEvent() {

        // Define a wait object for dynamic elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Step 1: Locate and click the options button
        WebElement optionsButton = wait.until(ExpectedConditions.elementToBeClickable(eventTypeOptionsButton) );
        optionsButton.click();
        // Step 2: Wait for the dropdown menu to appear
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownButton));
        // Step 3: Locate the "Delete" button
        WebElement deleteButton = wait.until( ExpectedConditions.elementToBeClickable(deleteBtn));
        // Scroll into view if necessary
        (( JavascriptExecutor ) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        // Click the "Delete" button
        deleteButton.click();
        // Step 4: Locate the "Yes, delete" button
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(confirmationDeleteButton));
        // Step 5: Click the "Yes, delete" button
        confirmDeleteButton.click();


    }


    public void clickDayButton() {
        // Use an explicit wait to wait for the day button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement dayBtn = wait.until(ExpectedConditions.elementToBeClickable(dayButton));
        // Click the day button
        dayBtn.click();
    }

    public void clickTimeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        List<WebElement> timeButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(timeButton));
        WebElement timeBtn = timeButtons.get(4); // Use the first button or the desired index
        timeBtn.click();
    }


    public void clickConfirmBookButton() {
        // Use an explicit wait to wait for the button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement bookConfirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmBookButton));
        // Click the button
        bookConfirmBtn.click();
    }

    public void clickSvgRightButton() {
        // Use an explicit wait to wait for the button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement svgBtn = wait.until(ExpectedConditions.elementToBeClickable(svgIcon));
        // Click the button
        svgBtn.click();
    }

    public void backToBookingsLinkButton(){
        // Wait for the "Back to bookings" link to be present and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement backToBookingsLink = wait.until(ExpectedConditions.elementToBeClickable(bookingbtn));
        // Click the link
        backToBookingsLink.click();
    }

    public void enterName(String text) {
        // Use an explicit wait to wait for the 'quickChatInput' to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement nameText = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));

        // Clear and enter the text into the 'quickChatInput' field
        nameText.clear();
        nameText.sendKeys(text);
    }
    public void enterMail(String text) {
        // Use an explicit wait to wait for the 'quickChatInput' to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement emailText = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));

        // Clear and enter the text into the 'quickChatInput' field
        emailText.clear();
        emailText.sendKeys(text);
    }

    public void previewAction() throws InterruptedException {
        clickSvgRightButton();
        clickDayButton();
        clickTimeButton();
        enterName("Rami Miari");
        enterMail("my.first.drive.bm@gmail.com");
        clickConfirmBookButton();
        //backToBookingsLinkButton();
    }

    public boolean isLoggedInSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        try {
            // Wait for the title to be "Event Types | Cal.com"
            wait.until(ExpectedConditions.titleIs("Event Types | Cal.com"));
            return true; // Title matches, login was successful
        } catch (Exception e) {
            System.out.println("Login failed or timeout waiting for the title. Current title: " + driver.getTitle());
            return false; // Title didn't match, login failed
        }
    }

    public By getToastSuccessMessage() {
        return toastSuccessMessage;
    }

    public By getToastFailedMessage() {
        return toastFailedMessage;
    }

    public By getToastDeleteMessage( ) {
        return toastDeleteMessage;
    }
}
