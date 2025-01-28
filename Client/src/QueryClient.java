import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class QueryClient {

    public static void main(String[] args) {
        try {
            // Definisci la query SQL
            String query = "SELECT * FROM ville";
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String urlString = "http://localhost:8080/VilleVenete_war_exploded/ville-servlet?query=" + encodedQuery;

            // Crea la connessione
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Leggi la risposta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Stampa la risposta JSON
            System.out.println("Response: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}