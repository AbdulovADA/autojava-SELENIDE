package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class CardFormTest {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldTestOrderAdminCenter() {
        String meetingDate = generateDate(6);

       $("[data-test-id = 'city'] input").setValue("Самара");
       $("[data-test-id = 'date'] input").doubleClick();
       $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
       $("[data-test-id = 'date'] input").setValue(meetingDate);
       $x("//input[@name='name']").setValue("Алина Пушкина-Абрамова");
       $x("//input[@name='phone']").setValue("+79996666666");
       $("[data-test-id='agreement']").click();
       $x("//span[@class='button__text']").click();
       $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + meetingDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
