<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 2018/2/12
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <title>index</title>
    <%--<script type="text/javascript">
        $(document).ready(function(){
            $("a").click(function(){
                $.ajax({
                    type:'post',
                    url:'http://localhost/loginServlet',
                    data:$("#myform").serialize(),
                    cache:false,
                    dataType:'json',
                    success:function(data){
                        alert("请求成功");
                    }
                });
            });
        });
    </script>
--%>
</head>
<body>
<form action="http://hong.s1.natapp.cc/Chat-1.0-SNAPSHOT/loginServlet" method="POST">
    username:<input name="username" type="text"/><br>
    password:<input name="password" type="password"/><br>
    <input type="submit" value="提交" id="login"/>
    <a href="register.jsp">注册</a>
</form>


<%--<form action="" id="myform">
    用户名<input type="text" name="username"/>
    密码<input type="password" name="password"/>

</form>

<a href="#" style="text-decoration: none;">使用ajax提交表单数据</a>--%>
</body>
</html>
