package tests;

import helpers.BaseTest;
import org.junit.jupiter.api.Disabled;
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
import static org.openqa.selenium.Keys.ENTER;

public class StudentRegistrationTest extends BaseTest {

    @Disabled
    @Test
    void testStudentRegistrationForm() {
        StringBuilder mobileNum = new StringBuilder();
        int rand = new Random().nextInt(999999);
        String fName = "Fname" + rand;
        String lName = "Lname" + rand;
        String email = format("e%s@mail.com", rand);
        int gender = new Random().nextInt(3) + 1;
        int hobby = new Random().nextInt(3) + 1;

        for (int i = 1; i <= 10; i++)
            mobileNum.append(new Random().nextInt(10));

        LocalDate birthDate = LocalDate.now().minusYears(22).minusDays(new Random().nextInt(88));
        String dateIn = birthDate.format(ofPattern("dd MMM yyyy"));
        String dateOut = birthDate.format(ofPattern("dd MMMM,yyyy"));

        open("https://demoqa.com/automation-practice-form");
        $("#app").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(fName);
        $("#lastName").setValue(lName);
        $("#userEmail").setValue(email);

        String cssGender = format("#gender-radio-%s", gender);
        $(cssGender).parent().click();
        String genderName = $(cssGender).getValue();
        $("#userNumber").setValue(mobileNum.toString());
        $("#dateOfBirthInput").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("#dateOfBirthInput").sendKeys(dateIn + ENTER);
        $("#subjectsInput").setValue("e")
                .pressEnter();

        $(format("#hobbies-checkbox-%s", hobby)).parent()
                .click();
        File file = new File(getProperty("icon.path"));
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue("country1\n city2\n street3 \n address4");
        $("#react-select-3-input").setValue("utt")
                .pressEnter();
        $("#react-select-4-input").setValue("mer")
                .pressEnter();
        $("#submit").click();

        //Assert section
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x(".//table//tr[1]/td[2]").shouldHave(text(fName + " " + lName));
        $x(".//table//tr[2]/td[2]").shouldHave(text(email));
        $x(".//table//tr[3]/td[2]").shouldHave(text(genderName));
        $x(".//table//tr[4]/td[2]").shouldHave(text(mobileNum.toString()));
        $x(".//table//tr[5]/td[2]").shouldHave(text(dateOut));
    }
}