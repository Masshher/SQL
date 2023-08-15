package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuhtInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static AuhtInfo getAuhtInfo() {
        return new AuhtInfo("vasya", "qwerty123");
    }

    public static AuhtInfo generateRandomUser() {
        return new AuhtInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static AuhtInfo generateUserWithRandomPassword() {
        return new AuhtInfo("vasya", generateRandomPassword());
    }

    public static String generateRandomLogin() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().username();
    }

    public static String generateRandomPassword() {
        Faker faker = new Faker(new Locale("en"));
        return faker.internet().password();
    }

    public static VerificationCode generateRandomVerificationCode() {
        Faker faker = new Faker(new Locale("en"));
        return new VerificationCode(faker.numerify("######"));
    }
}
