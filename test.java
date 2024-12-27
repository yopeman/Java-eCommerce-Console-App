import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.DB_Interface;

public class test {
    public static void main(String[] args){
        try{
            Registry registry = LocateRegistry.getRegistry(64318);
            DB_Interface stub = (DB_Interface) registry.lookup("BiT_Store");
            System.out.println(stub.server_connection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
