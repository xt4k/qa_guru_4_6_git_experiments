package tests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;

public class SelenideYandexTest {

  @Test
  void justFindSelenideInYandex() {
    open("https://www.yandex.ru/");
    $("#text").setValue("Selenide").pressEnter();
    // asssert
    $$(".main__center").last().shouldHave(text("selenide.org"));
  }
}
