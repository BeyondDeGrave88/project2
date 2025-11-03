package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.PayByCardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayByCardPageTest {

    private DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        SQLHelper.clearDatabase();
        open("http://localhost:8080");
        dashboardPage = new DashboardPage();
    }

    //               Позитивные сценарии оплаты/кредита APPROVED и DECLINED карт
    @Test
    void shouldSuccessPayOnPayPageWithApprovedCard() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getApprovedCardInfo());
        payPage.waitForSuccessNotification();

        String status = SQLHelper.getLastPaymentStatus();
        assertEquals("APPROVED", status);
    }

    @Test
    void shouldSuccessPayOnCreditPageWithApprovedCard() {
        PayByCardPage payPage = dashboardPage.goToCreditByCard();
        payPage.fillForm(DataHelper.getApprovedCardInfo());
        payPage.waitForSuccessNotification();

        String status = SQLHelper.getLastCreditStatus();
        assertEquals("APPROVED", status);
    }

    @Test
    void shouldDeclinePayWithDeclinedCard() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getDeclinedCardInfo());
        payPage.waitForErrorNotification();

        String status = SQLHelper.getLastPaymentStatus();
        assertEquals("DECLINED", status);
    }

    //         Негативные сценарии (для формы "Обычная оплата")
    @Test
    void shouldShowErrorWithNonExistentCard() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getNonExistentCardNumber());
        payPage.waitForErrorNotification();
    }

    @Test
    void shouldShowErrorWithShortNumberCard() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getInvalidCardWithShortNumber());
        payPage.checkFieldError(payPage.getCardNumberField(), "Неверный формат");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldShowErrorWithNonExistenceMonth() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithInvalidMonth());
        payPage.checkFieldError(payPage.getMonthField(), "Неверный формат");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldShowErrorWithExpiredMonth() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithExpiredMonth());
        payPage.checkFieldError(payPage.getMonthField(), "Истёк срок действия карты");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldShowErrorWithExpiredYear() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithExpiredYear());
        payPage.checkFieldError(payPage.getYearField(), "Истёк срок действия карты");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldShowErrorWithCyrillicOwner() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithCyrillicOwner());
        payPage.checkFieldError(payPage.getOwnerField(), "Неверный формат");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldShowErrorWithShortCvc() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithShortCvc());
        payPage.checkFieldError(payPage.getCvcField(), "Неверный формат");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldLimitCvcInputLength() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        DataHelper.CardInfo cardInfo = DataHelper.getCardNumberWithLongCvc();

        payPage.setCardNumber(cardInfo.getCardNumber());
        payPage.setMonth(cardInfo.getMonth());
        payPage.setYear(cardInfo.getYear());
        payPage.setOwner(cardInfo.getOwner());
        payPage.setCvc(cardInfo.getCvc());

        String actualCvc = payPage.getCvcFieldValue();
        assertEquals(3, actualCvc.length());

        payPage.verifyFormNotSubmitted();
    }


    @Test
    void shouldShowErrorWithEmptyFields() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillEmptyForm();
        payPage.checkFieldError(payPage.getCardNumberField(), "Поле обязательно для заполнения");
        payPage.checkFieldError(payPage.getMonthField(), "Поле обязательно для заполнения");
        payPage.checkFieldError(payPage.getYearField(), "Поле обязательно для заполнения");
        payPage.checkFieldError(payPage.getOwnerField(), "Поле обязательно для заполнения");
        payPage.checkFieldError(payPage.getCvcField(), "Поле обязательно для заполнения");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldShowErrorWithEmptyCardNumberField() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyNumber());
        payPage.checkFieldError(payPage.getCardNumberField(), "Поле обязательно для заполнения");

        payPage.verifyFormNotSubmitted();

    }

    @Test
    void shouldShowErrorWithEmptyMonth() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyMonth());
        payPage.checkFieldError(payPage.getMonthField(), "Поле обязательно для заполнения");

        payPage.verifyFormNotSubmitted();
    }

    @Test
    void shouldShowErrorWithEmptyYear() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyYear());
        payPage.checkFieldError(payPage.getYearField(), "Поле обязательно для заполнения");

        payPage.verifyFormNotSubmitted();
    }

    @Test
    void shouldShowErrorWithEmptyOwner() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyOwner());
        payPage.checkFieldError(payPage.getOwnerField(), "Поле обязательно для заполнения");

        payPage.verifyFormNotSubmitted();
    }

    @Test
    void shouldShowErrorWithEmptyCvc() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyCvc());
        payPage.checkFieldError(payPage.getCvcField(), "Поле обязательно для заполнения");

        payPage.verifyFormNotSubmitted();
    }
}
