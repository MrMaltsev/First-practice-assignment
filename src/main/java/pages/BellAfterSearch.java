package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BellAfterSearch extends BellBeforeSearch{

    private List<WebElement> results;

    private WebDriverWait wait;

    public BellAfterSearch(WebDriver chromeDriver) {
        super(chromeDriver);
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(120));
    }

    public List<WebElement> getResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class, 'table-vakansii')]//tbody//td[contains(@headers, 'view-title-table-column--2')]")));
        results = chromeDriver.findElements(By.xpath("//table[contains(@class, 'table-vakansii')]//tbody//td[contains(@headers, 'view-title-table-column--2')]"));
        return results;
    }

}
