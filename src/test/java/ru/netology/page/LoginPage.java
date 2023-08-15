package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    private static SelenideElement login = $("[data-test-id=\"login\"] input");
    private static SelenideElement password = $("[data-test-id=\"password\"] input");
    private static SelenideElement actionLogin = $("[data-test-id=\"action-login\"]");
    private static SelenideElement error = $("[data-test-id=error-notification]");

    public static VerificationPage validLogin(DataHelper.AuhtInfo info) {
        login.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), info.getLogin());
        password.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), info.getPassword());
        actionLogin.click();
        return new VerificationPage();
    }

    public void verifyErrorNotificationVisiblity(String expectedText) {
        error.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).should(visible);
    }
}