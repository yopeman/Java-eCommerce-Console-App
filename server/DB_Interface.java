package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.HashMap;

public interface DB_Interface extends Remote {
    public boolean exec_query(String sql) throws RemoteException;
    public HashMap <String,String> select_query(String sql) throws RemoteException;
    public int count_query(String sql) throws RemoteException;
    public ResultSet get_result_set(String sql) throws RemoteException;
    public String server_connection() throws RemoteException;
}
