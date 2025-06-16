package stockstalker.service;

import stockstalker.model.User;

public interface TradingActions {
    
    boolean sellStock(User user, String symbol, int quantity ) throws Exception;
    boolean buyStock(User user, String symbol , int quantity) throws Exception;
}
