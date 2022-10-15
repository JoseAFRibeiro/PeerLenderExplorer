package com.jose.selenium;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.jose.networking.ProxyManager;

public abstract class WebsiteDriver {
    
    public WebDriver driver;
    public String website;
    public Proxy proxy;


    public WebsiteDriver(){
        System.setProperty("webdriver.chrome.driver", ".\\src\\res\\chromedriver.exe");
        driver = new ChromeDriver();
        proxySetup();
    }

    public abstract void login(String email, String password);

    public abstract void getData();

    public void proxySetup(){
        ProxyManager manager = new ProxyManager();
        String address = manager.useProxy();
        proxy = new Proxy();
        proxy.setHttpProxy(address);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
    }


    protected void finalize(){
        driver.quit();
    }
}
