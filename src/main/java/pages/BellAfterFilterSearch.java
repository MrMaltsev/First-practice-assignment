package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BellAfterFilterSearch extends BellBeforeFilterSearch {

    private List<WebElement> results;
    private WebDriverWait wait;

    public BellAfterFilterSearch(WebDriver chromeDriver) {
        super(chromeDriver);
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
    }

    public List<WebElement> getResults() {
        try {
            WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//table[contains(@class, 'table-vakansii')]")));
            results = chromeDriver.findElements(
                    By.xpath("//table[contains(@class, 'table-vakansii')]//tbody//td[contains(@headers, 'view-title-table-column--2')]"));
            if (results.isEmpty()) {
                System.out.println("Таблица найдена, но вакансий нет. Возвращаем пустой список.");
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Таблица не найдена или произошла ошибка: " + e.getMessage());
            System.out.println("Текущий HTML страницы: " + chromeDriver.getPageSource().substring(0, 1000));
            return new ArrayList<>();
        }
        return results;
    }
}