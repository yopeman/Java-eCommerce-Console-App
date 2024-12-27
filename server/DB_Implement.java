package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.HashMap;
import java.util.Date;
import java.time.LocalDateTime;

import menu.Files;

public class DB_Implement implements DB_Interface, Serializable {
    final static String db_url = "jdbc:sqlite:server/BiT_Store_Database.db";

    public void start() throws RemoteException{
            String sql;

            sql = "create table if not exists user ("+
                    "id integer primary key autoincrement,"+
                    "usr_name text not null,"+
                    "email text not null unique,"+
                    "pswd text not null,"+
                    "address text not null,"+
                    "role text not null default 'user',"+
                    "balance float,"+
                    "reg_date timestamp default current_timestamp"+
                  ")";
            exec_query(sql);

                sql = "create table if not exists product ("+
                        "id integer primary key autoincrement,"+
                        "prdct_name text not null,"+
                        "category text not null,"+
                        "price float not null,"+
                        "desc text not null,"+
                        "reg_date timestamp default current_timestamp"+
                      ")";
                exec_query(sql);

                sql = "create table if not exists cart ("+
                        "id integer primary key autoincrement,"+
                        "usr_id integer not null,"+
                        "prdct_id integer not null,"+
                        "quantity integer not null,"+
                        "reg_date timestamp default current_timestamp"+
                      ")";
                exec_query(sql);

                sql = "select count(*) from user where role='admin'";
                if (count_query(sql) == 0) {
                    sql = "insert into user (usr_name, email, pswd, address, role) values ('admin','admin@bitstore.com','admin','Ethiopia','admin')";
                    exec_query(sql);
                }
    }

    @Override
    public boolean exec_query(String sql) throws RemoteException {
        Files.write_log_file("database_log", sql);

        try(Connection conn = DriverManager.getConnection(db_url)){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e){
            Files.write_log_file("server_log", e);
            return false;
        }
    }

    @Override
    public HashMap <String,String> select_query(String sql) throws RemoteException {
        Files.write_log_file("database_log", sql);

        HashMap <String,String> row = new HashMap<String,String>();

        try(Connection conn = DriverManager.getConnection(db_url)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData meta_data = rs.getMetaData();
            int col_num = meta_data.getColumnCount();
            String key, value;

            for(int i = 1; i <= col_num; i++){
                key = meta_data.getColumnName(i);
                value = rs.getString(key);

                row.put(key, value);
            }

            return row;
        } catch(Exception e){
            Files.write_log_file("server_log", e);
            return null;
        }
    }

    @Override
    public int count_query(String sql) throws RemoteException {
        Files.write_log_file("database_log", sql);

        try(Connection conn = DriverManager.getConnection(db_url)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
                if(rs.next()){
                    int num_row = rs.getInt(1);
                    return num_row;
                } else {
                    return 0;
                }
        } catch(Exception e){
            Files.write_log_file("server_log", e);
            return 0;
        }
    }

    @Override
    public ResultSet get_result_set(String sql) throws RemoteException {
        Files.write_log_file("database_log", sql);

        try(Connection conn = DriverManager.getConnection(db_url)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch(Exception e){
            Files.write_log_file("server_log", e);
            return null;
        }
    }

    @Override
    public String server_connection(){
        Date date1 = new Date();
        LocalDateTime date2 = LocalDateTime.now();

        String str = "Server @ " + date1 + " | " + date2;
        System.out.println(str);
        return str;
    }
}
