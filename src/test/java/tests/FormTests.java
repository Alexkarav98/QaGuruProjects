package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.pageObjects.StudentRegistrationPageObject;
import utils.Attachments;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class FormTests {

    //You can put your variables
    private String firstName = "Alex";
    private String lastName = "Box";
    private String email = "AlexBox@gmail.com";
    private String phone = "7123456890";
    private String year = "1990";
    private String month = "January";
    private String day = "1";
    private String gender = "Other";
    private String[] subjects = new String[]{"Maths", "Accounting"};
    private String[] hobbys = new String[]{"Sport", "Reading"};
    private String address = "Puskina AV. Colotuskina residence";
    private String photo = "Mem.png";
    private String state = "Rajasthan";
    private String city = "Jaipur";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1280";

        Configuration.remote = "https://" + System.getProperty("user") + ":" + System.getProperty("password") + "@" + System.getProperty("remoteBrowser");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments(){
        Attachments.screenshotAs("Screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        closeWebDriver();
    }

    @Test
    @Tag("Smoke")
    void testFormWithPageObject() {
        StudentRegistrationPageObject studentRegistration = new StudentRegistrationPageObject();
        open("/automation-practice-form");
        //Input
        studentRegistration.openPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterSubjects(subjects)
                .enterGender(gender)
                .enterUserPhone(phone)
                .enterBirthDate(day, month, year)
                .enterHobbys(hobbys)
                .uploadPictureForm(photo)
                .enterAddress(address)
                .enterStateAndCity(state, city)
                .pushSubmit();

        //Validate
        studentRegistration.validate(firstName)
                .validate(lastName)
                .validate(email)
                .validate(phone)
                .validate(year).validate(month).validate(day)
                .validate(photo)
                .validate(address)
                .validate(state)
                .validate(city);

        for (String s : subjects) {
            studentRegistration.validate(s);
        }
        for (String s : hobbys) {
            studentRegistration.validate(s);
        }
    }
}