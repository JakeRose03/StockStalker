package stockstalker.model;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private double accountBalance ;
    private double amountInStocks;
    private HashMap<String , Integer> stockToNumberOwned;

    public User(String name){
        this.name = name;
        accountBalance = 10000;
        stockToNumberOwned = new HashMap<>();
    }




}
