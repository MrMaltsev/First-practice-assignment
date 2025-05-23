package ru.bellintegrator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static helpers.Properties.testsProperties;

public class BaseTests {

    protected WebDriver chromeDriver;
    protected List<String> lastTestResults = new ArrayList<>(); // Храним результаты последнего теста
    protected String lastTestWord = ""; // Храним слово последнего теста

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(testsProperties.defaultTimeout(), TimeUnit.SECONDS);
    }

    @AfterEach
    public void after() {
        // Логирование результатов
        System.out.println("Результаты поиска для '" + lastTestWord + "': " +
                (lastTestResults.isEmpty() ? "нет результатов" : String.join(", ", lastTestResults)));
        if (lastTestResults.isEmpty()) {
            System.out.println("Предупреждение: Статьи содержащие '" + lastTestWord + "' не найдены.");
        }
        if (chromeDriver != null) {
            chromeDriver.quit();
        }
    }

}
