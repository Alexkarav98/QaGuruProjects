package pages.pageObjects.gitHub;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GitHubSelenidePageObjects {
    private SelenideElement wikiButton = $(By.id("wiki-tab"));

    public GitHubSelenidePageObjects openWiki() {
        wikiButton.click();
        return this;
    }
}
