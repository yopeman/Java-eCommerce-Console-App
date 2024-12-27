import menu.Menu;
import database.*;

public class Main {
    public static void main(String[] args){
        new DB_Client();
        DB_Client.get_stub();
        Menu menu = new Menu();
        menu.main_menu();
    }
}
