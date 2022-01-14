package com.company;
import java.sql.*;

public class DevelopersJdbcDemo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // подключаем коннектор
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pr"; // обозначаем базу данных

    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String [] args) throws
            ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver");
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Creating database connection");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT * FROM developers";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Data from database");
        System.out.println("\nDevelopers:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String speciality = resultSet.getString("speciality");
            int salary = resultSet.getInt("salary");

            System.out.println("\n******************************\n");
            System.out.println("id:" + id);
            System.out.println("name:" + name);
            System.out.println("speciality:" + speciality);
            System.out.println("salary: $" + salary);
        }

            System.out.println("Closing connection and releasing resources");
            resultSet.close();
            statement.close();
            connection.close();
    }
}
