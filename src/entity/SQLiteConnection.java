package entity;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLiteConnection {
    private Connection connection;
    private String dbURL;

    public SQLiteConnection(String dbURL) {
        this.dbURL = dbURL;
        initializeConnection();
    }

    private void initializeConnection() {
        Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.url", "jdbc:sqlite:" + dbURL);
        properties.put("javax.persistence.jdbc.driver", "org.sqlite.JDBC");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");

        try {
            Class.forName("org.sqlite.JDBC");

            // Verifique se a pasta "AFDeveloer" existe, se não, crie-a
            File afDeveloperFolder = new File(System.getProperty("user.home") + File.separator + "documentos" + File.separator + "AFDeveloer");
            if (!afDeveloperFolder.exists()) {
                afDeveloperFolder.mkdirs();
            }

            // Construa o caminho completo para o banco de dados
            String dbPath = afDeveloperFolder.getAbsolutePath() + File.separator + "DBnote.db";

            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath, properties);

            createTableIfNotExists();
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Note ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "description TEXT,"
                + "alarm INTEGER DEFAULT 0,"
                + "date_time_alarm DATETIME"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SQLiteConnection sqliteConnection = new SQLiteConnection("DBnote.db");
        sqliteConnection.closeConnection();
    }
}
