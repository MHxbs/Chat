
<%--
  Created by IntelliJ IDEA.
  User: meng
  Date: 2018/5/23
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String username=request.getParameter("username");%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        var username=<%=username%>
        var ws = new WebSocket("ws://localhost/webSocketSingle/"+username);
        /*
         *监听三种状态的变化 。js会回调
         */
        ws.onopen = function(message) {

        };
        ws.onclose = function(message) {

        };
        ws.onmessage = function(message) {
            showMessage(message.data);
        };
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function() {
            ws.close();
        };
        //关闭连接
        function closeWebSocket() {
            ws.close();
        }
        //发送消息
        function send() {
            var input = document.getElementById("msg");
            var text = input.value;
            ws.send(text);
            input.value = "";
        }
        function showMessage(message) {
            var text = document.createTextNode(message);
            var br = document.createElement("br")
            var div = document.getElementById("showChatMessage");
            div.appendChild(text);
            div.appendChild(br);
        }
    </script>
</head>
<body>
<div
        style="width: 600px; height: 240px; overflow-y: auto; border: 1px solid #333;"
        id="show">
    <div id="showChatMessage"></div>
    <input type="text" size="80" id="msg" name="msg" placeholder="please input" />
    <input type="button" value="send" id="sendBn" name="sendBn"
           onclick="send()">
</div>
</body>
</html>