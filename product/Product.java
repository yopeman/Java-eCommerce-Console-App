package product;

import java.util.Scanner;

import javax.swing.JOptionPane;

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
        try {
            prdct_name = JOptionPane.showInputDialog("Enter product name:");
            prdct_name = prdct_name.trim();

            category = JOptionPane.showInputDialog("Enter category:");
            category = category.trim();

            desc = JOptionPane.showInputDialog("Enter description:");
            desc = desc.trim();

            price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:", "0.0")) + "";
            price = price.trim();

            sql = "insert into product (prdct_name,category,desc,price) values ('"+prdct_name+"','"+category+"','"+desc+"','"+price+"')";
            if(DB.exec_query(sql)){
                JOptionPane.showMessageDialog(null, "Successfully product are inserted!");
                return true;
            } else {
                System.out.println("Something are wrong!");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You commit mistake when enter the value!");
            return false;
        }
    }

    public void display_category(){
        sql = "select min(id) as id, category from product group by category";
        DB.display_query(sql, "%-20s", 30);
    }

    public void display_product(){
        sql = "select * from product";
        DB.display_query(sql, "%-20s", 120);
    }

    public void display_product_by_category(String prdct_id){
        sql = "select * from product where category=(select category from product where id='"+prdct_id+"')";
        DB.display_query(sql, "%-20s", 109);
    }

    public void delete_product(){
        String prdct_id = "";
        try{
            prdct_id = JOptionPane.showInputDialog("Enter product id:");
            prdct_id = prdct_id.trim();
        } catch (Exception e) {
            prdct_id = "";
        }

        if (!prdct_id.isEmpty()) {
            sql = "delete from product where id='"+prdct_id+"'";
            if (DB.exec_query(sql)) {
                JOptionPane.showMessageDialog(null, "Successfully product are deleted!");
            } else {
                System.out.println("Product deletion are failed!");
            } 
        } else {
            JOptionPane.showMessageDialog(null, "You enter wrong value!");
        }
    }

    public void change_product_information(){

        try{
            id = JOptionPane.showInputDialog("Enter product id:");
            id = id.trim();

            if (!(id.isEmpty())) {
                prdct_name = JOptionPane.showInputDialog("Enter product name:");
                prdct_name = prdct_name.trim();

                if (!(prdct_name.isEmpty())) {
                    sql = "update product set prdct_name='"+prdct_name+"' where id='"+id+"'";
                    if (!DB.exec_query(sql)) {
                        System.out.println("Updating product information are failed!");
                    } 
                }
                    
                category = JOptionPane.showInputDialog("Enter category:");
                category = category.trim();

                if (!(category.isEmpty())) {
                    sql = "update product set category='"+category+"' where id='"+id+"'";
                    if (!DB.exec_query(sql)) {
                        System.out.println("Updating product information are failed!");
                    } 
                }

                price = JOptionPane.showInputDialog("Enter price:");
                price = price.trim();

                if (!(price.isEmpty())) {
                    sql = "update product set price='"+price+"' where id='"+id+"'";
                    if (!DB.exec_query(sql)) {
                        System.out.println("Updating product information are failed!");
                    } 
                }

                desc = JOptionPane.showInputDialog("Enter product name:");
                desc = desc.trim();

                if (!(desc.isEmpty())) {
                    sql = "update product set desc='"+desc+"' where id='"+id+"'";
                    if (!DB.exec_query(sql)) {
                        System.out.println("Updating product information are failed!");
                    } 
                }
                    
                JOptionPane.showMessageDialog(null, "Successfully product information are updated!");
            } else {
                JOptionPane.showMessageDialog(null, "You enter wrong value!");
            } 
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Change product information canceled, \nbut some value are may be changed!");
        }
    }

    public void search_product(){
        String search_query = "";
        try{
            search_query = JOptionPane.showInputDialog("Enter search query");
        } catch (Exception e){
            search_query = "";
        }
        
        sql = "select * from product where id like '%"+ search_query +"%' or prdct_name like '%"+ search_query +"%' or category like '%"+ search_query +"%' or price like '%"+ search_query +"%' or desc like '%"+ search_query +"%' or reg_date like '%"+ search_query +"%'";
        DB.display_query(sql, "%-20s", 109);
        System.out.println();
    }
}
