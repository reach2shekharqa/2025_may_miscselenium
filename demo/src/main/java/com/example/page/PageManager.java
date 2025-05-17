package com.example.page;

import org.openqa.selenium.WebDriver;

public class PageManager {
    private WebDriver driver;
    private LoginPage loginPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
}
