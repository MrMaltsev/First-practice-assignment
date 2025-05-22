package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BellBeforeSearch {

    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    public BellBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        this.searchField = chromeDriver.findElement(By.xpath("//input[@id='edit-title--2' and @type='text']"));
        this.searchButton = chromeDriver.findElement(By.xpath("//div[contains(@class, 'col-md-1 align-self-end')]//button[contains(@class, 'btn btn-danger')]"));
    }

    public void find(String word) {
        searchField.click();
        searchField.sendKeys(word);
        searchButton.click();
    }
}