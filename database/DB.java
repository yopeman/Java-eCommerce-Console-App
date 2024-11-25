package database;

import java.sql.*;
import java.util.HashMap;

public class DB {
    final static String db_url = "jdbc:sqlite:eCommerce.db";

    public static void start(){
            String sql;

            sql = "create table if not exists user ("+
                    "id integer primary key autoincrement,"+
                    "usr_name text not null,"+
                    "email text not null unique,"+
                    "pswd text not null,"+
                    "address text not null,"+
                    "role text not null default 'user',"+
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

    public static boolean exec_query(String sql){
        try(Connection conn = DriverManager.getConnection(db_url)){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e){
            System.out.println("Something is wrong: "+e);
            return false;
        }
    }

    public static HashMap <String,String> select_query(String sql) {
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
            System.out.println("Something is wrong: "+e);
            return null;
        }
    }

    public static int count_query(String sql){
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
            System.out.println("Something is wrong: "+e);
            return 0;
        }
    }

    public static void display_query(String sql, String format, int separator){
        try(Connection conn = DriverManager.getConnection(db_url)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
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
            System.out.println("Something is wrong: "+e);
        }
    
    }
}
