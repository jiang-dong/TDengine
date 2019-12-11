import com.taosdata.jdbc.TSDBDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;


public class TDengine_jdbc {
    public static void main(String[] args) throws Exception {
        Connection conn = getConn();
        System.out.println(conn);
        Statement stmt = conn.createStatement();
// create database
        stmt.executeUpdate("create database if not exists db1");
// use database
        stmt.executeUpdate("use db1");
// create table
        stmt.executeUpdate("create table if not exists tb (ts timestamp, temperature int, humidity float)");


    conn.close();

    }




    public static Connection getConn() throws Exception {
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        String jdbcUrl = "jdbc:TAOS://192.168.100.100:6030/log?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_USER, "root");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_PASSWORD, "taosdata");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_CONFIG_DIR, "/etc/taos");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_CHARSET, "UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_LOCALE, "en_US.UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_TIME_ZONE, "UTC-8");
        Connection conn = DriverManager.getConnection(jdbcUrl, connProps);
        return conn;
    }


}
