package pages.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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


    @Step("Открываем страницу")
    public StudentRegistrationPageObject openPage() {
        open(url);
        headerTitle.shouldHave(Condition.text("Practice form"));
        return this;
    }


    @Step("Вводим имя")
    public StudentRegistrationPageObject enterFirstName(String firstName) {
        fistNameInput.setValue(firstName);
        return this;
    }

    @Step("Вводим фамилию")
    public StudentRegistrationPageObject enterLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Вводим почту")
    public StudentRegistrationPageObject enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }
    @Step("Выбираем пол")
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

    @Step("Вводим телефон")
    public StudentRegistrationPageObject enterUserPhone(String userPhone) {
        userPhoneInput.setValue(userPhone);
        return this;
    }

    @Step("Вводим дату рождения")
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

    @Step("Вводим хобби")
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

    @Step("Загружаем картинку")
    public StudentRegistrationPageObject uploadPictureForm(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Вводим адрес")
    public StudentRegistrationPageObject enterAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    @Step("Вводим дату и штат")
    public StudentRegistrationPageObject enterStateAndCity(String state, String city) {
        stateInput.scrollTo().click();
        $(byText(state)).click();
        cityInput.click();
        $(byText(city)).click();
        return this;
    }

    @Step("Отправляем данные")
    public StudentRegistrationPageObject pushSubmit() {
        submitButton.click();
        return this;
    }

    public StudentRegistrationPageObject validate(String input) {
        validateSelector.shouldHave(Condition.text(input));
        return this;
    }


}

