package cart;

import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import database.DB;

public class Balance {
    Scanner scanner = new Scanner(System.in);
    private static double money = 0.00d;
    static String usr_id = "";

    public Balance(String usr_id){
        Balance.usr_id = usr_id;
        Balance.money = getBalance();
    }

    public void menu_balance(){
        check_balance();

        System.out.println("To deposit money enter 1 else enter other key");
        System.out.print("Enter your choice: ");
        String option = scanner.nextLine();
        System.out.println();

        if(option.equals("1"))
            deposit_balance();
    }

    public void check_balance(){
        System.out.println("Your available balance are: "+money);
    }

    public void deposit_balance(){
        double temp;
        try {
            temp = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount of money:", "0.0"));
        } catch (Exception e) {
            temp = -1.0;
        }

        if (temp >= 0) {
            money += temp;
            String sql = "update user set balance='"+money+"'";

            if (DB.exec_query(sql)) {
                JOptionPane.showMessageDialog(null, "Successfully deposit your money!");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! deposit money are faild!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry! you inter invalid amount of money!");
        }

        check_balance();
    }

    public static double getBalance(){
        String sql = "select balance from user where id='"+usr_id+"'";
        HashMap <String,String> result = new HashMap<String,String>();
        result = DB.select_query(sql);
        
        try {
            money = Double.parseDouble(result.get("balance"));
        } catch (Exception e) {
            money = 0.0;
        }
        
        return money;
    }

    public static void withdraw(double money){
        if (Balance.money >= money) {
            Balance.money -= money;
            String sql = "update user set balance='"+money+"'";
            
            if (DB.exec_query(sql)) {
                JOptionPane.showMessageDialog(null, "Successfully deposit your money!");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! deposit money are faild!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Your money are insufficent!");
        }
    }
}
