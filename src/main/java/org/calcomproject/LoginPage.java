package org.calcomproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class LoginPage {

    private WebDriver driver;

    // Locators for elements on the login page
    private By emailInputField = By.id("email");
    private By passwordInputField = By.id("password");
    private By signInButton = By.cssSelector("button[type='submit']");
    private By errorMessageBy = By.xpath("//h3[text()='Email or password is incorrect.']");
    private By EventTypePage= By.xpath("//h3[text()='Event Types']");// Locator for error message


    // Constructor
    public LoginPage( WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(40));
        if (!Objects.requireNonNull( driver.getTitle( ) ).contains("Login")) {
            throw new IllegalStateException("This is not the Login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    // Method to enter email
    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailInputField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Method to enter password
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordInputField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Method to click the sign-in button
    public void clickSignInButton() {
        WebElement signInBtn = driver.findElement(signInButton);
        signInBtn.click();
    }


    public EventPage loginAsValidUser( String userName, String password) {
        enterEmail(userName);
        enterPassword(password);
        clickSignInButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/event-types"));
        wait.until(ExpectedConditions.presenceOfElementLocated(EventTypePage));
        return new EventPage(driver);
    }

    public LoginPage loginAsInvalidUser(String userName, String password) {
        enterEmail(userName);
        enterPassword(password);
        clickSignInButton();
        return this;
    }

    // Check if the error message is displayed
    public boolean isErrorMessageDisplayed() {
        // Wait for the error message to appear and return true if found
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageBy)).isDisplayed();
    }
}
