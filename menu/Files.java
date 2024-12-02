package menu;

import java.util.Scanner;
import java.io.File;

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
            System.out.println("Something is wrong: "+e);
        }
    } 
}
