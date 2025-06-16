package stockstalker.service;

import javax.management.RuntimeErrorException;

import stockstalker.clients.FinnHubClient;
import stockstalker.model.QuoteResponse;
import stockstalker.model.User;

public class TradingEngine implements TradingActions{

    private static final Error IllegalArgumentException = null;

    @Override
    public boolean sellStock(User user, String symbol, int quantity) throws Exception {
        if (quantity < 1 ){
            throw new RuntimeErrorException(IllegalArgumentException, "quantity must be > 1 ");
        }

        if(!user.getStockToNumberOwned().containsKey(symbol)){
            throw new RuntimeErrorException(IllegalArgumentException, "quantity must be > 1 ");
        };

        if (user.getStockToNumberOwned().get(symbol) < quantity){
            throw new RuntimeErrorException(IllegalArgumentException, "You do not have enough of " + symbol + " stock to sell" + quantity + " stocks");
        }

        QuoteResponse  quoteResponse = FinnHubClient.getQuote(symbol);
        double stockPrice = quoteResponse.getC();
        double currentAccountBalance = user.getAccountBalance();

        user.setAccountBalance(currentAccountBalance + (stockPrice * quantity));

        if((user.getStockToNumberOwned().get(symbol) - quantity) == 0 ) {
            user.getStockToNumberOwned().remove(symbol);
        }

        return true;

    }

    @Override
    public boolean buyStock(User user, String symbol, int quantity) throws Exception {
        QuoteResponse  quoteResponse = FinnHubClient.getQuote(symbol);
        double stockPrice = quoteResponse.getC();
        if(user.getAccountBalance() < (stockPrice * quantity)){
            throw new RuntimeErrorException(IllegalArgumentException);
        }

        user.setAccountBalance(user.getAccountBalance() - (stockPrice * quantity));
        user.setAmountInStocks(user.getAmountInStocks() + (stockPrice * quantity));
        if(!user.getStockToNumberOwned().containsKey(symbol)){
            user.getStockToNumberOwned().put(symbol, quantity);
        }else{
            int currentCount = user.getStockToNumberOwned().get(symbol);
            user.getStockToNumberOwned().put(symbol, quantity + currentCount);
        }
        return true;

    }
    


}
