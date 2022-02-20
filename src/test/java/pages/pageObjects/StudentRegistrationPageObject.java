package pages.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationPageObject {
    private String url = "/automation-practice-form";
    private CalendarComponent calendarComponent = new CalendarComponent();
    private SelenideElement
            headerTitle = $(".main-header"),
            fistNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            userPhoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            hobbySportsButton = $(byText("Sports")),
            hobbyReadingButton = $(byText("Reading")),
            hobbyMusicButton = $(byText("Music")),
            genderOtherButton = $(byText("Other")),
            genderMaleButton = $(byText("Male")),
            genderFemaleButton = $(byText("Female")),
            dateOfBirthButton = $("#dateOfBirthInput"),
            submitButton = $("#submit"),
            validateSelector = $(".table-responsive");


    public StudentRegistrationPageObject openPage() {
        open(url);
        headerTitle.shouldHave(Condition.text("Practice form"));
        return this;
    }


    public StudentRegistrationPageObject enterFirstName(String firstName) {
        fistNameInput.setValue(firstName);
        return this;
    }

    public StudentRegistrationPageObject enterLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public StudentRegistrationPageObject enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public StudentRegistrationPageObject enterGender(String gender) {
        switch (gender) {
            case ("Male"):
                genderMaleButton.click();
                break;
            case ("Female"):
                genderFemaleButton.click();
                break;
            case ("Other"):
                genderOtherButton.click();
                break;
        }
        return this;
    }

    public StudentRegistrationPageObject enterUserPhone(String userPhone) {
        userPhoneInput.setValue(userPhone);
        return this;
    }

    public StudentRegistrationPageObject enterBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public StudentRegistrationPageObject enterSubjects(String[] subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject.substring(0, 2)).click();
            $(byText(subject)).click();
        }
        return this;
    }

    public StudentRegistrationPageObject enterHobbys(String[] hobbys) {
        for (String hobby : hobbys) {
            switch (hobby) {
                case ("Sport"):
                    hobbySportsButton.click();
                    break;
                case ("Reading"):
                    hobbyReadingButton.click();
                    break;
                case ("Music"):
                    hobbyMusicButton.click();
                    break;
            }
        }
        return this;
    }

    public StudentRegistrationPageObject uploadPictureForm(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
        return this;
    }

    public StudentRegistrationPageObject enterAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public StudentRegistrationPageObject enterStateAndCity(String state, String city) {
        stateInput.scrollTo().click();
        $(byText(state)).click();
        cityInput.click();
        $(byText(city)).click();
        return this;
    }

    public StudentRegistrationPageObject pushSubmit() {
        submitButton.click();
        return this;
    }

    public StudentRegistrationPageObject validate(String input) {
        validateSelector.shouldHave(Condition.text(input));
        return this;
    }


}

