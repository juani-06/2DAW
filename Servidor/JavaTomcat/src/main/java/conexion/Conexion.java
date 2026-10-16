package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static BasicDataSource dataSource = null;

    private static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setUrl("jdbc:mysql://localhost:3306/crud?useTimezone=true&serverTimezone=UTC");
            dataSource.setInitialSize(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxTotal(20);
            dataSource.setMaxWait(Duration.ofSeconds(5));
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}