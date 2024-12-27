package user;

import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
import database.DB_Client;
import menu.Files;

public class User {
    Scanner scanner = new Scanner(System.in);
    String sql;

    public String id = "";
    public String usr_name = "";
    public String email = "";
    public String role = "";
    public String pswd = "";
    public String address = "";
    public String reg_date = "";

    public boolean signup(){
        try{
            usr_name = JOptionPane.showInputDialog("Enter username:");
            usr_name = usr_name.trim();

            email = JOptionPane.showInputDialog("Enter email:");
            email = email.trim();

            pswd = JOptionPane.showInputDialog("Enter password:");
            pswd = pswd.trim();

            address = JOptionPane.showInputDialog("Enter address:");
            address = pswd.trim();
        } catch(Exception e){
            Files.write_log_file(e);
            return false;
        }

        sql = "insert into user (usr_name, email, pswd, address, balance) values ('"+
                usr_name + "','" + email + "','" + pswd + "','" + address +
              "', 100.0)";

        if(DB_Client.exec_query(sql)){
            JOptionPane.showMessageDialog(null, "Successfully registered!");

        sql =  "select * from user where email='" + email + "' limit 1";
        HashMap<String,String> row = new HashMap<String,String>();

        try{
            row = DB_Client.select_query(sql);
            if(row != null){
                id = row.get("id");
                usr_name = row.get("usr_name");
                email = row.get("email");
                role = row.get("role");
                address = row.get("address");
                reg_date = row.get("reg_date");
                pswd = row.get("pswd");
                return true;

            } else {
                JOptionPane.showMessageDialog(null, "User information is not found!");
                    return false;
                }
        } catch (Exception e) {
            Files.write_log_file(e);
                return false;
        }

        } else {
            JOptionPane.showMessageDialog(null, "This user are alrady exist!");
            return false;
        }
    }

    public boolean login(){
        try{
            email = JOptionPane.showInputDialog("Enter email:");
            email = email.trim();

            pswd = JOptionPane.showInputDialog("Enter password:");
            pswd = pswd.trim();
        } catch(Exception e){
            Files.write_log_file(e);
            return false;
        }

        sql = "select count(*) from user where email='" + email + "' and pswd='" + pswd + "'";

        if(DB_Client.count_query(sql) == 1){
            sql =  "select * from user where email='" + email + "' limit 1";
            HashMap<String,String> row = new HashMap<String,String>();

            try{
                row = DB_Client.select_query(sql);
                if(row != null){
                    id = row.get("id");
                    usr_name = row.get("usr_name");
                    email = row.get("email");
                    role = row.get("role");
                    address = row.get("address");
                    reg_date = row.get("reg_date");
                    pswd = row.get("pswd");

                    JOptionPane.showMessageDialog(null, "Successfully logined! :)");
                    return true;

                } else {
                    System.out.println("User information is not found!");
                    return false;
                }
            } catch (Exception e) {
                Files.write_log_file(e);
                return false;
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Email or password are incorrect!");
            return false;
        }
    }

    public String usr_info(String key){
        if(id == null) id = "";
        if(!(id.isEmpty())){
            switch (key) {
                case "id":
                    return id;
                
                case "usr_name":
                    return usr_name;

                case "email":
                    return email;
            
                case "role":
                    return role;

                case "pswd":
                    return pswd;

                case "address":
                    return address;

                case "reg_date":
                    return reg_date;

                default:
                    return "envalid";
            }
        } else {
            return "Not Logined User";
        }
    }

    public boolean is_logined(){
        if(id == null) id = "";
        if (!(id.isEmpty())) 
            return true;
        else 
            return false;
    }

    public void profile(){
        System.out.println("\t=== " + usr_name + " Profile (^_^;) ===\n");
        System.out.println("User ID            : " + id);
        System.out.println("Username           : " + usr_name);
        System.out.println("Email              : " + email);
        System.out.println("Address            : " + address);
        System.out.println("Registeration date : " + reg_date);

        System.out.println("To change your information enter 1 else enter other key!");
        System.out.print("Enter your option: ");
        String option = scanner.nextLine();

        if(option.equals("1")){
            String temp = "";

            try{
                temp = JOptionPane.showInputDialog("Enter username:", usr_name);
                temp = temp.trim();

                if(temp == null) temp = "";
                if (!(temp.equalsIgnoreCase("\n") || temp.isEmpty())) {
                    usr_name = temp;
                }

                temp = JOptionPane.showInputDialog("Enter password:");
                temp = temp.trim();

                if(temp == null) temp = "";
                if (!(temp.equalsIgnoreCase("\n") || temp.isEmpty())) {
                    pswd = temp;
                }

                temp = JOptionPane.showInputDialog("Enter address:", address);
                temp = temp.trim();

                if(temp == null) temp = "";
                if (!(temp.equalsIgnoreCase("\n") || temp.isEmpty())) {
                    address = temp;
                }

                sql = "update user set usr_name='"+usr_name+"', pswd='"+pswd+"', address='"+address+"' where id='"+id+"'";
                if (DB_Client.exec_query(sql)) {
                    JOptionPane.showMessageDialog(null, "Successfully information are changed!");
                } else {
                    JOptionPane.showMessageDialog(null, "Change information are failed!");
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Change information are canceld!");
            }
        }
    }

    public void display_all_user(){
        sql = "select id,usr_name,email,role,balance,address,reg_date from user";
        DB_Client.display_query(sql, "%-20s", 140);
    }

    public void change_user_role(){
        String temp_id = "";
        String temp_role = "";

        try{
            temp_id = JOptionPane.showInputDialog("Enter user id:");
            temp_id = temp_id.trim();

            temp_role = JOptionPane.showInputDialog("Enter role");
            temp_role = temp_role.trim();
        } catch (Exception e){
            temp_id = "";
            temp_role = "";
        }

        if(temp_id == null) temp_id = "";
        if(temp_role == null) temp_role = "";
        if (!(temp_id.isEmpty() && temp_role.isEmpty())) {
            sql = "update user set role='"+temp_role+"' where id='"+temp_id+"'";
            if (DB_Client.exec_query(sql)) {
                JOptionPane.showMessageDialog(null, "Successfully information are change!");
            } else {
                JOptionPane.showMessageDialog(null, "Change information are failed!");
            } 
        } else {
            JOptionPane.showMessageDialog(null, "You enter wrong information!");
        }

               
    }

    public void delete_user(){
        String temp_id = "";

        try{
            temp_id = JOptionPane.showInputDialog("Enter user id:");
            temp_id = temp_id.trim();
        } catch (Exception e) {
            temp_id = "";
        }
        
        if(temp_id == null) temp_id = "";
        if (!temp_id.isEmpty()) {
            sql = "delete from user where id='"+temp_id+"'";
            if (DB_Client.exec_query(sql)) {
                JOptionPane.showMessageDialog(null, "Successfully user are deleted!");
            } else {
                JOptionPane.showMessageDialog(null, "User deletion are failed!");
            } 
        } else {
            JOptionPane.showMessageDialog(null, "You enter wrong value!");
        }

               
    }
}
