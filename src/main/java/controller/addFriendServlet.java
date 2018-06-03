package controller;

import service.AddService;
import util.DBCPFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
@WebServlet("/addFriend")
public class addFriendServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String tousername = request.getParameter("tousername");
        Connection connection = DBCPFactory.getConnection();
        if (AddService.isExsit(username, tousername)) {
            response.getWriter().print("{\"code\": 1}");
        } else {
            String sql = "INSERT INTO friends (username ,tousername,status) VALUES(?,?,?)";
            try {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, tousername);
                pst.setInt(3, 1);
                pst.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.getWriter().print("{\"code\": 0,\"username\":\"" + username + "\", \"tousername\": \"" + tousername + "\"}");
        }
    }
}
