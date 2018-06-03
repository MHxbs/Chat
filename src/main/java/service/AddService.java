package service;

import util.DBCPFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddService {
    public static boolean isExsit(String username,String tousername){
        Connection conn= DBCPFactory.getConnection();

        String sql="SELECT * FROM friends WHERE username =?";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            ResultSet resultSet=pst.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString("tousername").equals(tousername)){
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
