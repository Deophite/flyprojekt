<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Projekt Fly Excel - DB</title>

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
    <form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data" id="form">
        <input type="file" name="datei" accept=".xls" required />
        <p style="font-size: 11px">Unterst&uuml;tzte Dateiformate: .xls</p>
        <button type="submit">Senden</button>
    </form>
    <p id="onSubmit1" style="display: none">Ihre Datei werden in die Datenbank gespeichert. Haben Sie ein bisschen Geduld. Das kann bis paar Minuten dauern!</p>
    <p id="result"></p>
</main>

<script>
    // JS - Form wird ausgewählt dann beim Submit wird default gestoppt. Nachdem JS übernimmt das Control
    // und sendet die Form zu UploadServlet. Dann erwartet auf Response in dem entweder kein Fehler oder Fehler kommt.
    // Die Fehlermeldung/Result wird in dem Element "result" auf dem Bildschirm gezeigt

      const form =  document.getElementById("form");
      const btn = document.getElementById("submit");
      const result = document.getElementById("result");
      const waitMessage = document.getElementById("submit");
      let isReady = false;

        form.addEventListener("submit", function asd (e) {
        e.preventDefault();
        if (isReady) {
            result.innerHTML= "";
        }
        waitMessage.setAttribute("style", "display: block");
        const formData = new FormData(this);

        fetch("${pageContext.request.contextPath}/fileUpload", {
            method: "POST",
            body: formData
        }).then(res => res.text()).then((res) => {

            waitMessage.setAttribute("style", "display: none");

            if (res.toString().includes("erfolgreich")) {
                isReady = true;
                result.setAttribute("style", "color: green; font-weight: bold;");
            } else {
                result.setAttribute("style", "color: red");
            }
            result.innerHTML = res;
            }).catch(err => console.log(err));
    });
</script>

</body>
</html>