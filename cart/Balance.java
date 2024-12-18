package cart;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Balance {
    Scanner scanner = new Scanner(System.in);
    private static double money = 0.00d;

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
        System.out.print("Enter the amount of money: ");
        double temp = scanner.nextDouble();

        if (temp > 0) {
            money += temp;
            //System.out.println("Successfully deposit your money!");
            JOptionPane.showMessageDialog(null, "Successfully deposit your money!");
        } else {
            System.out.println("Sorry! deposit money are faild!");
        }

        check_balance();
    }

    public static double getBalance(){
        return money;
    }

    public static void setBalance(double money){
        Balance.money = money;
    }
}
