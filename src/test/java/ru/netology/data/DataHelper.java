package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo generateAuthInfo() {
        Faker faker = new Faker(new Locale("ru"));
        return new AuthInfo(
                faker.name().username(),
                faker.internet().password()
        );
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static VerificationCode generateVerificationCode() {
        Faker faker = new Faker(new Locale("ru"));
        return new VerificationCode(
                faker.number().digits(5)
        );
    }
}
