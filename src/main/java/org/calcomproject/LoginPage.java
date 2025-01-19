package org.calcomproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Objects;

public class LoginPage {

    private WebDriver driver;

    // Locators for elements on the login page
    private By emailInputField = By.id("email");
    private By passwordInputField = By.id("password");
    private By signInButton = By.cssSelector("button[type='submit']");
    private By errorMessageBy = By.xpath("//h3[text()='Email or password is incorrect.']"); // Locator for error message


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
        // Navigate to the Event Types page on successful login
        return new EventPage(driver);
    }

    public LoginPage loginAsInvalidUser(String userName, String password) {
        enterEmail(userName);
        enterPassword(password);
        clickSignInButton();
        return this;
    }
}
