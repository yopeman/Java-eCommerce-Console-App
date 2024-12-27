package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface DB_Interface extends Remote {
    public boolean exec_query(String sql) throws RemoteException;
    public HashMap <String,String> select_query(String sql) throws RemoteException;
    public int count_query(String sql) throws RemoteException;
    public ArrayList<String> get_result_set(String sql) throws RemoteException;
    public ArrayList<String> get_meta_data(String sql) throws RemoteException;
    public String server_connection() throws RemoteException;
}
