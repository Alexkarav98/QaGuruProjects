package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.pageObjects.gitHub.GitHubSelenidePageObjects;
import pages.pageObjects.gitHub.GitHubSelenideWikiPageObjects;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.open;

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
}
