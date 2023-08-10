package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=\"code\"] input");
    private SelenideElement verifyButton = $("[data-test-id=\"action-verify\"]");
    private static SelenideElement error = $("[data-test-id=error-notification]");

    public VerificationPage() {
        codeField.should(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
        return new DashboardPage();
    }

    public void getError(String expectedText) {
        error.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).should(visible);
    }
}
