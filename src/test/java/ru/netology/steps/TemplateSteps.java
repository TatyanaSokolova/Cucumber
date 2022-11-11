package ru.netology.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;

import io.cucumber.java.ru.Тогда;

import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {

    private static DashboardPage dashboardPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string} и ввел проверочный код {string}")
    public void loginWithNamePasswordAndCode(String login, String password, String code) {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(code);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы")
    public void makeTransferFromSecondCardToFirst(String amountOfTransfer, String secondCard, String firstCard) {
        var transferPage = dashboardPage.selectFirstCardToTransfer(firstCard);
        transferPage.makeValidTransfer(amountOfTransfer, secondCard);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей")
    public void getActualBalance(String firstCard, String expectedBalance) {
        var actualBalance = dashboardPage.getFirstCardBalance(firstCard);
        assertEquals(expectedBalance, actualBalance);
    }
}
