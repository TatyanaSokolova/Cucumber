package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private ElementsCollection button = $$("[data-test-id=action-deposit]");
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        String[] subtext = text.split(":");
        String balance = subtext[1].substring(0, subtext[1].indexOf("р.")).trim();
        return Integer.parseInt(balance);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        String[] subtext = text.split(":");
        String balance = subtext[1].substring(0, subtext[1].indexOf("р.")).trim();
        return Integer.parseInt(balance);
    }


    public TransferPage selectFirstCardToTransfer() {
        button.first().click();
        return new TransferPage();
    }

    public TransferPage selectSecondCardToTransfer() {
        button.last().click();
        return new TransferPage();
    }
}
