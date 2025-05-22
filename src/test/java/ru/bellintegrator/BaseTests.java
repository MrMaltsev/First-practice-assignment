package ru.bellintegrator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static helpers.Properties.testsProperties;

public class BaseTests {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(testsProperties.defaultTimeout(), TimeUnit.SECONDS);
    }

    @AfterEach
    public void after(){
        chromeDriver.quit();
    }

}
