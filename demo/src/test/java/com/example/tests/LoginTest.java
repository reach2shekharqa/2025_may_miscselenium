package com.example.tests;

import org.junit.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(enabled = false)
    public void testValidLogin() {

        pageManager.getLoginPage().enterUsername("standard_user");
        pageManager.getLoginPage().enterPassword("secret_sauce");
        pageManager.getLoginPage().clickLogin();
        // Add assertions here
    }

    @Test(enabled = false)

    public void testvalidLoginUsingForm() {

        // pageManager.getLoginPage().loginUsingYaml("logindata.yml", "login");

    }

    @Test
    public void testvalidLoginUsingForm1() {

        pageManager.getLoginPage().fillForm("login.yml", "login");
        pageManager.getLoginPage().pageLoaded();
        pageManager.getLoginPage().fillForm("accountInfo.yml", "accountInfo");
        String actualresult = pageManager.getLoginPage().isAccountCreated();

        Assert.assertTrue(actualresult.contains("Welcome"));

    }
}
