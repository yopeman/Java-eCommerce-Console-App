package user;
import cart.Cart;
import menu.*;
import product.Product;

public class Admin extends User implements Menu_List {
    Product product = new Product();
    Cart cart = new Cart();

    public Admin(String ids, String usr_name, String email, String pswd, String role, String address, String reg_date){
        id = ids;
        this.usr_name = usr_name;
        this.email = email;
        this.pswd = pswd;
        this.role = role;
        this.address = address;
        this.reg_date = reg_date;
    }


    @Override
    public void menu(){
        System.out.println("\t=== BiT Store (ADMIN) (^_^;) ===\n");
        System.out.println("Select one of them:");
        System.out.println("1. Users");
        System.out.println("2. Products");
        System.out.println("3. Add new product");
        System.out.println("4. Cart history");
        System.out.println("5. Profile("+this.usr_name+")");
        System.out.println("0. Logout");

        System.out.print("Enter your choice: ");
        String option = scanner.nextLine();
        System.out.println();

        switch (option) {
            case "1":
                display_all_user();

                System.out.println("Select one of them:");
                System.out.println("1. Change user role");
                System.out.println("2. Delete user");
                System.out.println("3. To continue enter other key");
                System.out.print("Enter your option: ");
                option = scanner.nextLine();

                if(option.equals("1"))
                    change_user_role();
                
                else if (option.equals("2")) 
                    delete_user();

                break;

            case "2":
                product.display_product();

                System.out.println("Select one of them:");
                System.out.println("1. Change product information");
                System.out.println("2. Delete product");
                System.out.println("3. To continue enter other key");
                System.out.print("Enter your option: ");
                option = scanner.nextLine();

                if(option.equals("1"))
                    product.change_product_information();
                else if(option.equals("2"))
                    product.delete_product();
                break;

            case "3":
                product.add_new_product();
                break;

            case "4":
                cart.cart_history();
                break;

            case "5":
                profile();
                break;

            case "0":
                System.out.println("Successfully logouted!");
                return;
        
            default:
                System.out.println("Envalid input: "+option);
        }
        menu();
    }

}
