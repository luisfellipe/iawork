package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class DataBase {

    private static Connection connection = null;
    private static String host = "localhost";
    private static String database = "aeroporto";
    private static String url = "jdbc:mysql://";
    private static String username = "root"; // nome de usuario do db
    private static String password = "mysql"; //senha de acesso
    private static String status = "Não conectado!";//status da conexão

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                    url + host + "/" + database,
                    username,
                    password);

        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            System.out.println(e);
        }
        //testa conexão
        if (connection != null) {
            status = "Conectado com sucesso!";
            return connection;
        } else {
            status = " não foi possivel realizar conexão!";
            return null;
        }
    }

    public static boolean close() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection reconect() {
        DataBase.close();
                
        return DataBase.getConnection();
    }

}
