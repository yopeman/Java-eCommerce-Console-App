package database;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import menu.Files;
import server.DB_Interface;

public class DB_Client {
    static String ip = "127.0.0.1";

    public DB_Client(){
        ip = JOptionPane.showInputDialog("Enter IP address of server machine", "127.0.0.1");;
    }

    public static DB_Interface get_stub(){
        try {
            Registry registry = LocateRegistry.getRegistry(ip, 64318);
            DB_Interface stub = (DB_Interface) registry.lookup("BiT_Store");
            stub.server_connection();
            return stub;
        } catch (Exception e) {
            Files.write_log_file(e);
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public static boolean exec_query(String sql) {
        try {
            return get_stub().exec_query(sql);
        } catch (RemoteException e) {
            Files.write_log_file(e);
            return false;
        }
    }

    public static HashMap <String,String> select_query(String sql) {
        try {
            return get_stub().select_query(sql);
        } catch (RemoteException e) {
            Files.write_log_file(e);
            return null;
        }
    }

    public static int count_query(String sql) {
        try {
            return get_stub().count_query(sql);
        } catch (RemoteException e) {
            Files.write_log_file(e);
            return 0;
        }
    }

    public static void display_query(String sql, String format, int separator){
        try {
            ArrayList<String> result_set = new ArrayList<String>();
            ArrayList<String> meta_data = new ArrayList<String>(); 
            
            result_set = get_stub().get_result_set(sql);
            meta_data = get_stub().get_meta_data(sql);
            int col_num = meta_data.size();
            int i = 1;

            for(String temp : meta_data){
                System.out.printf(format, temp); 
            }

            System.out.println();
            System.out.println(new String(new char[separator]).replace("\0", "-"));

            for (String temp : result_set) {
                System.out.printf(format,temp);

                if(i % col_num == 0)
                    System.out.println();

                i++;
            }
        } catch(Exception e){
            Files.write_log_file(e);
        }
    
    }
}
