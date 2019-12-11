import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

public class TDengine_Druid {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName","com.taosdata.jdbc.TSDBDriver");
        properties.put("url","jdbc:TAOS://127.0.0.1:6030/log");
        properties.put("username","root");
        properties.put("password","taosdata");
        properties.put("maxActive","10"); //maximum number of connection in the pool
        properties.put("initialSize","3");//initial number of connection
        properties.put("maxWait","10000");//maximum wait milliseconds for get connection from pool
        properties.put("minIdle","3");//minimum number of connection in the pool
        properties.put("timeBetweenEvictionRunsMillis","3000");// the interval milliseconds to test connection
        properties.put("minEvictableIdleTimeMillis","60000");//the minimum milliseconds to keep idle
        properties.put("maxEvictableIdleTimeMillis","90000");//the maximum milliseconds to keep idle
        properties.put("validationQuery","describe log.dn"); //validation query
        properties.put("testWhileIdle","true"); // test connection while idle
        properties.put("testOnBorrow","false"); // don't need while testWhileIdle is true
        properties.put("testOnReturn","false"); // don't need while testWhileIdle is true
        //create druid datasource
        DataSource ds = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = ds.getConnection(); // get connection
        Statement statement = connection.createStatement(); // get statement
        //query or insert


        // ...
        connection.close(); // put back to conneciton pool
    }
}
