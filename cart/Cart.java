package cart;

import java.util.HashMap;
import java.util.Scanner;

import database.DB;
import user.User;

public class Cart {
    Scanner scanner = new Scanner(System.in);
    static HashMap<String,String> carts = new HashMap<String,String>();
    String sql;

    public void cart_menu(){
        show_cart();

        System.out.println("Select one of them:");
        System.out.println("1. Remove item");
        System.out.println("2. Clear the cart");
        System.out.println("3. Buy the cart");

        System.out.print("Enter your choice: ");
        String option = scanner.nextLine();
        System.out.println();

        switch (option) {
            case "1":
                System.out.print("Enter product id for remove: "); 
                String key = scanner.nextLine();
                carts.remove(key);  
                break;

            case "2":
                carts.clear();
                break;

            case "3":
                buy_cart();
                break;

            default:
                System.out.println("Envalid input: "+option);
        }
    }

    public void add_to_cart(){
        String prdct_id, quantity;

        do {
            System.out.print("Enter product id for add to cart: ");
            prdct_id = scanner.nextLine();

            if(prdct_id.isEmpty())
                break;
                
            System.out.print("Enter product quantity for add to cart: ");
            quantity = scanner.nextLine();

            if (quantity.isEmpty())
                quantity = "1";

            if (!prdct_id.isEmpty())
                carts.put(prdct_id, quantity);

        } while (!prdct_id.isEmpty());
        
    }

    public void show_cart(){
        String id_list = "";
        int index = 0;

        for(String i : carts.keySet()){
            id_list += "'"+ carts.get(i) +"'";

            if (index < (carts.size() - 1)) {
                id_list += ", ";
            }

            index ++;
        }

        String sql = "select prdct_name, category, price from product where id in ("+ id_list +")";
        DB.display_query(sql, "%-10s", 30);
    }

    public void buy_cart(){
        String single_price;
        double total_price = 0.00d;
        double aggregate_price = 0.00d;

        if (carts.size() != 0) {
            for (String prdct_id : carts.keySet()) {
                sql = "select price from product where id='"+ prdct_id +"'";
                HashMap <String,String> result = new HashMap <String,String>();
                result = DB.select_query(sql);

                single_price = result.get("price");
                total_price = Double.parseDouble(single_price) * Double.parseDouble(carts.get(prdct_id));
                aggregate_price += total_price;
            }

            if (aggregate_price <= Balance.money) {
                for (String prdct_id : carts.keySet()) {
                    sql = "insert into cart (usr_id,prdct_id,quantity) values ('"+ User.id +"','"+ prdct_id +"','"+ carts.get(prdct_id) +"')";
                }
            }
        } else 
            System.out.println("No product are selected!");
    }

    public void cart_history(){
        sql = "select u.usr_name, u.email, p.prdct_name, p.category, p.price, c.reg_date from user u, product p ,cart c where c.usr_id = u.id and c.prdct_id = p.id order by id desc";
        DB.display_query(sql, "%-10s", 30);
    }

    public void cart_history(String usr_id){
        sql = "select p.prdct_name, p.category, p.price, c.reg_date from user u, product p ,cart c where c.usr_id = u.id and c.prdct_id = p.id and u.id = '"+ usr_id +"' order by id desc";
        DB.display_query(sql, "%-10s", 30);
    }
}
