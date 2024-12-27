package database;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

            ResultSet rs = get_stub().get_result_set(sql);
            ResultSetMetaData meta_data = rs.getMetaData();
            int col_num = meta_data.getColumnCount();
            String key, value;

            for(int i = 1; i <= col_num; i++){
                key = meta_data.getColumnName(i);
                System.out.printf(format,key);
            }

            System.out.println();
            System.out.println(new String(new char[separator]).replace("\0", "-"));

            while (rs.next()) {
                for(int i = 1; i <= col_num; i++){
                    key = meta_data.getColumnName(i);
                    value = rs.getString(key);
    
                    System.out.printf(format,value);
                }
                System.out.println();
            }
        } catch(Exception e){
            Files.write_log_file(e);
        }
    
    }
}
