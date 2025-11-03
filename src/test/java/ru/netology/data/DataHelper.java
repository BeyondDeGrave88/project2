package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));
    private static final Faker FAKER_RU = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(getValidDeclinedCard(), getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
    }
    public static String getValidApprovedCard() {
        return "1111 2222 3333 4444";
    }
    public static String getValidDeclinedCard() {
        return "5555 6666 7777 8888";
    }

    public static String getValidMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getValidYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidOwner() {
        return FAKER.name().firstName().toUpperCase() + " " + FAKER.name().lastName().toUpperCase();
    }

    public static String getValidCvc() {
        return String.format("%03d", FAKER.number().numberBetween(1, 999));
    }

    //               Методы для негативных тестов
    public static CardInfo getNonExistentCardNumber() {
        return new CardInfo("0000 0000 0000 0000",getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getInvalidCardWithShortNumber() {
        return new CardInfo("1111 2222 3333",getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardNumberWithInvalidMonth() {
        return new CardInfo(getValidApprovedCard(),getInvalidMonth(),getValidYear(),getValidOwner(),getValidCvc());
    }

    public static String getInvalidMonth() {
        return String.valueOf(FAKER.number().numberBetween(13, 100));
    }

    public static CardInfo getCardNumberWithExpiredMonth() {
        return new CardInfo(getValidApprovedCard(),getExpiredMonth(),getCurrentYear(),getValidOwner(),getValidCvc());
    }

    public static String getExpiredMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static CardInfo getCardNumberWithExpiredYear() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), getExpiredYear(), getValidOwner(), getValidCvc());
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getExpiredYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static CardInfo getCardNumberWithCyrillicOwner() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), getValidYear(), getInvalidOwnerCyrillic(), getValidCvc());
    }
    public static String getInvalidOwnerCyrillic() {
        return FAKER_RU.name().firstName().toUpperCase() + " " + FAKER_RU.name().lastName().toUpperCase();
    }

    public static CardInfo getCardNumberWithShortCvc() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), getValidYear(), getValidOwner(), getInvalidCvcShort());
    }

    public static String getInvalidCvcShort() {
        return String.format("%02d", FAKER.number().numberBetween(0, 100));
    }
    public static CardInfo getCardNumberWithLongCvc() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), getValidYear(), getValidOwner(), getInvalidCvcLong());
    }

    public static String getInvalidCvcLong() {
        return String.format("%04d", FAKER.number().numberBetween(0, 10000));
    }

    public static CardInfo getCardWithEmptyNumber() {
        return new CardInfo("", getValidMonth(), getValidYear(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardWithEmptyMonth() {
        return new CardInfo(getValidApprovedCard(), "", getValidYear(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardWithEmptyYear() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), "", getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardWithEmptyOwner() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), getValidYear(), "", getValidCvc());
    }

    public static CardInfo getCardWithEmptyCvc() {
        return new CardInfo(getValidApprovedCard(), getValidMonth(), getValidYear(), getValidOwner(), "");
    }

}