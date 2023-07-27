import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class StepsTest extends TestBase {
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("nsinitsyna")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test repository", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    public void testStaticLabels() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(byText("Search or jump to...")).click();
        $("#query-builder-test").sendKeys("allure-framework/allure2");
        $("#query-builder-test").submit();

        $(linkText("allure-framework/allure2")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);
    }
}