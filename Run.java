//package eCommerce;

import menu.Menu;
import database.*;

public class Run {
    public static void main(String[] args){
        System.out.println("Starting ...\n");
        DB.start();
        Menu menu = new Menu();
        menu.main_menu();
    }
}
