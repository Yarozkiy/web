import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SelenideTest {

    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTest1() {
        $("[data-test-id=name] input").setValue("Алексей Алексеев-Алексеевич");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldTestNotClickAgreement() {
        $("[data-test-id=name] input").setValue("Алексей");
        $("[data-test-id=phone] input").setValue("+79111784110");
        //$("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
    @Test
    void shouldTestInvalidNumber() {
        $("[data-test-id=name] input").setValue("Алексей");
        $("[data-test-id=phone] input").setValue("+791117841");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestInvalidName() {
        $("[data-test-id=name] input").setValue("Aleksey");
        $("[data-test-id=phone] input").setValue("+79111784110");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestEmptyName() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79111784110");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestEmptyNumber() {
        $("[data-test-id=name] input").setValue("Алексей");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }


}