package menu;
import java.util.Scanner;

import javax.swing.JOptionPane;

import user.Admin;
import user.Customer;
import user.User;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    User user = new User();
    Files files = new Files();
    OS_Control control = new OS_Control();

    public void main_menu(){
        System.out.println("\n=== Welcome to BiT store (^_^;) ===\n");
        System.out.println("Select one of them:");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. Help");
        System.out.println("4. About");
        System.out.println("5. Log");
        System.out.println("0. Quit");

        System.out.print("Enter your choice: ");
        String option = scanner.nextLine();
        System.out.println();

        switch (option) {
            case "1":

                if (user.login()) {
                    if (user.usr_info("role").equalsIgnoreCase("admin")) {
                        Admin admin = new Admin(user.id, user.usr_name, user.email, user.pswd, user.role, user.address, user.reg_date);
                        control.clear_scrn();
                        admin.menu();
                    } else if (user.usr_info("role").equalsIgnoreCase("user")) {
                        Customer cust = new Customer(user.id, user.usr_name, user.email, user.pswd, user.role, user.address, user.reg_date);
                        control.clear_scrn();
                        cust.menu();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry, your role is invalid! \n("+user.usr_info("role")+")");
                    }
                } 
                
                break;

            case "2":
                if (user.signup()) {
                    Customer cust = new Customer(user.id, user.usr_name, user.email, user.pswd, user.role, user.address, user.reg_date);
                    control.clear_scrn();
                    cust.menu();
                }
                break;

            case "3":
                files.file_read("menu/help.txt");
                break;
            case "4":
                files.file_read("menu/about.txt");
                break;

            case "5":
                files.file_read("menu/log.txt");
                break;

            case "0":
                JOptionPane.showMessageDialog(null, "Successfully exiting from our system! \nThanks for choosen us :)", "BiT Store", JOptionPane.INFORMATION_MESSAGE);
                return;
        
            default:
                JOptionPane.showMessageDialog(null, "Sorry! you enter invalid: "+option);
        }
        control.clear_scrn();
        main_menu();
    }
}
