package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.pageObjects.HerokuAppPageObject;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.*;

public class HerokuAppTests {
    HerokuAppPageObject herokuAppPageObject = new HerokuAppPageObject();

    @BeforeAll
    static void beforeAll(){
        baseUrl = ("https://the-internet.herokuapp.com");
        browserSize = "1920x1280";
    }

    @Test
    void DragAndDrop(){
        open("/drag_and_drop");
        herokuAppPageObject.DragAndDrop();
    }
}
