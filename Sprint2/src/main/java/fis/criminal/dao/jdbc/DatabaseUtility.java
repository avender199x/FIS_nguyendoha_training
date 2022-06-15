package fis.criminal.dao.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {
    public final static String URL = "jdbc:mysql://localhost:3306/nguyendoha_fis";
    public final static String USER_NAME = "root";
    public final static String PASSWORD = "";

    public static ComboPooledDataSource getDataSource()
            throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(URL);
        cpds.setUser(USER_NAME);
        cpds.setPassword(PASSWORD);

        // Optional Settings
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(100);

        return cpds;
    }

    public static Connection getConnection() throws Exception {
        Connection con = ds.getConnection();
        return con;
    }

    private static HikariConfig config = new HikariConfig("/datasource.properties");
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource(config);
    }
}
