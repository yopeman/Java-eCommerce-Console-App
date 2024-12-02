package cart;

import java.util.HashMap;
import java.util.Scanner;

import database.DB;

public class Cart {
    Scanner scanner = new Scanner(System.in);
    static HashMap<String,String> carts = new HashMap<String,String>();
    String sql;
    String user_id;

    public Cart(String user_id){
        this.user_id = user_id;
    }

    public void cart_menu(){
        if (carts.size() == 0) {
            System.out.println("No product are selected!");
            return;
        }
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
                return;

            default:
                return;
        }
        cart_menu();
    }

    public void add_to_cart(){
        String prdct_id = "";
        String quantity = "";

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

        String[] header = {"id", "prdct_name", "category", "price", "quantity", "cost"};
        for (String i : header) {
            System.out.printf("%-20s",i);
        }

        System.out.println();
        System.out.print(new String(new char[30]).replace("\0", "-"));
        System.out.println();
        
        for (String prdct_id : carts.keySet()) {
            sql = "select id, prdct_name, category, price from product where id='"+ prdct_id +"'";
            HashMap <String,String> result = new HashMap <String,String>();
            result = DB.select_query(sql);
            Double total_price = 0.00d;

            System.out.printf("%-20s",result.get("id"));
            System.out.printf("%-20s",result.get("prdct_name"));
            System.out.printf("%-20s",result.get("category"));
            System.out.printf("%-20s",result.get("price"));
            System.out.printf("%-20s",carts.get(prdct_id));
            total_price = Double.parseDouble(result.get("price")) * Double.parseDouble(carts.get(prdct_id));
            System.out.printf("%-20s",total_price);
            System.out.println();
        }
        System.out.println("#. Total price: "+total_cart_price());
    }

    public void buy_cart(){
        if (carts.size() != 0) {
            double total_price = total_cart_price();
            if (total_price <= Balance.getBalance()) {
                System.out.print("Do you want to buy the product?[Y/N]: ");
                String option = scanner.nextLine();
                boolean is_inserted = option.trim().toLowerCase().equals("y");
                
                if (is_inserted && !user_id.isEmpty()) {

                    for (String prdct_id : carts.keySet()) {
                        sql = "insert into cart (usr_id,prdct_id,quantity) values ('"+ user_id +"','"+ prdct_id +"','"+ carts.get(prdct_id) +"')";
                        if (!DB.exec_query(sql)) 
                            is_inserted = false;
                    }

                    if (is_inserted) {
                        System.out.println("Successfully ordered! Thanks for choosen us (^_^;)");
                        carts.clear();
                    } else 
                        System.out.println("Sorry order product are faild!");

                } 
                
            } else 
                System.out.println("Your money are insufficent!");
        } else 
            System.out.println("No product are selected!");
    }

    public double total_cart_price(){
        double total_price, aggregate_price = 0.00d;
        String single_price;
        if (carts.size() != 0) {
            for (String prdct_id : carts.keySet()) {
                sql = "select price from product where id='"+ prdct_id +"'";
                HashMap <String,String> result = new HashMap <String,String>();
                result = DB.select_query(sql);

                single_price = result.get("price");
                total_price = Double.parseDouble(single_price) * Double.parseDouble(carts.get(prdct_id));
                aggregate_price += total_price;
            }
        }
        return aggregate_price;
    }

    public void cart_history(){
        System.out.println("Cart history are:");
        sql = "select u.usr_name, u.email, p.prdct_name, p.category, p.price, c.reg_date from user u, product p ,cart c where c.usr_id = u.id and c.prdct_id = p.id order by c.id desc";
        DB.display_query(sql, "%-20s", 30);
    }

    public void cart_history(String uid){
        System.out.println("Cart history are:");
        sql = "select p.prdct_name, p.category, p.price, c.quantity, c.reg_date from user u, product p ,cart c where c.usr_id = u.id and c.prdct_id = p.id and u.id = '"+ this.user_id +"' order by c.id desc";
        DB.display_query(sql, "%-20s", 50);
    }
}
