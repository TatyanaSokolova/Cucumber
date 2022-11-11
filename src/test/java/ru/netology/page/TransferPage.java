package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement headingText = $(byText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id=amount] .input__control");
    private SelenideElement from = $("[data-test-id=from] .input__control");
    private SelenideElement actionTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement expectedErrorMessage = $("[data-test-id=error-notification]");

    public TransferPage() {
        headingText.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountOfTransfer, String secondCard) {
        amount.setValue(amountOfTransfer);
        from.setValue(secondCard);
        actionTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage makeTransferWithInvalidSum(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        from.setValue(cardInfo.getCardNumber());
        actionTransfer.click();
        expectedErrorMessage.shouldHave(text("Вы не можете совершить перевод больше доступной на карте суммы"), Duration.ofSeconds(10)).shouldBe(visible);
        return new DashboardPage();
    }
}