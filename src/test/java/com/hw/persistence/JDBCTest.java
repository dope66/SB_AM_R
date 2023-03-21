package com.hw.persistence;


import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.AssertionsForClassTypes.fail;


public class JDBCTest {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {

        try(Connection con =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:/?characterEncoding=UTF-8&serverTimezone=UTC",
                            "",
                            "")){
            System.out.println(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
