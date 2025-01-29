import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class VilleClient {

    public static void main(String[] args) {
        try {

            // Chiedi la query all'utente
            Scanner scanner = new Scanner(System.in);
            System.out.print("Inserisci la query SQL: ");
            String query = scanner.nextLine();
            scanner.close();

            // Definisci la query SQL
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String urlString = "http://localhost:8080/VilleVenete_war_exploded/ville-servlet?query=" + encodedQuery;

            // Crea la connessione
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Leggi la risposta JSON
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Stampa la risposta JSON ricevuta dalla servlet
            System.out.println("Response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}