package stockstalker;
import java.net.http.HttpClient;

public class HttpClientProvider {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static HttpClient getClient() {
        return client;
    }
    
}
