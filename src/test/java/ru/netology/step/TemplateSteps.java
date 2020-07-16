package ru.netology.step;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * cards:
 * first:
 * number: '5559 0000 0000 0001'
 * balance: 10 000 RUB
 * second:
 * number: '5559 0000 0000 0002'
 * balance: 10 000 RUB

1. Перевода с определённой карты на другую карту n'ой суммы
2. Проверки баланса по карте (со страницы списка карт)
 */
public class TemplateSteps {
    private final String cardNunber0001 = "5559 0000 0000 0001";
    private final String cardNunber0002 = "5559 0000 0000 0002";

    private DashboardPage openDashboard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        return verificationPage.validVerify(verificationCode);
    }

    /*
    Когда он переводит "5 000" рублей с карты с номером "5559 0000 0000 0002" на свою "1" карту с главной страницы;
     */
    @Test
    public void shouldTransferFromCard0001toCard002 () {
        DashboardPage page = openDashboard();
        page.updateBalance();
        int currentBalance = page.getBalance("0001");
        int expected = currentBalance + 5000;
        page.moneyTransfer("0001", cardNunber0002, 5000);
        page.updateBalance();
        assertEquals(expected, page.getBalance("0001"));
    }

    /*
    Тогда баланс его "1" карты из списка на главной странице должен стать "15 000" рублей.
     */
    public void shouldCheckBalanceTransferFromCard000 () {

    }
}
/*
Issue #1
Сохраняется сумма предыдущей операции в форме пополнения карты
Issue #2
Возможно снятие суммы большей, чем есть на карте
 */