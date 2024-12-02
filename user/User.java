package user;

import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

import database.DB;
import menu.Files;

public class User extends User_Operation {
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
            //System.out.print("\nEnter username: ");
            //usr_name = scanner.nextLine();
            usr_name = JOptionPane.showInputDialog("Enter username:");
            usr_name = usr_name.trim();

            //System.out.print("Enter email: ");
            //email = scanner.nextLine();
            email = JOptionPane.showInputDialog("Enter email:");
            email = email.trim();

            //System.out.print("Enter password: ");
            //pswd = scanner.nextLine();
            pswd = JOptionPane.showInputDialog("Enter password:");
            pswd = pswd.trim();

            //System.out.print("Enter Address: ");
            //address = scanner.nextLine();
            address = JOptionPane.showInputDialog("Enter address:");
            address = pswd.trim();
        } catch(Exception e){
            //System.out.println("Something is wrong: "+e);
            Files.write_log_file(e);
            return false;
        }

        sql = "insert into user (usr_name, email, pswd, address) values ('"+
                usr_name + "','" + email + "','" + pswd + "','" + address +
              "')";

        if(DB.exec_query(sql)){
            System.out.println("Successfully registered!");

        // /////////////////////
        sql =  "select * from user where email='" + email + "' limit 1";
        HashMap<String,String> row = new HashMap<String,String>();

        try{
            row = DB.select_query(sql);
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
                System.out.println("User information is not found!");
                    return false;
                }
        } catch (Exception e) {
            //System.out.println("Something is wrong: "+e);
            Files.write_log_file(e);
                return false;
        }
        // /////////////////////

        } else {
            System.out.println("This user are alrady exist!");
            return false;
        }
    }

    public boolean login(){
        try{
            //System.out.print("Enter email: ");
            //email = scanner.nextLine();
            email = JOptionPane.showInputDialog("Enter email:");
            email = email.trim();

            //System.out.print("Enter password: ");
            //pswd = scanner.nextLine();
            pswd = JOptionPane.showInputDialog("Enter password:");
            pswd = pswd.trim();
        } catch(Exception e){
            //System.out.println("Something is wrong: "+e);
            Files.write_log_file(e);
            return false;
        }

        sql = "select count(*) from user where email='" + email + "' and pswd='" + pswd + "'";

        if(DB.count_query(sql) == 1){
            sql =  "select * from user where email='" + email + "' limit 1";
            HashMap<String,String> row = new HashMap<String,String>();

            try{
                row = DB.select_query(sql);
                if(row != null){
                    id = row.get("id");
                    usr_name = row.get("usr_name");
                    email = row.get("email");
                    role = row.get("role");
                    address = row.get("address");
                    reg_date = row.get("reg_date");
                    pswd = row.get("pswd");

                    System.out.println("Successfully logined! :)");
                    return true;

                } else {
                    System.out.println("User information is not found!");
                    return false;
                }
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
                return false;
            }
            
        } else {
            System.out.println("Email or password are incorrect!");
            return false;
        }
    }

    public String usr_info(String key){
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

            System.out.print("Enter username: ");
            temp = scanner.nextLine();
            temp = temp.trim();
            if (!(temp.equalsIgnoreCase("\n") || temp.isEmpty())) {
                usr_name = temp;
            }


            System.out.print("Enter password: ");
            temp = scanner.nextLine();
            temp = temp.trim();
            if (!(temp.equalsIgnoreCase("\n") || temp.isEmpty())) {
                pswd = temp;
            }


            System.out.print("Enter Address: ");
            temp = scanner.nextLine();
            temp = temp.trim();
            if (!(temp.equalsIgnoreCase("\n") || temp.isEmpty())) {
                address = temp;
            }

            sql = "update user set usr_name='"+usr_name+"', pswd='"+pswd+"', address='"+address+"' where id='"+id+"'";
            if (DB.exec_query(sql)) {
                System.out.println("Information are successfully change!");
            } else {
                System.out.println("Change in information are failed!");
            }
        }
    }

    public void display_all_user(){
        sql = "select id,usr_name,email,role,address,reg_date from user";
        DB.display_query(sql, "%-25s", 145);
    }

    public void change_user_role(){
        String temp_id = "";
        String temp_role = "";

        System.out.print("Enter user id: ");
        temp_id = scanner.nextLine();
        temp_id = temp_id.trim();


        System.out.print("Enter role: ");
        temp_role = scanner.nextLine();
        temp_role = temp_role.trim();

        sql = "update user set role='"+temp_role+"' where id='"+temp_id+"'";
        if (DB.exec_query(sql)) {
            System.out.println("Information are successfully change!");
        } else {
            System.out.println("Change in information are failed!");
        }        
    }

    public void delete_user(){
        String temp_id = "";

        System.out.print("Enter user id: ");
        temp_id = scanner.nextLine();
        temp_id = temp_id.trim();

        sql = "delete from user where id='"+temp_id+"'";
        if (DB.exec_query(sql)) {
            System.out.println("User are successfully deleted!");
        } else {
            System.out.println("User deletion are failed!");
        }        
    }
}
