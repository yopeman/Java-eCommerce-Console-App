package user;

import javax.swing.JOptionPane;

import menu.*;
import product.Product;
import cart.*;

public class Customer extends User {
    public Customer(String id, String usr_name, String email, String pswd, String role, String address, String reg_date){
        this.id = id;
        this.usr_name = usr_name;
        this.email = email;
        this.pswd = pswd;
        this.role = role;
        this.address = address;
        this.reg_date = reg_date;
    }

    public void menu(){
        Cart cart = new Cart(this.id);
        Product product = new Product();
        Balance balance = new Balance(id);
        OS_Control control = new OS_Control();
        
        System.out.println("\n=== BiT Store (CUSTOMER) (^_^;) ===\n");
        System.out.println("Select one of them:");
        System.out.println("1. Product");
        System.out.println("2. Search product");
        System.out.println("3. Cart");
        System.out.println("4. History");
        System.out.println("5. Profile("+this.usr_name+")");
        System.out.println("6. Balance");
        System.out.println("0. Logout");

        System.out.print("Enter your choice: ");
        String option = scanner.nextLine();
        System.out.println();

        switch (option) {
            case "1":
            String prdct_id = "";
                try{
                    product.display_category();
                    prdct_id = JOptionPane.showInputDialog("Enter category id:");
                } catch (Exception e) {
                    prdct_id = "";
                }

                if(!prdct_id.isEmpty()){
                    product.display_product_by_category(prdct_id);
                    cart.add_to_cart();
                }
                    
                break;

            case "2":
                product.search_product();
                cart.add_to_cart();
                break;

            case "3":
                cart.cart_menu();
                break;

            case "4":
            cart.cart_history(id);
                break;

            case "5":
                profile();
                break;

            case "6":
                balance.menu_balance();
                break;

            case "0":
                JOptionPane.showMessageDialog(null, "Successfully logouted!");
                return;
        
            default:
            JOptionPane.showMessageDialog(null, "Sorry! you enter invalid: "+option);
        }
        control.clear_scrn();
        menu();
    }

}
