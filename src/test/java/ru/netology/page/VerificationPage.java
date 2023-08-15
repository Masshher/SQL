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

    public void verifyVerificationPageVisiblity() {
        codeField.shouldBe(visible);
    }

    public void verifyErrorNotificationVisiblity() {
        error.shouldHave(visible);
    }

    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }
}
