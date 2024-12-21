package menu;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class Files {
    public void file_read(String f_path){
        try{
            File fObj = new File(f_path);
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
        Date date = new Date();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu/log.txt", true))) {
            writer.write(date + ": " + data+"\n\n");
        } catch (Exception e) {
            System.out.println("Something is wrong: "+e);
        }
    }
}
