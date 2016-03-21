package myshop;

public class Product {
    private int id = -1;
    private String name = "";
    private int price = -1;
    private int count = -1;
    
    public Product(int id, String name, int price, int count){
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }
    
    public void buyProduct(int k){
       if (k > this.count) System.out.println("Недостоточное количество товаров");
       else this.count -= k;
    }
    
    public void addProduct (int n){
        this.count += n;
    }
    
    public int getId(){ return this.id; }
    public String getName(){ return this.name; }
    public int getPrice(){ return this.price; }
    public int getCount(){ return this.count; }
    
    public String toString() { 
        String s = Integer.toString(this.id);
        String d = Integer.toString(this.price);
        String f = Integer.toString(this.count);
        return s + " " + this.name + " " + d  + " "+ f;        
    }
}