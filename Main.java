import menu.Menu;
import database.*;

public class Main {
    public static void main(String[] args){
        DB.start();
        Menu menu = new Menu();
        menu.main_menu();
    }
}
