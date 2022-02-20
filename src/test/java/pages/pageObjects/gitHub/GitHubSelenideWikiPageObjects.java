package pages.pageObjects.gitHub;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class GitHubSelenideWikiPageObjects {
    private SelenideElement layoutSideBar = $(".Layout-sidebar");
    private SelenideElement buttonShowAllPages = $(".f6.Link--muted.js-wiki-more-pages-link.btn-link.mx-auto");
    private SelenideElement markDownBody = $(".markdown-body");
    private SelenideElement buttonSoftAssertions = $(byText("SoftAssertions"));

    public GitHubSelenideWikiPageObjects checkText(String text) {
        buttonShowAllPages.scrollTo().click();
        layoutSideBar.shouldHave(Condition.text(text));
        return this;
    }

    public GitHubSelenideWikiPageObjects clickSoftAssertions(){
        buttonSoftAssertions.click();
        return this;
    }

    public GitHubSelenideWikiPageObjects checkJunit5Text(String text) {
        markDownBody.shouldHave(Condition.text(text))
                .findElement(byText("@ExtendWith"));
        return this;
    }
}
