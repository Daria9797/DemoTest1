import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Tag("demoqa")
public class FormRegistration extends TestBase{

    @Test
    void enterFormTest()
    {
        step("Открываем страницу регистрации", ()-> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });

        step("Заполняем имя",()->{
            $("#firstName").setValue("Daria");
        });

        step("Заполняем фамилию",()->{
            $("#lastName").setValue("Kuteynikova");
        });

        step("Заполняем почту",()->{
            $("#userEmail").setValue("kolohmatova@yandex.ru");
        });

        step("Выбираем пол",()->{
            $("#genterWrapper").$(byText("Female")).click();
        });

        step("Заполняем номер",()->{
            $("#userNumber").setValue("9002172121");
        });

        step("Заполняем дату рождения",()->{
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("January");
            $(".react-datepicker__year-select").selectOption("1997");
            $(".react-datepicker__day--006").click();
        });

        step("Заполняем предмет",()->{
            $("#subjectsInput").setValue("Maths").pressEnter();
        });

        step("Выбираем хобби",()->{
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#hobbiesWrapper").$(byText("Reading")).click();
        });

        step("Загружаем аватарку",()->{
            if (!Configuration.browser.equalsIgnoreCase("firefox")) {
                $("#uploadPicture").uploadFromClasspath("cat.jpg");
            }

        });

        step("Заполняем адрес",()->{
            $("#currentAddress").setValue("Moskow");
        });

        step("Выбираем штат и город",()->{
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Haryana")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Karnal")).click();
        });

        step("Нажимаем на кнопку регистрации",()->{
            $("#submit").click();
        });


        step("Проверяем соответствие введённых данных ",()->{
            $(".table-responsive").shouldHave(text("Daria Kuteynikova"));
            $(".table-responsive").shouldHave(text("kolohmatova@yandex.ru"));
            $(".table-responsive").shouldHave(text("Female"));
            $(".table-responsive").shouldHave(text("9002172121"));
            $(".table-responsive").shouldHave(text("6 January,1997"));
            $(".table-responsive").shouldHave(text("Maths"));
            $(".table-responsive").shouldHave(text("Sports, Reading"));
            if (!Configuration.browser.equalsIgnoreCase("firefox")) {
                $(".table-responsive").shouldHave(text("cat.jpg"));
            }
            $(".table-responsive").shouldHave(text("Moskow"));
            $(".table-responsive").shouldHave(text("Haryana Karnal"));
        });

    }

}
