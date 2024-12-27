package cart;

import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
import database.DB_Client;

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
            JOptionPane.showMessageDialog(null, "No product are selected!");
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
                try{
                    String key = JOptionPane.showInputDialog("Enter product id to remove: ");
                    carts.remove(key);
                    JOptionPane.showMessageDialog(null, "Successfully product are removed!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You entered wrong value!");
                }
                break;

            case "2":
                carts.clear();
                JOptionPane.showMessageDialog(null, "Successfully cart are cleared!");
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
            try{
                prdct_id = JOptionPane.showInputDialog("Enter product id for add to cart:");

                if(prdct_id == null) prdct_id = "";
                if(prdct_id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Add to cart are finished!");
                    break;
                }
                    
                quantity = JOptionPane.showInputDialog("Enter product quantity for add to cart:", 1);

                if(quantity == null) quantity = "";
                if (quantity.isEmpty())
                    quantity = "1";

                if (!prdct_id.isEmpty())
                    carts.put(prdct_id, quantity);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add to cart are canceled!");
                break;
            }
        } while (!prdct_id.isEmpty());
        
    }

    public void show_cart(){

        String[] header = {"id", "prdct_name", "category", "price", "quantity", "cost"};
        for (String i : header) {
            System.out.printf("%-20s",i);
        }

        System.out.println();
        System.out.print(new String(new char[105]).replace("\0", "-"));
        System.out.println();
        
        for (String prdct_id : carts.keySet()) {
            sql = "select id, prdct_name, category, price from product where id='"+ prdct_id +"'";
            HashMap <String,String> result = new HashMap <String,String>();
            result = DB_Client.select_query(sql);
            Double total_price = 0.00d;

            System.out.printf("%-20s",result.get("id"));
            System.out.printf("%-20s",result.get("prdct_name"));
            System.out.printf("%-20s",result.get("category"));
            System.out.printf("%-20s",result.get("price"));
            System.out.printf("%-20s",carts.get(prdct_id));
            
            try{
                total_price = Double.parseDouble(result.get("price")) * Double.parseDouble(carts.get(prdct_id));
            } catch (Exception e) {
                total_price = 0.0;
            }

            System.out.printf("%-20s",total_price);
            System.out.println();
        }
        System.out.println("#. Total price: "+total_cart_price());
    }

    public void buy_cart(){
        if (carts.size() != 0) {
            double total_price = total_cart_price();
            if (total_price <= Balance.getBalance()) {
                boolean is_inserted = JOptionPane.showConfirmDialog(null,  "Do you want to buy the product?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0;
                
                if(user_id == null) user_id = "";
                if (is_inserted && !user_id.isEmpty()) {

                    for (String prdct_id : carts.keySet()) {
                        sql = "insert into cart (usr_id,prdct_id,quantity) values ('"+ user_id +"','"+ prdct_id +"','"+ carts.get(prdct_id) +"')";
                        if (!DB_Client.exec_query(sql)) 
                            is_inserted = false;
                    }

                    if (is_inserted) {
                        Balance.withdraw(total_price);
                        JOptionPane.showMessageDialog(null, "Successfully ordered! \nThanks for choosen us (^_^;)");
                        carts.clear();
                    } else 
                        System.out.println("Sorry order product are faild!");

                } 
                
            } else 
                JOptionPane.showMessageDialog(null, "Your money are insufficent!");
        } else 
            JOptionPane.showMessageDialog(null, "No product are selected!");
    }

    public double total_cart_price(){
        double total_price, aggregate_price = 0.00d;
        String single_price;
        if (carts.size() != 0) {
            for (String prdct_id : carts.keySet()) {
                sql = "select price from product where id='"+ prdct_id +"'";
                HashMap <String,String> result = new HashMap <String,String>();
                result = DB_Client.select_query(sql);

                single_price = result.get("price");
                
                try{
                    total_price = Double.parseDouble(single_price) * Double.parseDouble(carts.get(prdct_id));
                } catch (Exception e) {
                    total_price = 0;
                }

                aggregate_price += total_price;
            }
        }
        return aggregate_price;
    }

    public void cart_history(){
        System.out.println("Cart history are:");
        sql = "select u.usr_name, u.email, p.prdct_name, p.category, p.price, c.reg_date from user u, product p ,cart c where c.usr_id = u.id and c.prdct_id = p.id order by c.id desc";
        DB_Client.display_query(sql, "%-20s", 120);
    }

    public void cart_history(String uid){
        System.out.println("Cart history are:");
        sql = "select p.prdct_name, p.category, p.price, c.quantity, c.reg_date from user u, product p ,cart c where c.usr_id = u.id and c.prdct_id = p.id and u.id = '"+ this.user_id +"' order by c.id desc";
        DB_Client.display_query(sql, "%-20s", 89);
    }
}
