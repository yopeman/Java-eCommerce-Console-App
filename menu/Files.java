package menu;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Date;

public class Files {
    public void file_read(String file_path){
        try{
            File fObj = new File(file_path);
            try (Scanner scanner = new Scanner(fObj)) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (Exception e){
            Files.write_log_file(e);
        }
    } 

    public static void write_log_file(Exception data){
        Date date1 = new Date();
        LocalDateTime date2 = LocalDateTime.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu/client_log.txt", true))) {
            writer.write("["+ date1 + " | "+ date2 + "]" + " :\t " + data+"\n\n");
        } catch (Exception e) {
            System.out.println("Something is wrong: "+e);
        }
    }

    public static void write_log_file(String file_name, Exception data){
        Date date1 = new Date();
        LocalDateTime date2 = LocalDateTime.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("server/"+file_name+".txt", true))) {
            writer.write("["+ date1 + " | "+ date2 + "]" + " :\t " + data+"\n\n");
        } catch (Exception e) {
            System.out.println("Something is wrong: "+e);
        }
    }

    public static void write_log_file(String file_name, String data){
        Date date1 = new Date();
        LocalDateTime date2 = LocalDateTime.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("server/"+file_name+".txt", true))) {
            writer.write("["+ date1 + " | "+ date2 + "]" + " :\t " + data+"\n\n");
        } catch (Exception e) {
            System.out.println("Something is wrong: "+e);
        }
    }
}
