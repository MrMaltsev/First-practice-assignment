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
import pages.BellBeforeFilterSearch;
import pages.BellAfterFilterSearch;

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

    @Feature("Проверка фильтров")
    @DisplayName("Проверка вакансий по фильтрам")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @CsvSource({"Архитекторы", "Дизайнеры"})
    public void testFilterSearch(String specialization) {
        chromeDriver.get(testsProperties.bellIntegratorUrl());
        BellBeforeFilterSearch filterPage = new BellBeforeFilterSearch(chromeDriver);
        filterPage.selectSpecialization(specialization);
        BellAfterFilterSearch afterFilterSearch = new BellAfterFilterSearch(chromeDriver);
        List<WebElement> results = afterFilterSearch.getResults();
        lastTestResults = results.stream().map(WebElement::getText).collect(Collectors.toList());
        lastTestWord = specialization;
    }


    @Feature("Проверка фильтров")
    @DisplayName("Проверка вакансий по фильтрам")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @CsvSource({"Беларусь", "Екатеринбург"})
    public void testFilterSearchTwo(String location) {
        chromeDriver.get(testsProperties.bellIntegratorUrl());
        BellBeforeFilterSearch filterPage = new BellBeforeFilterSearch(chromeDriver);
        filterPage.selectLocation(location);
        BellAfterFilterSearch afterFilterSearch = new BellAfterFilterSearch(chromeDriver);
        List<WebElement> results = afterFilterSearch.getResults();
        lastTestResults = results.stream().map(WebElement::getText).collect(Collectors.toList());
        lastTestWord = location;
    }


    @Feature("Проверка горячих вакансий")
    @DisplayName("Проверка вакансий по фильтру 'Горячие вакансии'")
    @ParameterizedTest(name="{displayName}")
    @CsvSource({"true"})
    public void testHotVacanciesSearch(String hotVacancies) {
        chromeDriver.get(testsProperties.bellIntegratorUrl());
        BellBeforeFilterSearch filterPage = new BellBeforeFilterSearch(chromeDriver);
        filterPage.selectHotVacancies();
        System.out.println("После применения фильтра 'Горячие вакансии', текущий URL: " + chromeDriver.getCurrentUrl());
        BellAfterFilterSearch afterFilterSearch = new BellAfterFilterSearch(chromeDriver);
        List<WebElement> results = afterFilterSearch.getResults();
        lastTestResults = results.stream().map(WebElement::getText).collect(Collectors.toList());
        lastTestWord = "Горячие вакансии";
    }


    @Feature("Проверка вакансий только удаленно")
    @DisplayName("Проверка вакансий по фильтру 'Только удаленно'")
    @ParameterizedTest(name="{displayName}")
    @CsvSource({"true"})
    public void testOnlyDistantSearch(String OnlyDistant) {
        chromeDriver.get(testsProperties.bellIntegratorUrl());
        BellBeforeFilterSearch filterPage = new BellBeforeFilterSearch(chromeDriver);
        filterPage.selectOnlyDistant();
        System.out.println("После применения фильтра 'Горячие вакансии', текущий URL: " + chromeDriver.getCurrentUrl());
        BellAfterFilterSearch afterFilterSearch = new BellAfterFilterSearch(chromeDriver);
        List<WebElement> results = afterFilterSearch.getResults();
        lastTestResults = results.stream().map(WebElement::getText).collect(Collectors.toList());
        lastTestWord = "Только удаленно";
    }

}
