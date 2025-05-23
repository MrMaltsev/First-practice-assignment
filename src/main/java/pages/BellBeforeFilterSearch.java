package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BellBeforeFilterSearch {

    protected WebDriver chromeDriver;
    protected WebElement specializationDropdown;
    protected WebElement hotVacancies;
    protected WebDriverWait wait;
    protected WebElement searchButton;

    public BellBeforeFilterSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.specializationDropdown = chromeDriver.findElement(By.xpath("//select[@id='edit-field-specializaciya-target-id--2']"));
        this.hotVacancies = chromeDriver.findElement(By.xpath("//input[@class='form-check-input' and @id='edit-field-goryachee-value--2']"));
        this.searchButton = chromeDriver.findElement(By.xpath("//div[contains(@class, 'col-md-1 align-self-end')]//button[contains(@class, 'btn btn-danger')]"));
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    public void selectSpecialization(String specialization) {
        Select select = new Select(specializationDropdown);
        select.selectByVisibleText(specialization); // Выбираем первый пункт или указанный текст
        searchButton.click(); // Нажимаем кнопку поиска
    }

    public void selectHotVacancies() {
        hotVacancies.click(); // Нажимаем на чекбокс "Горячие вакансии"
        searchButton.click(); // Нажимаем кнопку поиска
    }
}
