package server;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import menu.Files;

public class Server {
    public static void main(String[] args) {
        try {
            DB_Implement db_impl = new DB_Implement();
            DB_Interface skeleton = (DB_Interface) UnicastRemoteObject.exportObject(db_impl, 0);
            Registry registry = LocateRegistry.createRegistry(64318);
            registry.bind("BiT_Store", skeleton);
            
            DB_Implement database = new DB_Implement();
            database.start();
            InetAddress ip_address = InetAddress.getLocalHost();
            String ip = ip_address.getHostAddress();

            System.out.println("Server is start with address => "+ip+":64318 \n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            Files.write_log_file("server_log", e);
        }
    }
}