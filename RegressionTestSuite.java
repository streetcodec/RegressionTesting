package com.example.tests;

import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegressionTestSuite extends BaseTest {

    @Test(priority = 1)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPassword");
        loginPage.clickLogin();

        Assert.assertTrue(homePage.isLogoutButtonDisplayed(), "Logout button should be displayed on successful login.");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickLogin();

        String errorMessage = driver.findElement(By.id("error")).getText();
        Assert.assertEquals(errorMessage, "Invalid credentials", "Expected error message not displayed for invalid login.");
    }

    @Test(priority = 3)
    public void testPageNavigation() {
        // Assuming there's a navigation feature that takes you to a profile page
        driver.findElement(By.id("profileLink")).click();
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Profile Page", "Navigation to profile page failed.");
    }

    @Test(priority = 4)
    public void testLogoutFunctionality() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPassword");
        loginPage.clickLogin();

        Assert.assertTrue(homePage.isLogoutButtonDisplayed(), "Logout button should be displayed after login.");
        
        driver.findElement(By.id("logout")).click();
        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed(), "Login button should be displayed after logout.");
    }
}
