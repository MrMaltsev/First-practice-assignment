package ru.bellintegrator;

import java.util.List;
import java.util.stream.Collectors;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import pages.BellAfterSearch;
import pages.BellBeforeSearch;

import static helpers.Properties.testsProperties;

public class FirstTask extends BaseTests {

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @CsvSource({"Разработчик", "Директор", "Аналитик"})
    public void testPO(String word) {
        chromeDriver.get(testsProperties.bellIntegratorUrl());
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find(word);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        List<String> matchingResults = bellAfterSearch.getResults().stream()
                .filter(x -> x.getText().toLowerCase().contains(word.toLowerCase()))
                .map(WebElement::getText)
                .collect(Collectors.toList());
        lastTestResults = matchingResults;
        lastTestWord = word;

    }
}