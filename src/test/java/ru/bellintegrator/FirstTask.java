package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.BellAfterSearch;
import pages.BellBeforeSearch;

import static helpers.Properties.testsProperties;

public class FirstTask extends BaseTests {

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @CsvSource({"Разработчик"})
    public void testPO(String word) {
        chromeDriver.get(testsProperties.bellIntegratorUrl());
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find(word);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        Assertions.assertTrue(bellAfterSearch.getResults().stream().anyMatch(x -> x.getText().contains(word)),
                "Статьи содержащие " + word + " не найдены для поискового слова " + word);

        /*TODO: Исправить Assertions (24 строка), потому что сейчас в stream
            проверяется есть ли хотяб один элемент, а нужно их вывести.
            Плюс в CsvSource() добавить каких-нибудь слов для поиска и проверить, чтобы с ними
            все ок было
         */
    }
}