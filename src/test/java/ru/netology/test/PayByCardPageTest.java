package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.PayByCardPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayByCardPageTest {

    private DashboardPage dashboardPage;

    @BeforeAll
    static void setUpAll() {
        SQLHelper.clearDatabase();
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        dashboardPage = new DashboardPage();
    }

    @AfterAll
    static void tearDownAll() {
        SQLHelper.clearDatabase();
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
    void shouldShowErrorWithSortNumberCard() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getInvalidCardWithShortNumber());
        payPage.checkCardNumberError();
    }
    @Test
    void shouldShowErrorWithNonExistenceMonth() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithInvalidMonth());
        payPage.checkMonthError();
    }

    @Test
    void shouldShowErrorWithExpiredMonth() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithExpiredMonth());
        payPage.checkMonthError();
    }

    @Test
    void shouldShowErrorWithExpiredYear() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithExpiredYear());
        payPage.checkYearError();
    }

    @Test
    void shouldShowErrorWithCyrillicOwner() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithCyrillicOwner());
        payPage.checkOwnerError();
    }

    @Test
    void shouldShowErrorWithShortCvc() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithShortCvc());
        payPage.checkCvcError();
    }

    @Test
    void shouldShowErrorWithLongCvc() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardNumberWithLongCvc());
        payPage.checkCvcError();
    }

    @Test
    void shouldShowErrorWithEmptyFields() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.continueButton.click();

        payPage.checkEmptyCvcError();
        payPage.checkEmptyOwnerError();
        payPage.checkEmptyMonthError();
        payPage.checkEmptyYearError();
        payPage.checkEmptyCardNumberError();

        payPage.continueButton.shouldBe(visible).shouldBe(enabled);
        payPage.successNotification.shouldNotBe(visible);
        payPage.errorNotification.shouldNotBe(visible);

    }

    @Test
    void shouldShowErrorWithEmptyCardNumberField() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyNumber());

        payPage.continueButton.click();
        payPage.checkEmptyCardNumberError();
        payPage.verifyFormNotSubmitted();

    }
    @Test
    void shouldShowErrorWithEmptyMonth() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyMonth());
        payPage.checkEmptyMonthError();
        payPage.verifyFormNotSubmitted();
    }

    @Test
    void shouldShowErrorWithEmptyYear() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyYear());
        payPage.checkEmptyYearError();
        payPage.verifyFormNotSubmitted();
    }

    @Test
    void shouldShowErrorWithEmptyOwner() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyOwner());
        payPage.checkEmptyOwnerError();
        payPage.verifyFormNotSubmitted();
    }

    @Test
    void shouldShowErrorWithEmptyCvc() {
        PayByCardPage payPage = dashboardPage.goToPayByCard();
        payPage.fillForm(DataHelper.getCardWithEmptyCvc());
        payPage.checkEmptyCvcError();
        payPage.verifyFormNotSubmitted();
    }

}
