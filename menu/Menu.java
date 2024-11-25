package menu;
import java.util.Scanner;

import user.Admin;
import user.Customer;
import user.User;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    User user = new User();
    Files f = new Files();

    public void main_menu(){
        System.out.println("\t=== Welcome to BiT store (^_^;) ===\n");
        System.out.println("Select one of them:");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. Help");
        System.out.println("4. About");
        System.out.println("0. Quit");

        System.out.print("Enter your choice: ");
        String option = scanner.nextLine();
        System.out.println();

        switch (option) {
            case "1":

                if (user.login()) {
                    if (user.usr_info("role").equalsIgnoreCase("admin")) {
                        Admin admin = new Admin(User.id, user.usr_name, user.email, user.pswd, user.role, user.address, user.reg_date);
                        admin.menu();
                    } else if (user.usr_info("role").equalsIgnoreCase("user")) {
                        Customer cust = new Customer(User.id, user.usr_name, user.email, user.pswd, user.role, user.address, user.reg_date);
                        cust.menu();
                    } else {
                        System.out.println(user.usr_info("role"));
                    }
                } 
                
                break;

            case "2":
                user.signup();
                break;

            case "3":
                f.f_read("menu/help.txt");
                break;
            case "4":
                f.f_read("menu/about.txt");
                break;

            case "0":
                return;
        
            default:
                System.out.println("Envalid input: "+option);
        }
        main_menu();
    }
}
