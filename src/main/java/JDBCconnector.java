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


}
