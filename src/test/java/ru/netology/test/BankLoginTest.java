package ru.netology.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static ru.netology.data.SQLHelper.cleanDatabase;
import static com.codeborne.selenide.Selenide.open;

public class BankLoginTest {

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @Test
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var auhtInfo = DataHelper.getAuhtInfo();
        var verificationPage = loginPage.validLogin(auhtInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldGetErrorIfUserNotInDatabase() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var auhtInfo = DataHelper.generateRandomUser();
        loginPage.validLogin(auhtInfo);
        loginPage.verifyErrorNotificationVisiblity("Ошибка " +
                "Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void shouldGetErrorInvalidVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var auhtInfo = DataHelper.getAuhtInfo();
        var verificationPage = loginPage.validLogin(auhtInfo);
        verificationPage.verifyVerificationPageVisiblity();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisiblity();

    }

    @Test
    void shouldBlockIfEnterWrongPasswordThreeTimes() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var auhtInfo = DataHelper.generateUserWithRandomPassword();
        loginPage.validLogin(auhtInfo);
        loginPage.verifyErrorNotificationVisiblity("Ошибка " +
                "Ошибка! Неверно указан логин или пароль");
        loginPage.validLogin(auhtInfo);
        loginPage.verifyErrorNotificationVisiblity("Ошибка " +
                "Ошибка! Неверно указан логин или пароль");
        loginPage.validLogin(auhtInfo);
        loginPage.verifyErrorNotificationVisiblity("Ошибка " +
                "Ошибка! + Пользователь заблокирован");
    }

}
