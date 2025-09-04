package org.projekt30grp4.spring.flyprojekt;


import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "UploadView", value="/upload")

public class UploadView extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"de\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Excel Upload in Datenbank</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: verdana, serif;\n" +
                "            font-size: 13px;\n" +
                "            background-color: #9FB6CD;\n" +
                "            background-size: auto;\n" +
                "            background-position: center;\n" +
                "            min-height: 100vh;\n" +
                "        }\n" +
                "\n" +
                "        .page-content {\n" +
                "            flex: 1;\n" +
                "            background-color:#e0e0e0;\n" +
                "            padding: 44px;\n" +
                "            max-width: 1111px;\n" +
                "            margin: 111px auto;\n" +
                "            border-radius: 22px;\n" +
                "            min-height: 77vh;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<main class=\"page-content\">\n" +
                "    <h4>Bitte w&auml;hlen Sie die Buchungsliste zum Hochladen in die Datenbank aus</h4>\n" +
                "\n" +
                "    <form action=\"/flyprojekt/uploadFile\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "        <input type=\"file\" name=\"datei\" accept=\".xls\" required />\n" +
                "\n" +
                "\n" +
                "        <p style=\"font-size: 11px\">Unterst&uuml;tzte Dateiformate: .xls</p>\n" +
                "\n" +
                "\n" +
                "        <button type=\"submit\">Senden</button>\n" +
                "    </form>\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>");
    }
}