<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Excel Upload in Datenbank</title>
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
<main class="page-content">
    <h4>Bitte w&auml;hlen Sie die Buchungsliste zum Hochladen in die Datenbank aus</h4>
    <form action="${pageContext.request.contextPath}" method="post" enctype="multipart/form-data">
        <input type="file" name="datei" accept=".xls" required />
        <p style="font-size: 11px">Unterst&uuml;tzte Dateiformate: .xls</p>
        <a href="uploadresult.jsp"><button type="submit">Senden</button></a>
    </form>
</main>
<br><br>
<% if (request.getAttribute("fehler") != null) { %>
<p style="font-weight: bold;">
    <%= request.getAttribute("fehler") %>
</p>
<% } %>
</body>
</html>