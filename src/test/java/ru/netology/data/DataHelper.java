package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private static final Faker FAKER = new Faker();

    private DataHelper() {
    }
        @Value
        public static class ApprovedCardInfo {
            String cardNumber;
            String month;
            String year;
            String owner;
            String cvc;
        }

    public static ApprovedCardInfo getApprovedCardInfo() {
        return new ApprovedCardInfo("1111_2222_3333_4444", "12", "26", "IVAN_IVANOV", "123");
    }
    @Value
    public static class DeclinedCardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static DeclinedCardInfo getDeclinedCardInfo() {
        return new DeclinedCardInfo("5555_6666_7777_8888", "12", "26", "IVAN_IVANOV", "123");
    }
}