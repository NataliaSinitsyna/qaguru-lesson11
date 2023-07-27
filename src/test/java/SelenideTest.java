import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class SelenideTest extends TestBase {
    private static final String REPOSITORY = "allure-framework/allure2";
    private static final String ISSUE = "No screenshot attached to Allure report";

    @Test
    public void testLambdaStep() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть главную страницу", () -> {
            open("https://github.com");
        });
        step("Ввести в поисковой строке наименование репозитория " + REPOSITORY + " и нажать enter", () -> {
            $(byText("Search or jump to...")).click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Выбрать на репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открыть таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие Issue с наименованием " + ISSUE, () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }

}