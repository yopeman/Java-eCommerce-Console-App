package product;

import java.util.Scanner;

import database.DB;

public class Product {
    Scanner scanner = new Scanner(System.in);
    String id = "";
    String prdct_name = "";
    String category = "";
    String desc = "";
    String price = "";
    String reg_date = "";
    String sql = "";

    public boolean add_new_product(){
        System.out.print("Enter product name: ");
        prdct_name = scanner.nextLine();
        prdct_name = prdct_name.trim();

        System.out.print("Enter category: ");
        category = scanner.nextLine();
        category = category.trim();

        System.out.print("Enter description: ");
        desc = scanner.nextLine();
        desc = desc.trim();

        System.out.print("Enter price: ");
        price = scanner.nextLine();
        price = price.trim();

        sql = "insert into product (prdct_name,category,desc,price) values ('"+prdct_name+"','"+category+"','"+desc+"','"+price+"')";
        if(DB.exec_query(sql)){
            System.out.println("Products are successfully inserted!");
            return true;
        } else {
            System.out.println("Something are wrong!");
            return false;
        }
    }

    public void display_category(){
        sql = "select min(id) as id, category from product group by category";
        DB.display_query(sql, "%-10s", 30);
    }

    public void display_product(){
        sql = "select * from product";
        DB.display_query(sql, "%-10s", 30);
    }

    public void display_product_by_category(String prdct_id){
        sql = "select * from product where category=(select category from product where id='"+prdct_id+"')";
        DB.display_query(sql, "%-10s", 30);
    }

    public void delete_product(){
        System.out.print("Enter product id: ");
        String prdct_id = scanner.nextLine();
        prdct_id = prdct_id.trim();

        sql = "delete from product where id='"+prdct_id+"'";
        if (DB.exec_query(sql)) {
            System.out.println("Product are successfully deleted!");
        } else {
            System.out.println("Product deletion are failed!");
        } 
    }

    public void change_product_information(){

        System.out.print("Enter product id: ");
        id = scanner.nextLine();
        id = id.trim();

        if (!(id.isEmpty())) {
            System.out.print("Enter product name: ");
            prdct_name = scanner.nextLine();
            prdct_name = prdct_name.trim();

            if (!(prdct_name.isEmpty())) {
                sql = "update product set prdct_name='"+prdct_name+"' where id='"+id+"'";
                if (!DB.exec_query(sql)) {
                    System.out.println("Updating product information are failed!");
                } 
            }
                
            System.out.print("Enter category: ");
            category = scanner.nextLine();
            category = category.trim();

            if (!(category.isEmpty())) {
                sql = "update product set category='"+category+"' where id='"+id+"'";
                if (!DB.exec_query(sql)) {
                    System.out.println("Updating product information are failed!");
                } 
            }


            System.out.print("Enter price: ");
            price = scanner.nextLine();
            price = price.trim();

            if (!(price.isEmpty())) {
                sql = "update product set price='"+price+"' where id='"+id+"'";
                if (!DB.exec_query(sql)) {
                    System.out.println("Updating product information are failed!");
                } 
            }


            System.out.print("Enter description: ");
            desc = scanner.nextLine();
            desc = desc.trim();

            if (!(desc.isEmpty())) {
                sql = "update product set desc='"+desc+"' where id='"+id+"'";
                if (!DB.exec_query(sql)) {
                    System.out.println("Updating product information are failed!");
                } 
            }
                
            System.out.println("Product information are successfully updated!");
        }  
    }

    public void search_product(){
        System.out.print("Enter search query: ");
        String search_query = scanner.nextLine();
        
        sql = "select * from product where prdct_name like '%"+ search_query +"%'";
        DB.display_query(sql, "%-10s", 30);
        System.out.println();

        sql = "select * from product where category like '%"+ search_query +"%'";
        DB.display_query(sql, "%-10s", 30);
        System.out.println();

        sql = "select * from product where desc like '%"+ search_query +"%'";
        DB.display_query(sql, "%-10s", 30);
        System.out.println();
    }
}
