package com.example.villevenete;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "VilleServlet", value = "/ville-servlet")
public class VilleServlet extends HttpServlet {

    private Connection connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/villevenete";
        String user = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = request.getParameter("query");

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Leggi il file JSON
            File jsonFile = new File("result.json");
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
            reader.close();

            // Imposta il tipo di contenuto e invia il JSON come risposta
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}