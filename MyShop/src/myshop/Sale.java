package myshop;

import java.util.ArrayList;

public class Sale {
    private int sum = 0;
    private ArrayList<Product> buy = new ArrayList();
        
    public Sale (ArrayList<Product> buy){
        for (Product buy1 : buy) {
            sum += buy1.getCount() * buy1.getPrice();            
        }        
        this.buy = buy;
    }
    
    public int getSum(){ return sum; }
    
    public int getLength() { return buy.size(); }
    
    public Product getProduct(int i) { return buy.get(i); }   
}