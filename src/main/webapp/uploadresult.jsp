
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ergebnis</title>
    <style>
        body {
            font-family: verdana, serif;
            font-size: 13px;
            background-color: #9FB6CD;
            background-size: auto;
            background-position: center;
            min-height: 100vh;
        }

        .page-content {
            flex: 1;
            background-color:#e0e0e0;
            padding: 44px;
            max-width: 1111px;
            margin: 111px auto;
            border-radius: 22px;
            min-height: 77vh;
        }
    </style>
</head>
<body>
<% if (request.getAttribute("fehler") != null) { %>
<p style="font-weight: bold;">
    <%= request.getAttribute("fehler") %>
</p>
<% } %>
<a href="upload.jsp">Zur&uuml;ck zum Formular</a>
</body>
</html>
