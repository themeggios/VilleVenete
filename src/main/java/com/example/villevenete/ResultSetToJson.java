package com.example.villevenete;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultSetToJson {
    public static String convertToJson(ResultSet resultSet) {
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    jsonObject.addProperty(columnName, resultSet.getString(i)); // Converte i valori in stringa
                }
                jsonArray.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.toJson(jsonArray);
    }
}
