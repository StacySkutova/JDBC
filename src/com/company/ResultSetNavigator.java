package com.company;
import java.sql.*;

public class ResultSetNavigator {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pr";

    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws
            ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver");
        Class.forName(JDBC_DRIVER);
        System.out.println("Creating database connection");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        try {
            statement = connection.createStatement(
               ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            String SQL = "SELECT * FROM developers";
            ResultSet resultSet = statement.executeQuery(SQL);

            System.out.println("Moving cursor to the last position...");
            resultSet.last();

            System.out.println("Setting record");

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String speciality = resultSet.getString("speciality");
            int salary = resultSet.getInt("salary");

            System.out.println("\n******************************\n");
            System.out.println("id:" + id);
            System.out.println("name:" + name);
            System.out.println("speciality:" + speciality);
            System.out.println("salary: $" + salary);

            System.out.println("Moving cursor to the previous...");
            resultSet.previous();

            System.out.println("Setting record");

            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            speciality = resultSet.getString("speciality");
            salary = resultSet.getInt("salary");

            System.out.println("\n******************************\n");
            System.out.println("id:" + id);
            System.out.println("name:" + name);
            System.out.println("speciality:" + speciality);
            System.out.println("salary: $" + salary);

            System.out.println("Moving cursor to the first...");
            resultSet.first();

            System.out.println("Setting record");

            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            speciality = resultSet.getString("speciality");
            salary = resultSet.getInt("salary");

            System.out.println("\n******************************\n");
            System.out.println("id:" + id);
            System.out.println("name:" + name);
            System.out.println("speciality:" + speciality);
            System.out.println("salary: $" + salary);

            System.out.println("Adding record");
            SQL = "INSERT INTO developers VALUES (6, 'Adam', 'JavaScript', 5000)";

            statement.executeUpdate(SQL);

            SQL = "SELECT * FROM developers";
            resultSet = statement.executeQuery(SQL);
            resultSet.last();

            while (resultSet.next())
            {   id = resultSet.getInt("id");
                name = resultSet.getString("name");
                speciality = resultSet.getString("speciality");
                salary = resultSet.getInt("salary");

                System.out.println("\n******************************\n");
                System.out.println("id:" + id);
                System.out.println("name:" + name);
                System.out.println("speciality:" + speciality);
                System.out.println("salary: $" + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
