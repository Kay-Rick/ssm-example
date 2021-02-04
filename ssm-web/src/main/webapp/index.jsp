<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>测试Ajax</h2>
    <input type="button" id="btn" value="获取数据"/>
    <table align="center">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Age</td>
        </tr>
        <tbody id="content">

        </tbody>
    </table>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h2>测试文件上传</h2>
    ${msg}
    <form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
        用户名：<input type="text" name="username" /><br>
        头像：<input type="file" name="photo" /><br>
        <!-- 多文件上传要加 mutiple -->
        文件：<input type="file" name="files" multiple></br>
        <input type="submit" value="上传">
    </form>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    url:"${pageContext.request.contextPath}/ajax/a",
                    method:"POST",
                    data:{"name":"Rick"},
                    success:function (data) {
                        console.log(data);
                        var res = "";
                        for (var i = 0; i < data.length; i++) {
                            res += "<tr>" +
                                "<td>" + data[i].id + "</td>" +
                                "<td>" + data[i].name + "</td>" +
                                "<td>" + data[i].age + "</td>" +
                                "</tr>"
                        }
                         $("#content").html(res);
                    }
                });
            })
        })
    </script>
</body>
</html>
