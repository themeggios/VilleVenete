<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ville Venete</title>
</head>
<body>
<h1><%= "Ville Venete" %> </h1>
<form action="ville-servlet" method="GET">
    <label for="query">Inserisci la query SQL:</label>
    <input type="text" id="query" name="query" required>
    <button type="submit">Invia</button>
</form>
</body>
</html>