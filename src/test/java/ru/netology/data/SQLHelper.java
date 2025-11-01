package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/buy_a_trip", "app", "pass");
    }


    @SneakyThrows
    public static String getLastPaymentStatus() {
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConnection()) {
            return runner.query(conn, statusSQL, new ScalarHandler<String>());
        }
    }

    @SneakyThrows
    public static String getLastCreditStatus() {
        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConnection()) {
            return runner.query(conn, statusSQL, new ScalarHandler<String>());
        }
    }

    @SneakyThrows
    public static void clearDatabase() {
        try (var conn = getConnection()) {
            runner.update(conn, "DELETE FROM credit_request_entity");
            runner.update(conn, "DELETE FROM order_entity");
            runner.update(conn, "DELETE FROM payment_entity");
        }
    }
}