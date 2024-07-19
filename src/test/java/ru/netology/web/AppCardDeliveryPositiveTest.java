package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$;

public class AppCardDeliveryPositiveTest {

    private String dateGenerator (long days, String pattern) {
        return LocalDate.now().plusDays(days).format(java.time.format.DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldSucceed() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String planningDate = dateGenerator(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").setValue("Иванов Федор");
        $("[data-test-id='phone'] input").setValue("+79099998758");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));

    }
}