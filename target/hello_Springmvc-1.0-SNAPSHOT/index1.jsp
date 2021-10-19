<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2021/10/13
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
    <script type="text/javascript">
        $(function (){
            $("button").click(function (){

                $.ajax({
                    url:"returnVoid.do",
                    data:{
                        name:"zhangsan",
                        age:20
                    },
                    type:"post",
                    dataType:"json",

                    success:function (resp){
                        alert(resp)
                    }
                })
            })
        })

    </script>
</head>
<body>
<p>第一个springmvc项目</p>
<p>
    <a href="some.do">发起some.do请求</a>
</p>
<h2>提交参数给Controller</h2>

<form action="receiveproperty.do" method="post">
    姓名：<input type="text" name="name"><br/>
    年龄：<input type="text" name="age"><br/>
    <input type="submit" value="提交参数">
</form>

<button id="btn">发起ajax请求</button>

</body>
</html>
