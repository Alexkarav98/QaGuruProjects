package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.pageObjects.gitHub.GitHubSelenidePageObjects;
import pages.pageObjects.gitHub.GitHubSelenideWikiPageObjects;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GitHubTests {

    GitHubSelenidePageObjects gitHubSelenidePageObjects = new GitHubSelenidePageObjects();
    GitHubSelenideWikiPageObjects gitHubSelenideWikiPageObjects = new GitHubSelenideWikiPageObjects();

    @BeforeAll
    static void beforeAll() {
        baseUrl = ("https://github.com/");
        browserSize = "1920x1280";
    }

    @Test
    void SelenideElementTest() {
        open("selenide/selenide");
        gitHubSelenidePageObjects.openWiki();
        gitHubSelenideWikiPageObjects.checkText("SoftAssertions")
                .clickSoftAssertions()
                .checkJunit5Text("Using JUnit5 extend test class");
    }

    @Test
    void IssueTestAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();

        $(By.linkText("eroshenkoam/allure-example")).click();
        $(By.partialLinkText("Issues")).click();
        $(withText("#68")).should(Condition.exist);
    }
}
