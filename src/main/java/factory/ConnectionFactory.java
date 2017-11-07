package factory;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.concurrent.Executors;

public class ConnectionFactory {

    private static Connection connection = null;

    public static Connection createConnection() throws URISyntaxException, SQLException {
        String username = "eslvxidpytncgz";
        String password = "x-U6SDenKqe9rQrI3gNd45kdIr";
        String dbUrl = "jdbc:postgresql://ec2-54-243-201-3.compute-1.amazonaws.com:5432/d4fug1on08dodq";
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void closeConnection(Connection conexao, Statement pstmt){

        try {
            if(conexao != null){
                conexao.close();
            }
            if(pstmt != null){
                pstmt.close();
            }

        } catch (Exception e) {
            System.out.println("Error in finish connection with bd");
        }
    }

    protected static void connect() {
        try {
            connection = createConnection();
            connection.setNetworkTimeout(null, 300000);
            System.out.println("Creating statement...");
        }catch (SQLException e){
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        try {
            return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

