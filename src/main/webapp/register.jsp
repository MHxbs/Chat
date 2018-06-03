<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 2018/2/13
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
   <%-- <script type="text/javascript">
        $(document).ready(function(){
            $("a").click(function(){
                $.ajax({
                    type:'post',
                    url:'http://localhost/registerServlet',
                    data:$("#myform").serialize(),
                    cache:false,
                    dataType:'json',
                    success:function(data){
                        alert("请求成功");
                    }
                });
            });
        });
    </script>--%>
    <title>register</title>
</head>
<body>
    <form action="http://hong.s1.natapp.cc/Chat-1.0-SNAPSHOT/registerServlet" method="post">
        <h1>注册界面</h1>
        username:<input name="username" type="text"/><br>
        password:<input name="password" type="password"/>
        <input type="submit" value="submit"/>
    </form>


    <%--<form action="" id="myform">
        用户名<input type="text" name="username"/>
        密码<input type="password" name="password"/>

    </form>

    <a href="#" style="text-decoration: none;">使用ajax提交表单数据</a>--%>
</body>
</html>
