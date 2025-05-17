package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.utilities.YamlUtil;

public class LoginPage extends BasePage {
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    protected LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void fillForm(String yamlFile, String section) {
        YamlUtil yamlUtil = new YamlUtil(yamlFile);
        yamlUtil.performActionsWithValidation(this.driver, section);

    }

    public void pageLoaded() {
        waitForDOMLoaded();
    }

    public String isAccountCreated() {

        String actualResult = driver.findElement(By.cssSelector("#result")).getText();
        System.out.println(actualResult);
        return actualResult;

    }
}
