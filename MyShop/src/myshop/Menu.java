package myshop;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    File f = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\MyShop\\shoop.xls");
    File file = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\MyShop\\sale.xls");
    private Shop sh = new Shop(f, file);
    Scanner s = new Scanner(System.in);
    
    public void SaleMenu(){
        ArrayList<Product> product = this.sh.getGood();
        for (Product p : product){
            System.out.println(p.toString());
        }
        boolean f = true;
        while(f){  
            System.out.println("\n");
            System.out.println("1. Сформировать продажу");
            System.out.println("2. Отменить продажу");
            System.out.println("3. Вернуться в главное меню");
            System.out.println("\n");
            System.out.println("Введите название или id и количество товара: ");
            String k = s.nextLine();
            if (k.contains(" ")) {
                int pos = k.indexOf(" ");
                String st = k.substring(0, pos);
                int count = Integer.parseInt(k.substring(pos + 1, k.length()));
                int answer = sh.BuyByName(st, count);
                if (answer == -1){
                    int id = Integer.parseInt(st);
                    int ret = sh.BuyById(id, count);
                    if (ret == -1) System.out.println("Товар не найден");
                }
            }
            else {
                switch(k) {
                    case "1": {
                        sh.addSales(new Sale(sh.copySale()));
                        this.sh.clearSale();
                        System.out.println("Продажа сформирована");
                        break;
                    }
                    case "2": {
                        this.sh.cancelSale();
                        break;
                    }
                    case "3": {
                        f = !f;
                        break;
                    }
                }
            }
        
        }
    }
    
    public void supplyMenu(){
        ArrayList<Product> product = this.sh.getGood();
        boolean f = true;
        while(f){
            System.out.println("1. Выход в главное меню");
            System.out.println("Введите id и количество товара:");
            String k = s.nextLine();
            
            if (k.contains(" ")){               
                int pos = k.indexOf(" ");
                int id = Integer.parseInt(k.substring(0, pos));
                int count = Integer.parseInt(k.substring(pos + 1, k.length()));
                int answer = sh.SaleById(id, count);
                if (answer == -1) {
                    System.out.println("Товар не существует");
                    System.out.println("Введите название, цену и количество товара: ");
                    String add = s.nextLine();
                    pos = add.indexOf(" ");
                    String name = add.substring(0, pos);
                    int position = add.lastIndexOf(" ");
                    int price = Integer.parseInt(add.substring(pos + 1, position));
                    count = Integer.parseInt(add.substring(position + 1, add.length()));
                    sh.AddGood(new Product(id, name, price, count)); 
                    System.out.println("Товар добавлен");
                }
            }
                else {
                    switch(k){
                        case "1": {
                            f = !f;
                            break;
                        } 
                    }
                }
                
            }
    }
    
    public void infoSale(){
        boolean f = true;
        for (int i = 0; i < sh.salesLenght(); i++) {
             System.out.println(i + 1 + " " + sh.getSale(i).getSum());                   
        }
        while (f) {
            System.out.println("0. Вернуться в главное меню");
            System.out.println("Введите id интересующей продажи: ");
            String k = s.nextLine();
            switch(k) {
                case "0": {
                    f = !f;
                    break;
                }
                default : {
                    int id = Integer.parseInt(k);
                    id -= 1;
                    if (id > sh.salesLenght()) {
                        System.out.println("Продажа не существует");
                    }
                    else  {
                        for(int i = 0; i < sh.getSale(id).getLength(); i++) {
                            Product buy = sh.getSale(i).getProduct(i);
                            System.out.println(buy.getName() + " " + buy.getCount());
                        }
                    }
                }
            }
        }
    }
    
    public void mainMenu(){       
        boolean f = true;        
        while(f){
            System.out.println("1. Продажа товара");
            System.out.println("2. Поставка товара");
            System.out.println("3. Данные по продажам");
            System.out.println("4. Выход");
            System.out.print("Введите операцию: ");
            String k = s.nextLine();
            switch(k){
                case "1": {
                    this.SaleMenu();
                    break;
                }
                case "2": {
                    this.supplyMenu();
                    break;
                }                 
                case "3": {
                    this.infoSale();
                    break;
                }
                case "4": {
                    f = !f;
                    sh.InfoToSale();
                    sh.SaleToExcel();
                    break;
                }    
            }
        }   
    }
}