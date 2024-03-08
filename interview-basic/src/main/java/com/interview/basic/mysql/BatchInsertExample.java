package com.interview.basic.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsertExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://47.97.17.75:3311/tuling";
        String username = "root";
        String password = "123456";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            //connection.setAutoCommit(false);

            String sql = "INSERT INTO employees (name,age,position) VALUES (?, ?, ?)";


            //  insert into employees(name,age,position) values(CONCAT('zhuge',i),i,'dev');
            // 添加批量数据
            for (int j = 500; j < 800; j++) {
                System.out.println("j==>" + j);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < 200; i++) {
                    int a = j * 100 + i;
                    preparedStatement.setString(1, "zhuge" + a);
                    preparedStatement.setInt(2, a);
                    preparedStatement.setString(3, "dev");
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }


            // 执行批量插入

            //connection.commit();

            System.out.println("批量插入成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}