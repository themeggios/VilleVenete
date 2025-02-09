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

            // Converti il ResultSet in JSON
            String json = ResultSetToJson.convertToJson(rs);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Invia il JSON direttamente al client
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}