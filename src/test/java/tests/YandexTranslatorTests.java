package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.pageObjects.YandexTranslatorPageObject;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class YandexTranslatorTests {

    @BeforeAll
    static void BeforeAll() {
        baseUrl = "https://translate.yandex.ru";
        browserSize = "1920x1280";
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
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
    }

    //Не работает, нужно попробовать пройти авторизацию
    @ValueSource(strings = {"https://www.reddit.com/", "https://steamspy.com/"})
    @ParameterizedTest
    void translationSite(String site) {
        step("Открываем главную страницу", () -> {
            open(baseUrl);
        });

        step("Переходим на вкладку сайты", () -> {
            $$(".header-nav-link").findBy(Condition.text("Сайты")).click();
        });

        step("Вводим урл сайта" + site + "и переходим к переводу", () -> {
            $(byId("urlInput-input")).setValue(site);
            $(".icn icn_arrow-short-forward").click();
        });

    }

}
