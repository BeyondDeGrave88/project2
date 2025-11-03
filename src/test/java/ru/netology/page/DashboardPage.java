package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement header = $(Selectors.byText("Путешествие дня"));
    private SelenideElement buyButton = $(Selectors.byText("Купить"));
    private SelenideElement creditButton = $(Selectors.byText("Купить в кредит"));

    public DashboardPage() {
        header.shouldBe(Condition.visible);
    }

    public PayByCardPage goToPayByCard() {
        buyButton.click();
        return new PayByCardPage();
    }

    public PayByCardPage goToCreditByCard() {
        creditButton.click();
        return new PayByCardPage();
    }
}