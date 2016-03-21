package myshop;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Shop {
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Product> good = new ArrayList<>();
    ArrayList<Product> sale = new ArrayList<>();
    ArrayList<Sale> sales = new ArrayList<>();
    File path = null;
    File file = null;
    
    public Shop(File path, File file){
        try { 
            POIFSFileSystem fileSystem = new POIFSFileSystem(path);
            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);

            Iterator<Row> rows = sheet.rowIterator();

            rows.next();
            
            this.path = path;
            this.file = file;

            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();

                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                int price = (int) row.getCell(2).getNumericCellValue();
                int count = (int) row.getCell(3).getNumericCellValue();

                this.id.add(id);
                this.name.add(name);
                this.good.add(new Product(id, name, price, count));
            }
            
            fileSystem = new POIFSFileSystem(file);
            workBook = new HSSFWorkbook(fileSystem);
            sheet = workBook.getSheetAt(0);

            rows = sheet.rowIterator();
            
            
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                int num = (int)row.getCell(0).getNumericCellValue();
                ArrayList<Product> pr = new ArrayList<>();
                for (int i = 0; i < num; i++){
                    row = (HSSFRow)rows.next();
                    int id = (int)row.getCell(0).getNumericCellValue();
                    String name = (String)row.getCell(1).getStringCellValue();
                    int price = (int)row.getCell(2).getNumericCellValue();
                    int count = (int)row.getCell(3).getNumericCellValue();
                    Product p = new Product(id, name, price, count);
                    pr.add(p);                    
                }
                this.sales.add(new Sale(pr));                
            }
        } catch (Exception e) {}
    }
    
    public void InfoToSale(){
        try { 
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet sheet = workBook.createSheet();
            
            HSSFRow r = sheet.createRow(0);
            
            r.createCell(0).setCellValue("id");
            r.createCell(1).setCellValue("name");
            r.createCell(2).setCellValue("price");
            r.createCell(3).setCellValue("count");
            
            for (int i = 0; i < good.size(); i++){
                Product p = good.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(p.getId());
                row.createCell(1).setCellValue(p.getName());
                row.createCell(2).setCellValue(p.getPrice());
                row.createCell(3).setCellValue(p.getCount());
            }
            workBook.write(new FileOutputStream(path));
        } catch (Exception e) {}        
   }
    
    public void SaleToExcel(){
         try { 
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet sheet = workBook.createSheet();
            int k = 1;
            
            for (int i = 0; i < sales.size(); i++){
                Sale s = sales.get(i);
                HSSFRow r = sheet.createRow(k++);
                r.createCell(0).setCellValue(s.getLength());
                for (int j = 0; j < s.getLength(); j++){
                    HSSFRow row = sheet.createRow(k++);
                    Product p = s.getProduct(j);
                    row.createCell(0).setCellValue(p.getId());
                    row.createCell(1).setCellValue(p.getName());
                    row.createCell(2).setCellValue(p.getPrice());
                    row.createCell(3).setCellValue(p.getCount());
                }
            }
            workBook.write(new FileOutputStream(file));
        } catch (Exception e) {}    
    }
    
    public int BuyById (int id, int count){
        if ((this.id).contains(id)){
            int index = (this.id).indexOf(id);
            Product p = (this.good).get(index);
            p.buyProduct(count);
            Product t = new Product(p.getId(), p.getName(), p.getPrice(), count);
            sale.add(t);
            return 0;
        }
        else return -1;   
    }
    
    public int BuyByName (String name, int count){
        if ((this.name).contains(name)){
            int index = (this.name).indexOf(name);
            Product p = (this.good).get(index);
            p.buyProduct(count);
            Product t = new Product(p.getId(), p.getName(), p.getPrice(), count);
            sale.add(t);
            return 0;
        }
        else return -1;
    }
    
    public int SaleById(int id, int count){
        if ((this.id).contains(id)){
            int index = (this.id).indexOf(id);
            Product p = (this.good).get(index);
            p.addProduct(count);
            return 0;
        }
        else return -1;   
    }
    
    public void AddGood(Product m){
        id.add(m.getId());
        name.add(m.getName());
        good.add(m);
    }
    
    public ArrayList<Product> getGood() { return this.good; }
    public void clearSale() { (this.sale).clear(); }
    
    public void cancelSale() {
        for (Product p : this.sale){
            this.SaleById(p.getId(), p.getCount());
        }
        this.sale.clear();
    } 
    
    public ArrayList<Product> copySale() { return (ArrayList<Product>) this.sale.clone(); } 
    
    public int salesLenght(){ return this.sales.size(); }
    
    public void addSales(Sale s){ this.sales.add(s); }
    
    public Sale getSale(int i){ return this.sales.get(i); }
}