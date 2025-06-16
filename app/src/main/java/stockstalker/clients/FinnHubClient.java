package stockstalker.clients;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import io.github.cdimascio.dotenv.Dotenv;
import stockstalker.config.EnvConfig;
import stockstalker.config.HttpClientProvider;
import stockstalker.model.QuoteResponse;

public class FinnHubClient {
    
    private static final String API_KEY = EnvConfig.get("FINNHUB_API_KEY");
    private static final Gson gson = new Gson();

    public static QuoteResponse getQuote(String stock) throws Exception{
        String url = "https://finnhub.io/api/v1/quote?symbol=" + stock + "&token=" + API_KEY;

        HttpRequest getRequest = HttpRequest.newBuilder()
        .uri(new URI(url))
        .GET()
        .build();
        
        HttpResponse<String> resp = HttpClientProvider.getClient().send(getRequest, BodyHandlers.ofString());
        return gson.fromJson(resp.body(), QuoteResponse.class);
    }
    
}
