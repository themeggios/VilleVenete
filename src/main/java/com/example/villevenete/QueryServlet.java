package com.example.villevenete;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QueryServlet", value = "/query-servlet")
public class QueryServlet extends HttpServlet {

    private Connection connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/villevenete"; // Sostituisci con il tuo database
        String user = "root"; // Nome utente predefinito di XAMPP
        String password = ""; // Password predefinita di XAMPP è vuota
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = request.getParameter("query");
        List<String> results = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                results.add(rs.getString(1)); // Assuming you want the first column
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Serialize results to JSON
        //Gson gson = new Gson();
        //String json = gson.toJson(results);

        // Set response type and write JSON to response
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(results);
        out.flush();
    }
}