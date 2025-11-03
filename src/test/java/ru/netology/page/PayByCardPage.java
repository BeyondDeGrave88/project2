package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class PayByCardPage {
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvcField = $("[placeholder='999']");

    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    public SelenideElement getCardNumberField() {
        return cardNumberField;
    }

    public SelenideElement getMonthField() {
        return monthField;
    }

    public SelenideElement getYearField() {
        return yearField;
    }

    public SelenideElement getOwnerField() {
        return ownerField;
    }

    public SelenideElement getCvcField() {
        return cvcField;
    }
    public String getCvcFieldValue() {
        return cvcField.getValue();
    }

    public void setCardNumber(String value) {
        cardNumberField.setValue(value);
    }

    public void setMonth(String value) {
        monthField.setValue(value);
    }

    public void setYear(String value) {
        yearField.setValue(value);
    }

    public void setOwner(String value) {
        ownerField.setValue(value);
    }

    public void setCvc(String value) {
        cvcField.setValue(value);
    }

    public void clickContinue() {
        continueButton.shouldBe(visible).shouldBe(enabled).click();
    }

    public void fillForm(DataHelper.CardInfo cardInfo) {
        setCardNumber(cardInfo.getCardNumber());
        setMonth(cardInfo.getMonth());
        setYear(cardInfo.getYear());
        setOwner(cardInfo.getOwner());
        setCvc(cardInfo.getCvc());
        clickContinue();
    }

    public void fillEmptyForm() {
        cardNumberField.clear();
        monthField.clear();
        yearField.clear();
        ownerField.clear();
        cvcField.clear();
        clickContinue();
    }

    public void waitForSuccessNotification() {
        successNotification.shouldBe(visible, ofSeconds(15))
                .shouldHave(text("Операция одобрена Банком."));
    }

    public void waitForErrorNotification() {
        errorNotification.shouldBe(visible, ofSeconds(15))
                .shouldHave(text("Банк отказал в проведении операции."));

    }

    public void verifyFormNotSubmitted() {
        successNotification.shouldNotBe(visible);
        errorNotification.shouldNotBe(visible);
    }

    public void checkFieldError(SelenideElement field, String expectedError) {
        field.closest(".input").$(".input__sub").shouldHave(text(expectedError));
    }
}


