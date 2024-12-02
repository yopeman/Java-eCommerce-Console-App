package menu;

import java.util.Scanner;

public class OS_Control {
    public String os_name;
    
    public OS_Control(){
        os_name = System.getProperty("os.name");
        os_name = os_name.toLowerCase();

        if (os_name.contains("win")) 
            os_name = "window";
        else if (os_name.contains("nux") || os_name.contains("nix")) 
            os_name = "linux";
        else 
            os_name = "unknown";
        
    }

    public boolean cmd_exec(String cmd1){
        if (os_name.equals("window")) {
            try {
                new ProcessBuilder("cmd", "/c", cmd1).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } 
        
        else if (os_name.equals("linux")) {
            try {
                new ProcessBuilder(cmd1).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } else {
            System.out.println("Something is wrong: " + "Unknown Operating System!");
            return false;
        }
        return true;
    }

    public boolean cmd_exec(String cmd1, String cmd2){
        if (os_name.equals("window")) {
            try {
                new ProcessBuilder("cmd", "/c", cmd1, cmd2).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } 
        
        else if (os_name.equals("linux")) {
            try {
                new ProcessBuilder(cmd1, cmd2).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } else {
            System.out.println("Something is wrong: " + "Unknown Operating System!");
            return false;
        }
        return true;
    }

    public boolean cmd_exec(String cmd1, String cmd2, String cmd3){
        if (os_name.equals("window")) {
            try {
                new ProcessBuilder("cmd", "/c", cmd1, cmd2, cmd3).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } 
        
        else if (os_name.equals("linux")) {
            try {
                new ProcessBuilder(cmd1, cmd2, cmd3).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } else {
            System.out.println("Something is wrong: " + "Unknown Operating System!");
            return false;
        }
        return true;
    }

    public boolean cmd_exec(String cmd1, String cmd2, String cmd3, String cmd4){
        if (os_name.equals("window")) {
            try {
                new ProcessBuilder("cmd", "/c", cmd1, cmd2, cmd3, cmd4).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } 
        
        else if (os_name.equals("linux")) {
            try {
                new ProcessBuilder(cmd1, cmd2, cmd3, cmd4).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } else {
            System.out.println("Something is wrong: " + "Unknown Operating System!");
            return false;
        }
        return true;
    }

    public boolean cmd_exec(String cmd1, String cmd2, String cmd3, String cmd4, String cmd5){
        if (os_name.equals("window")) {
            try {
                new ProcessBuilder("cmd", "/c", cmd1, cmd2, cmd3, cmd4, cmd5).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } 
        
        else if (os_name.equals("linux")) {
            try {
                new ProcessBuilder(cmd1, cmd2, cmd3, cmd4, cmd5).inheritIO().start().waitFor();
            } catch (Exception e) {
                //System.out.println("Something is wrong: "+e);
                Files.write_log_file(e);
            }
        } else {
            System.out.println("Something is wrong: " + "Unknown Operating System!");
            return false;
        }
        return true;
    }

    public void clear_scrn(){
        System.out.print("\nTo continue press enter key!\t");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        if (os_name.equals("window")) {
            cmd_exec("cls");
        } 
        
        else if (os_name.equals("linux")) {
            cmd_exec("clear");
        } else {
            System.out.println("Something is wrong: " + "Unknown Operating System!");
        }
    }
}
