package controller;

import util.DBCPFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Connection conn= DBCPFactory.getConnection();
        String sql1 = "SELECT username,password FROM users where username= ?";
        PreparedStatement pst = null;
        try {// 查找用户
            pst = conn.prepareStatement(sql1);
            pst.setString(1, username);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()){
                String passwordFromMysql = resultSet.getString("password");
                if (password.equals(passwordFromMysql)){

                    HttpSession session=request.getSession();
                    session.setAttribute("username",username);
                    request.getRequestDispatcher("/chatRoom.jsp").forward(request,response);
                }else {
                   // response.getWriter().print("{\"code\" : 1}");// 1密码错误
                   response.getWriter().print("<p>密码错误</p>");
                }
            }else {
                /*response.getWriter().print("{\"code\" : 2}");// 2没有该用户*/

                response.getWriter().print("用户名错误，没有该用户");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
