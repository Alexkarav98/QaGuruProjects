import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FormTests {
    //You can put your variables
    private String firstName = "Alex";
    private String lastName = "Box";
    private Gender gender = Gender.OTHER;
    private String email = "AlexBox@gmail.com";
    private String phone = "7123456890";
    private String year = "1990";
    private String day = "001";
    private String[] subjects = new String[]{"Maths", "Accounting"};
    private String[] hobbies = new String[]{"Sports", "Reading"};
    private String address = "Puskina AV. Colotuskina residence";
    private String photo = "Mem.png";
    private String state = "Rajasthan";
    private String city = "Jaipur";

    @BeforeAll

    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    private enum Gender {
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        private String selectorValue;

        Gender(String selector) {
            this.selectorValue = selector;
        }
    }

    @Test
    void testForm() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice form"));

        //Name
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender.selectorValue)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();

        //Date
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__month-select").selectOptionByValue("0"); // 0-11 month 0-January etc
        $(".react-datepicker__day.react-datepicker__day--001").click();

        //Subjects
        $("#subjectsInput").setValue("Ma").click();
        $(byText((String) Array.get(subjects, 0))).click();
        $("#subjectsInput").setValue("Ac");
        $(byText((String) Array.get(subjects, 1))).click();

        //Hobby
        $(byText(String.valueOf(Array.get(hobbies, 0)))).click();
        $(byText(String.valueOf(Array.get(hobbies, 1)))).click();

        //Image
        $("#uploadPicture").uploadFromClasspath("Mem.png");

        //Address
        $("#currentAddress").setValue(address);

        //State
        $("#state").scrollTo().click();
        $(byText(state)).click();

        //City
        $("#city").click();
        $(byText(city)).click();

        //Submit
        $("#submit").click();


        //Validate
        $(".table-responsive").shouldHave(
                text("Student Name " + firstName + " " + lastName),
                text("Student Email " + email),
                text("Gender " + gender.selectorValue),
                text("Mobile " + phone),
                text("Date of Birth " + day.substring(1) + " January," + year),
                text("Subjects " + Array.get(subjects, 0) + ", " + Array.get(subjects, 1)),
                text("Hobbies " + Array.get(hobbies, 0) + ", " + Array.get(hobbies, 1)),
                text("Picture " + photo),
                text("Address " + address),
                text("State and City " + state + " " + city));
    }
}