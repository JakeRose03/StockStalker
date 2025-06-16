package stockstalker.worker;

import java.time.Duration;

import lombok.AllArgsConstructor;
import stockstalker.clients.FinnHubClient;
import stockstalker.model.QuoteResponse;


@AllArgsConstructor
public class StockStalker extends Thread {


    private int threadNumber;
    private String stockName;
    private int price;
    

    public void run(){

        while (true){
        try {
            QuoteResponse quoteResponse = FinnHubClient.getQuote(stockName);
            if (quoteResponse.getC() < price){
                System.out.println(stockName + " dipped below $" + price + " from thread: " + threadNumber );
                break;
            }else{
                System.out.println(stockName + " is still above " + price + "from thread: " + threadNumber);
                System.out.println("The price you are tacking for " + stockName + " is $" + price );
            }
        } catch (Exception e) {
            System.out.println("idk some error");
        }
        try {
            sleep(Duration.ofSeconds(30));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

}