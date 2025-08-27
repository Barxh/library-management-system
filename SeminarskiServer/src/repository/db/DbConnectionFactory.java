/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import util.ServerConstants;

/**
 *
 * @author nikol
 */
public class DbConnectionFactory {

    private Connection connection;
    private static DbConnectionFactory instance;

    private DbConnectionFactory() {
    }

    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(ServerConstants.DB_CONFIG_FILE_PATH));
            String url = properties.getProperty(ServerConstants.DB_CONFIG_URL);
            String username = properties.getProperty(ServerConstants.DB_CONFIG_USERNAME);
            String password = properties.getProperty(ServerConstants.DB_CONFIG_PASSWORD);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
