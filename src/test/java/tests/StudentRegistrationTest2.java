package tests;

import com.github.javafaker.Faker;
import helpers.BaseTest;
import helpers.pojo.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.LocalDate;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.openqa.selenium.Keys.*;
import static org.openqa.selenium.Keys.ENTER;

public class StudentRegistrationTest2 extends BaseTest {

    @DisplayName("Register FAKE student")
    @Test
    void testStudentRegistrationForm() {
        Student student = new Student();

        open("https://demoqa.com/automation-practice-form");
        $("#app").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(student.getFirstName());
        $("#lastName").setValue(student.getLastName());
        $("#userEmail").setValue(student.getEmail());

        String cssGender = format("#gender-radio-%s", student.getGender());
        $(cssGender).parent().click();
        String genderName = $(cssGender).getValue();
        $("#userNumber").setValue(student.getMobileNum());
        $("#dateOfBirthInput").sendKeys(chord(CONTROL, "a"));
        $("#dateOfBirthInput").sendKeys(student.getBirthDateIn() + ENTER);
        $("#subjectsInput").setValue(student.getSubjects())
                .pressEnter();

        $(format("#hobbies-checkbox-%s", student.getHobbies())).parent()
                .click();
        File file = student.getIcon();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue(student.getAddress());
        $("#react-select-3-input").setValue(student.getState())
                .pressEnter();
        $("#react-select-4-input").setValue(student.getCity())
                .pressEnter();
        $("#submit").click();

        //Assert section
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x(".//table//tr[1]/td[2]").shouldHave(text(student.getFirstName() + " " + student.getLastName()));
        $x(".//table//tr[2]/td[2]").shouldHave(text(student.getEmail()));
        $x(".//table//tr[3]/td[2]").shouldHave(text(genderName));
        $x(".//table//tr[4]/td[2]").shouldHave(text(student.getMobileNum()));
        $x(".//table//tr[5]/td[2]").shouldHave(text(student.getBirthDateOut()));
    }
}