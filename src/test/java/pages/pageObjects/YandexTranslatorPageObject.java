package pages.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class YandexTranslatorPageObject {
    private final SelenideElement languageSelectionButton = $(By.id("srcLangButton"));
    private final SelenideElement languageOutputSelectionButton = $(By.id("dstLangButton"));
    private final SelenideElement textInputClick = $(".box-topLayers");
    private final SelenideElement textInputBox = $(byId("fakeArea"));
    private final SelenideElement textOutput = $(byId("translation"));
    private String text;

    public YandexTranslatorPageObject choiceInputLanguage(String inputLanguage) {
        languageSelectionButton.click();
        $(byText(inputLanguage)).click();
        return this;
    }

    public YandexTranslatorPageObject choiceOutputLanguage(String outputLanguage) {
        languageOutputSelectionButton.click();
        $(byText(outputLanguage)).click();
        return this;
    }

    public YandexTranslatorPageObject inputText(String text) {
        textInputClick.click();
        textInputBox.setValue(text);
        this.text = text;
        return this;
    }

    public YandexTranslatorPageObject assertions(String outputText) {
        textInputBox.shouldHave(Condition.text(text));
        textOutput.shouldHave(Condition.exactText(outputText));
        return this;
    }
}
