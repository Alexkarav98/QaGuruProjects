package pages.pageObjects.gitHub;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class GitHubSelenidePageObjects {
    private SelenideElement wikiButton = $(By.id("wiki-tab"));

    public GitHubSelenidePageObjects openWiki() {
        wikiButton.click();
        return this;
    }

    @Step("Ищем репозиторий")
    public GitHubSelenidePageObjects searchRepo(String repo){
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
        return this;
    }

    public GitHubSelenidePageObjects clickOnRepo(String repo){
        $(By.linkText(repo)).click();
        return this;
    }

    @Step("Переходим в Issue")
    public GitHubSelenidePageObjects openIssueTab() {
        $(By.partialLinkText("Issues")).click();
        return this;
    }

    @Step("Проверяем что существует Issue с номером {num}")
    public GitHubSelenidePageObjects shouldSeeIssueWithNumber(String num) {
        $(withText(num)).should(exist);
        return this;
    }
}
