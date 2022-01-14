package com.company;
import java.sql.*;

public class PreparedStatementDemo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pr";

    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws
            ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        System.out.println("Registering JDBC driver");
        Class.forName(JDBC_DRIVER);
        System.out.println("Creating database connection");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        try {
            String SQL = "SELECT * FROM developers";
            preparedStatement = connection.prepareStatement(SQL);

            System.out.println("Initial developers table content:");

            ResultSet resultSet = preparedStatement.executeQuery(SQL);
            while (resultSet.next())
            {   int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String speciality = resultSet.getString("speciality");
                int salary = resultSet.getInt("salary");

                System.out.println("\n******************************\n");
                System.out.println("id:" + id);
                System.out.println("name:" + name);
                System.out.println("speciality:" + speciality);
                System.out.println("salary: $" + salary);
            }

            System.out.println("\n******************************\n");

            SQL = "UPDATE developers SET salary=? WHERE speciality=?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, 3500);
            preparedStatement.setString(2, "Java");
            System.out.println("Rows impacted " + preparedStatement.executeUpdate());
            System.out.println("Final developers table content:");

            SQL = "SELECT * FROM developers";

            resultSet = preparedStatement.executeQuery(SQL);

            while (resultSet.next())
            {   int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String speciality = resultSet.getString("speciality");
                int salary = resultSet.getInt("salary");

                System.out.println("\n******************************\n");
                System.out.println("id:" + id);
                System.out.println("name:" + name);
                System.out.println("speciality:" + speciality);
                System.out.println("salary: $" + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
