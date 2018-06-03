package controller;

import util.DBCPFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(value = "/registerServlet")
public class registerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Connection conn= DBCPFactory.getConnection();

        String sql = "SELECT username FROM users where username = ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()){
                /*response.getWriter().print("{\"code\": 1}");// 已有该用户名*/
                response.getWriter().print("已有该用户名");
            }else {
                String sql1 = "INSERT INTO users (username,password) VALUES (?,?)";
                PreparedStatement pst2 = conn.prepareStatement(sql1);
                pst2.setString(1, username);
                pst2.setString(2, password);
                if (pst2.executeUpdate()==1){
                    /*response.getWriter().print("{\"code\": 0}");// 成功创建*/
                    response.sendRedirect("" +
                            "index.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
