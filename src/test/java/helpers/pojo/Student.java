package helpers.pojo;

import com.github.javafaker.Faker;

import java.io.File;
import java.time.LocalDate;
import java.util.Random;

import static java.lang.String.valueOf;
import static java.lang.System.getProperty;
import static java.time.ZoneId.systemDefault;
import static java.time.format.DateTimeFormatter.ofPattern;

public class Student {

    private String dateInPattern = "dd MMM yyyy";
    private String dateOutPattern = "dd MMMM,yyyy";
    private final String firstName;
    private final String lastName;
    private final String email;
    private final int gender;
    private final String mobileNum;
    private final LocalDate birthDate;
    private final String subjects;
    private final int hobbies;
    private final String address;
    private final File icon;
    private final String state;
    private final String city;

    public Student() {
        Faker faker = new Faker();
        StringBuilder mobileNum = new StringBuilder();

        for (int i = 1; i <= 10; i++)
            mobileNum.append(new Random().nextInt(10));

        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.gender = new Random().nextInt(3) + 1;
        this.mobileNum = valueOf(mobileNum);
        this.birthDate = faker.date().birthday(15, 55).toInstant().atZone(systemDefault()).toLocalDate();
        this.subjects = "e";
        this.hobbies = new Random().nextInt(3) + 1;
        this.address = faker.address().fullAddress();
        this.icon = new File(getProperty("icon.path"));
        this.state = "u";
        this.city = "l";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getGender() {
        return gender;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateIn() {
        return getBirthDate().format(ofPattern(dateInPattern));
    }

    public String getBirthDateOut() {
        return getBirthDate().format(ofPattern(dateOutPattern));
    }


    public String getSubjects() {
        return subjects;
    }

    public int getHobbies() {
        return hobbies;
    }

    public String getAddress() {
        return address;
    }

    public File getIcon() {
        return icon;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
