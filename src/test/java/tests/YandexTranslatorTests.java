package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.pageObjects.YandexTranslatorPageObject;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class YandexTranslatorTests {

    @BeforeAll
    static void BeforeAll() {
        baseUrl = "https://translate.yandex.ru";
        browserSize = "1920x1280";
    }


    @ParameterizedTest
    @CsvSource(value = {
            "Русский, Английский, Первый параметрезированный тест, The first parameterized test",
            "Вьетнамский , Гаитянский, Vinh quang đến Ukraine, Tout bèl pouvwa a nan Ikrèn"
    }, delimiter = ',')
    void translationTest(String inputLang, String outputLang, String text, String expectedText) {
        open(baseUrl);
        YandexTranslatorPageObject yandexTranslator = new YandexTranslatorPageObject();
        yandexTranslator.choiceInputLanguage(inputLang)
                .choiceOutputLanguage(outputLang)
                .inputText(text)
                .assertions(expectedText);
        closeWebDriver();
    }
}
