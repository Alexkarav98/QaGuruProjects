package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.pageObjects.gitHub.GitHubSelenidePageObjects;
import pages.pageObjects.gitHub.GitHubSelenideWikiPageObjects;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class GitHubTests {

    GitHubSelenidePageObjects gitHubSelenidePageObjects = new GitHubSelenidePageObjects();
    GitHubSelenideWikiPageObjects gitHubSelenideWikiPageObjects = new GitHubSelenideWikiPageObjects();
    private String REPOSITORY = "eroshenkoam/allure-example";
    private String ISSUE_NUMBER = "68";

    @BeforeAll
    static void beforeAll() {
        baseUrl = ("https://github.com/");
        browserSize = "1920x1280";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
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
        open(baseUrl);

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();

        $(By.linkText("eroshenkoam/allure-example")).click();
        $(By.partialLinkText("Issues")).click();
        $(withText(ISSUE_NUMBER)).should(exist);
    }

    @Test
    void IssueTestAnnotationStep() {
        String repo = "eroshenkoam/allure-example";
        open(baseUrl);
        gitHubSelenidePageObjects.searchRepo(repo)
                .clickOnRepo(repo)
                .openIssueTab()
                .shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }

    @Test
    void IssueTestLambdaSteps() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репоизторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Открываем репозиторий " + REPOSITORY, () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });
        step("Переходим в таб Issue", () -> {
            $(By.partialLinkText("Issues")).click();
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });
        step("Проверяем что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText(ISSUE_NUMBER)).should(exist);
        });
    }
}
