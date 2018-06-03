<%--
  Created by IntelliJ IDEA.
  User: meng
  Date: 2018/5/23
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String username=request.getParameter("username");
String name= (String) request.getSession().getAttribute("usernmae");%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

    <script type="text/javascript">

        var username="<%=username%>";
        var ws = new WebSocket("ws:hong.s1.natapp.cc/Chat-1.0-SNAPSHOT/webSocket/"+username);
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
            var str=message;
            if (str.indexOf("+++")!=-1){
                haoyousengqing(str);
            }else {
                var text = document.createTextNode(message);
                var br = document.createElement("br")
                var div = document.getElementById("showChatMessage");
                div.appendChild(text);
                div.appendChild(br);
            }
        }
        function friendList(who) {
            $.ajax({
                type:'post',
                url:'http://hong.s1.natapp.cc/Chat-1.0-SNAPSHOT/getFriends',
                data:{"username":who},
                cache:false,
                dataType:'json',
                success:function(data){
                    alert("请求成功");
                    $("#friendList").html("");
                    $("#friendList").html("<button id=\"getFriendList\" onclick=\"friendList(username)\">好友列表</button>\n");
                    for (var i=0;i<data.friends.length;i++) {

                        $("#friendList").append("<br>" +
                            "<a id='privateChat' href='SingleCharRoom.jsp'>"+data.friends[i]+"</a>");
                    }

                }
            });
        }


        function addFriend(friendName) {
            /*var friendName=addFriendName.value;*/
            $.ajax({
                type:'post',
                url:'http://hong.s1.natapp.cc/Chat-1.0-SNAPSHOT/addFriend',
                data:{"username":username,"tousername": friendName},
                cache:false,
                dataType:'json',
                success:function(data){
                    if (data.code==1){
                        alert("你们已经是好友了")
                    }else {
                        alert("请求成功")
                        ws.send(data.username + ">>" + data.tousername);
                    }
                }
            });
        }

        function haoyousengqing(message) {
            var str=message;
            var arr=str.split("|");
             username1=arr[0];
             tousername=arr[2];

            $("#friendList").append("<b>"+username1+"请求加你为好友"+"</b>" +
                "<button id='add' onclick='add(tousername,username1)'>同意</button>")
        }
       function add(str1,str2){
            $.ajax({
                type:'post',
                url:'http://hong.s1.natapp.cc/Chat-1.0-SNAPSHOT/addFriend',
                data:{"username":str1,"tousername":str2},
                cache:false,
                dataType:'json',
                success:function(data){
                    if (data.code==1){
                        alert("你们已经是好友了")
                    }else {
                        alert("请求成功");
                    }
                }
            });
        }


    </script>
</head>
<body>
<b>发送信息时 最后加->username 发送私人信息
    如：你好 ->孟宏</b>
<br>
<b>直接发送信息，就是发给所有在这个聊天室的人</b>
<br>
<br>
<b>
    好友列表点击一次就会看到你的好友
</b>
<br>
<b>
    添加好友，输入他的username，然后他会收到你的好友申请
</b>
<br>
<br>

<div id="user">
    <h2>我的username：<%=username%></h2>
</div>
<div
        style="width: 600px; height: 240px; overflow-y: auto; border: 1px solid #333;"
        id="show">
    <div id="showChatMessage"></div>
    <input type="text" size="80" id="msg" name="msg" placeholder="please input" />
    <input type="button" value="send" id="sendBn" name="sendBn"
           onclick="send()">
</div>

<div id="friendList">
<button id="getFriendList" onclick="friendList(username)">好友列表</button>
</div>

<div id="addFriend">
    <input type="text" id="addFriendName">
    <button id="addFriendButton" onclick="addFriend(addFriendName.value)">添加</button>
</div>

<a href="robotChat.jsp">与图灵机器人聊天</a>
<%--<div id="addFriend">
<form action="" id="myform">
    朋友名<input type="text" name="tousername"/>


</form>

<a href="#" style="text-decoration: none;">添加</a>
</div>--%>

</body>
</html>