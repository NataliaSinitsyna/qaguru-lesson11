import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps extends TestBase {
    @Step("Открыть главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ввести в поисковой строке наименование репозитория {repo} и нажать enter")
    public void searchRepository(String repo) {
        $(byText("Search or jump to...")).click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }
    @Step("Выбрать на репозиторий {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открыть таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверить наличие Issue с наименованием {issue}")
    public void shouldSeeIssueWithNumber(String issue) {
        $(withText(issue)).should(Condition.exist);
    }

}
