package JDBC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

class JDBCconnector {

    public static void main(String[] args) throws IOException {
        String url = "jdbc:mysql://localhost:3306/baza1?serverTimezone=UTC" + "&useSSL=false";
//        String databaseUsername = "root";
//        String databasePassword = "password";
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("C:\\Java\\databaseJDBCconnect\\src\\main\\resources\\database.properties")));
//        properties.put("user", databaseUsername);
//        properties.put("password", databasePassword);
        try (Connection connection = DriverManager.getConnection(url, properties)){
            createStatementInsert(connection);
            createStatementSelect(connection);
            createStatementUpdate(connection);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createStatementUpdate(Connection connection) {
        Integer id = 5;
        String newName = "KuraKimono";
        String query = "UPDATE `baza1`.`uzytkownicy` SET `NAZWISKO` = ?, `DATA_URODZENIA` = ? WHERE (`ID` = ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setString(2, "2009-01-02");
            statement.setInt(3, id);
            boolean execute = statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void createStatementSelect(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM baza1.uzytkownicy");
            doSomethingWith(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void doSomethingWith(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet);
        while (resultSet.next()) {
            int column1 = resultSet.getInt(1);
            String column2 = resultSet.getString(2);
            String column3 = resultSet.getString(3);
            String column4 = resultSet.getString(4);
            String column5 = resultSet.getString(5);
            String column6 = resultSet.getString(6);
            int column7 = resultSet.getInt(7);
            String column8 = resultSet.getString(8);
            int column9 = resultSet.getInt(9);
            int column10 = resultSet.getInt(10);
            String column11 = resultSet.getString(11);
            String row = new StringJoiner(", ")
                    .add(String.valueOf(column1)).add(column2)
                    .add(column3).add(column4).add(column5)
                    .add(column6).add(String.valueOf(column7))
                    .add(column8).add(String.valueOf(column9))
                    .add(String.valueOf(column11))
                    .toString();

            System.out.println(row);
        }
        resultSet.close();
    }
    private static void createStatementInsert(Connection connection) {
        Integer id = 6;
        String name = "Zenek";
        String surname = "Kimono";
        String query = "INSERT INTO `baza1`.`uzytkownicy` (`ID`, `IMIE`, `NAZWISKO`, `MIASTO`, `ULICA`, `KOD_POCZTOWY`, `NR_DOMU`, `DATA_URODZENIA`, `waga`, `wzrost`, `KRAJ`) VALUES (?,?,?,?,?,?,?,?,?,?,?);\n";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, "Bialystok");
            statement.setString(5, "Kwiatowa");
            statement.setString(6, "15-191");
            statement.setInt(7, 12);
            statement.setString(8, "200-01-01");
            statement.setInt(9, 120);
            statement.setInt(10, 199);
            statement.setString(11, "POL");
            boolean execute = statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
