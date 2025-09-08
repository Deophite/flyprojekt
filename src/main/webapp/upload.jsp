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
    <h4 id="headline">Bitte w&auml;hlen Sie die Buchungsliste zum Hochladen in die Datenbank aus</h4>
    <form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data" id="form">
        <input type="file" name="datei" accept=".xls" required />
        <p style="font-size: 11px">Unterst&uuml;tzte Dateiformate: .xls</p>
        <button type="submit">Senden</button>
    </form>
    <p id="onSubmit1" style="display: none">Ihre Datei wird in die Datenbank gespeichert. Haben Sie ein bisschen Geduld. Das kann bis paar Minuten dauern!</p>
    <p id="result"></p>
    <p id="timer" style="display: none"></p>
</main>

<script>
    // JS - Form wird ausgewählt dann beim Submit wird default gestoppt. Nachdem JS übernimmt das Control
    // und sendet die Form zu UploadServlet. Dann erwartet auf Response in dem entweder kein Fehler oder Fehler kommt.
    // Die Fehlermeldung/Result wird in dem Element "result" auf dem Bildschirm gezeigt

      const form =  document.getElementById("form");
      const btn = document.getElementById("submit");
      const result = document.getElementById("result");
      const waitMessage = document.getElementById("onSubmit1");
      const timer = document.getElementById("timer");
      const headline = document.getElementById("headline");

      let isReady = false;
      let seconds = 0;

      function count () {

         let minutes = 0;
         let sec = 0;

          if (seconds > 59) {
            minutes = Math.floor(seconds / 60);
          }
          sec = Math.floor(seconds % 60);

          if (sec < 10) {
              timer.innerHTML = minutes + ':' + "0" + sec;
          } else {
              timer.innerHTML = minutes + ':' + sec;
          }
          seconds += 1;
      }


          form.addEventListener("submit", function asd (e) {
              e.preventDefault();
              if (isReady) {
                  result.innerHTML= "";
              }
              form.setAttribute("style", "display: none");
              headline.setAttribute("style", "display: none");
              waitMessage.setAttribute("style", "display: block");
              timer.setAttribute("style", "display: block");

              setInterval(count, 1000);


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
                  let zeit = timer.innerHTML;
                  timer.setAttribute("style", "display: none");
                  result.innerHTML = res + " " + zeit;
                  seconds = 0;
                  form.setAttribute("style", "display: block");
                  headline.setAttribute("style", "display: block");
              }).catch(err => console.log(err));
          });


</script>

</body>
</html>