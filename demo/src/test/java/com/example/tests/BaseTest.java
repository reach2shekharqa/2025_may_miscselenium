package com.example.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.example.factory.DriverFactory;
import com.example.page.PageManager;

public abstract class BaseTest {

    protected BaseTest() {

    }

    protected WebDriver driver;
    protected PageManager pageManager;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        pageManager = new PageManager(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
