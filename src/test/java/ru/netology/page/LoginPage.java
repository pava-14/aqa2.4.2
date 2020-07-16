package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

@Name("Страница входа")
public class LoginPage extends AkitaPage {
    @Name("Логин")
    private SelenideElement loginField = $("[data-test-id=login] input");
    @Name("Ппароль")
    private SelenideElement passwordField = $("[data-test-id=password] input");
    @Name("Продолжить")
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }
}
