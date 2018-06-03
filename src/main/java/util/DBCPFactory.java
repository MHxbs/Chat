package util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPFactory {


    private static Properties properties = new Properties();
    private static DataSource dataSource;
    //加载DBCP配置文件
    static{
        try{



            String path= Thread.currentThread().getContextClassLoader().getResource("dbcp.properties").toString();
            System.out.println(path);
            InputStream is =Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp.properties");
            properties.load(is);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e);
        }

        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //从连接池中获取一个连接
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return connection;
    }
}
