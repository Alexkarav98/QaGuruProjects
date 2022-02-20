package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.pageObjects.StudentRegistrationPageObject;


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
    }

    @Test
    void testFormWithPageObject() {
        StudentRegistrationPageObject studentRegistration = new StudentRegistrationPageObject();
        //Input
        studentRegistration.openPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterSubjects(subjects)
                .enterGender(gender)
                .enterUserPhone(phone)
                .enterBirthDate(day, month, year)
                .enterSubjects(subjects)
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