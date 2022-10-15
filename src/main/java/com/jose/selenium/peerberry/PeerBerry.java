package com.jose.selenium.peerberry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;
import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jose.UI.CommandLineManager;
import com.jose.selenium.WebsiteDriver;

public class PeerBerry extends WebsiteDriver{

    private CommandLineManager cmmanger; 
    private WebDriverWait w;
    private List<WebElement> twoFAField;
    private String loginButtonXpath = "/html/body/div[1]/div[1]/div/div/div[2]/a";
    
    public PeerBerry(){
        super();
        cmmanger = new CommandLineManager();
        website = "https://peerberry.com/";
        driver.get(website);
        w = new WebDriverWait(driver, Duration.ofSeconds(5l));
        w.until(ExpectedConditions.elementToBeClickable(By.xpath(loginButtonXpath)));
        twoFAField = new ArrayList<>();  
    }


    @Override
    public void login(String email, String password) {
        
        final String logInXpath = "/html/body/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[2]/form/div[3]/button";
        final String emailFieldXpath = "/html/body/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div/input";
        final String pwFieldXpath = "/html/body/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div[2]/form/div[2]/div[1]/div/input";
        final String submitButtonXpath = "/html/body/div[1]/div/div/div/div/form/div[3]/button[1]";
        driver.findElement(By.xpath(loginButtonXpath)).click();
        w.until(ExpectedConditions.elementToBeClickable(By.xpath(logInXpath)));

        try {
            
            WebElement passWordField, emailField, loginButton, submitButton;
                      
            emailField = driver.findElement(By.xpath(emailFieldXpath));
            passWordField = driver.findElement(By.xpath(pwFieldXpath));
            loginButton = driver.findElement(By.xpath(logInXpath));

            emailField.sendKeys(email);
            passWordField.sendKeys(password);
            loginButton.click();

            w.until(ExpectedConditions.elementToBeClickable(By.xpath(submitButtonXpath)));
            submitButton = driver.findElement(By.xpath(submitButtonXpath));
            twoFAField = driver.findElements(By.tagName("input"));

            String twoFA = cmmanger.code2FA();
            
            int j = 0;
            for(WebElement i : twoFAField)
            {
                i.sendKeys(String.valueOf(twoFA.charAt(j)));
                j++;
            }

            submitButton.click();

        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }


        
    }


    @Override
    public void getData() {
        // TODO Auto-generated method stub
        
    }
}
