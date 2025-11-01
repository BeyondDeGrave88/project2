package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class PayByCardPage {
    public SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    public SelenideElement monthField = $("[placeholder='08']");
    public SelenideElement yearField = $("[placeholder='22']");
    public SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    public SelenideElement cvcField = $("[placeholder='999']");

    public SelenideElement continueButton = $(byText("Продолжить"));
    public SelenideElement successNotification = $(".notification_status_ok");
    public SelenideElement errorNotification = $(".notification_status_error");

    public void fillForm(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getOwner());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void waitForSuccessNotification() {
        successNotification.shouldBe(visible, ofSeconds(15));
    }

    public void waitForErrorNotification() {
        errorNotification.shouldBe(visible, ofSeconds(15));
    }
    public void verifyFormNotSubmitted() {
        successNotification.shouldNotBe(visible);
        errorNotification.shouldNotBe(visible);
    }

    public void checkCardNumberError() {
        $(".input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkEmptyCardNumberError() {
        cardNumberField.closest(".input").$(".input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkMonthError() {
        $(".input__sub").shouldHave(text("Неверно указан срок действия карты"));
    }

    public void checkEmptyMonthError() {
        monthField.closest(".input").$(".input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkYearError() {
        $(".input__sub").shouldHave(text("Истёк срок действия карты"));
    }

    public void checkEmptyYearError() {
        yearField.closest(".input").$(".input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkOwnerError() {
        $(".input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkEmptyOwnerError() {
        ownerField.closest(".input").$(".input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    public void checkCvcError() {
        $(".input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkEmptyCvcError() {
        cvcField.closest(".input").$(".input__sub").shouldHave(text("Неверный формат"));
    }
}

