package org.calcomproject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;


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
    }

    public void clickNewButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement addNewBtn = wait.until(ExpectedConditions.elementToBeClickable(newEventButton));
        addNewBtn.click();
    }

    public void enterQuickChat(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement quickChatField = wait.until(ExpectedConditions.visibilityOfElementLocated(quickChatInput));
        quickChatField.clear();
        quickChatField.sendKeys(text);
    }

    public void enterDescription(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(editorParagraph));
        descriptionField.click();
        descriptionField.clear();
        descriptionField.sendKeys(text);
    }

    public void setDuration(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement durationSetField = wait.until(ExpectedConditions.elementToBeClickable(durationField));
        durationSetField.clear();
        durationSetField.sendKeys(text);
    }

    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();
    }

    public void addNewEvent(String quickChat, String description, String duration) throws InterruptedException {
        clickNewButton();
        Thread.sleep( 1000 );
        enterQuickChat(quickChat);
        Thread.sleep( 1000 );
        enterDescription(description);
        Thread.sleep( 1000 );
        setDuration(duration);
        Thread.sleep( 1000 );
        clickContinueButton();
        Thread.sleep( 1000 );
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));  // Set wait time as needed
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccessMessage));
    }

    public void addExistEvent(String quickChat, String description, String duration) {
        clickNewButton();
        enterQuickChat(quickChat);
        enterDescription(description);
        setDuration(duration);
        clickContinueButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));  // Set wait time as needed
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastFailedMessage));
    }

    public void deletingEvent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement optionsButton = wait.until(ExpectedConditions.elementToBeClickable(eventTypeOptionsButton) );
        optionsButton.click();
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownButton));
        WebElement deleteButton = wait.until( ExpectedConditions.elementToBeClickable(deleteBtn));
        (( JavascriptExecutor ) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        deleteButton.click();
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(confirmationDeleteButton));
        confirmDeleteButton.click();
    }


    public void clickDayButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement dayBtn = wait.until(ExpectedConditions.elementToBeClickable(dayButton));
        dayBtn.click();
    }

    public void clickTimeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        List<WebElement> timeButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(timeButton));
        WebElement timeBtn = timeButtons.get(4);
        timeBtn.click();
    }

    public void clickConfirmBookButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement bookConfirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmBookButton));
        bookConfirmBtn.click();
    }

    public void clickSvgRightButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement svgBtn = wait.until(ExpectedConditions.elementToBeClickable(svgIcon));
        svgBtn.click();
    }

    public void backToBookingsLinkButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement backToBookingsLink = wait.until(ExpectedConditions.elementToBeClickable(bookingbtn));
        backToBookingsLink.click();
    }

    public void enterName(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement nameText = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameText.clear();
        nameText.sendKeys(text);
    }

    public void enterMail(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement emailText = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailText.clear();
        emailText.sendKeys(text);
    }

    public void previewAction() throws InterruptedException {
        clickSvgRightButton();
        clickDayButton();
        clickTimeButton();
        enterName("Rami Miari");
        enterMail("rami.lec305@gmail.com");
        clickConfirmBookButton();
    }

    public boolean isLoggedInSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        try {
            wait.until(ExpectedConditions.titleIs("Event Types | Cal.com"));
            return true;
        } catch (Exception e) {
            System.out.println("Login failed or timeout waiting for the title. Current title: " + driver.getTitle());
            return false;
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
