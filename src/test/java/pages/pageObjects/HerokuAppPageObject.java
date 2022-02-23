package pages.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import tests.HerokuAppTests;

import static com.codeborne.selenide.Selenide.$;

public class HerokuAppPageObject {
    private SelenideElement columnA = $(By.id("column-a"));
    private SelenideElement columnB = $(By.id("column-b"));

    public HerokuAppPageObject dragAndDrop(){
        columnA.dragAndDropTo(columnB);
        columnA.shouldHave(Condition.text("B"));
        columnB.shouldHave(Condition.text("A"));
        return this;
    }
}
