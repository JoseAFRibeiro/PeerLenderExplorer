package com.jose.selenium.mintos;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jose.UI.CommandLineManager;
import com.jose.html.TableHandeler;
import com.jose.selenium.WebsiteDriver;

public class Mintos extends WebsiteDriver {
    
    private String loginButtonID = "header-login-button";
    private WebElement email;
    private WebElement password;
    private WebDriverWait w;
    private CommandLineManager cmmanger; 
    private WebElement submitButton;
    WebElement twoFA;

    public Mintos(){
        super();
        cmmanger = new CommandLineManager();
        website = "https://www.mintos.com/en/";
        driver.get(website);
        w = new WebDriverWait(driver, Duration.ofSeconds(5l));
        w.until(ExpectedConditions.elementToBeClickable(By.id(loginButtonID)));
    }

    public void login(String email, String password){

        driver.findElement(By.id(loginButtonID)).click();
        w.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/main/div/div/div/form/div[2]/div[2]/button")));

        try {
            //pre 2FA
            this.email = driver.findElement(By.cssSelector("input[id='login-username']"));
            this.password = driver.findElement(By.cssSelector("input[id='login-password']"));
            
            this.email.sendKeys(email);
            this.password.sendKeys(password);

            driver.findElement(By.cssSelector("button[data-testid='login-button']")).click();

            if(driver.findElement(By.className("rc-imageselect")) != null)
                throw new Exception("Captcha found!");

            
            //2FA
            w = new WebDriverWait(driver, Duration.ofSeconds(60l));
            w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/main/div/div/div/form/div/div[1]/div[2]/div/div[1]/div/div/input")));
            twoFA = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div/div/form/div/div[1]/div[2]/div/div[1]/div/div/input"));
            submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div/div/form/div/div[1]/div[2]/div/div[2]/button"));

            twoFA.sendKeys(cmmanger.code2FA());
            submitButton.click();
            
            w.until(ExpectedConditions.titleContains("Overview"));            

        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }
        
    }

    public void getData()
    {
        final String tablePath = "/html/body/div[1]/div/main/div[1]/div/div[2]/article/section/div/main/table"; 
        WebElement resultTable;
            /*TODO: identificar dados para recolher do mintos
             *adicionar espera
             * implmentar escala temporal de dados a recolher
             * criar objeto de dados 
             * armazenar dados como xslt out outro formato (talvez sql?)
             */

            driver.get("https://www.mintos.com/en/account-statement/");
            resultTable = driver.findElement(By.xpath(tablePath));
            TableHandeler h = new TableHandeler(driver, tablePath);
            h.getTableList();
    }
}
