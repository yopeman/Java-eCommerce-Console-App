import menu.*;

public class Run {
    public static void main(String[] args) {
        OS_Control os_control = new OS_Control();

        String[] command_window = {
            "cls", 
            "echo \"===== Compiling all files =====\"", 
            "echo", 
            "export CLASSPATH=.:database/sqlite-jdbc-3.47.0.0.jar", 
            "echo \"CLASSPATH Of JDBC Are Inserted!\"", 
            "javac -classpath $CLASSPATH cart/*.java", 
            "echo \"Cart Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH database/*.java", 
            "echo \"Database Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH menu/*.java", 
            "echo \"Menu Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH product/*.java", 
            "echo \"Product Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH user/*.java", 
            "echo \"User Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH *.java", 
            "echo \"Main Package Are Compiled!\"", 
            "echo \"Running The Program!\"", 
            "echo \"\"", 
            "cls", 
            "java Main"
        };
        
        String[] command_linux = {
            "clear", 
            "echo \"===== Compiling all files =====\"", 
            "echo", 
            "export CLASSPATH=.:database/sqlite-jdbc-3.47.0.0.jar", 
            "echo \"CLASSPATH Of JDBC Are Inserted!\"", 
            "javac -classpath $CLASSPATH cart/*.java", 
            "echo \"Cart Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH database/*.java", 
            "echo \"Database Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH menu/*.java", 
            "echo \"Menu Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH product/*.java", 
            "echo \"Product Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH user/*.java", 
            "echo \"User Package Are Compiled!\"", 
            "javac -classpath $CLASSPATH *.java", 
            "echo \"Main Package Are Compiled!\"", 
            "echo \"Running The Program!\"", 
            "echo \"\"", 
            "clear", 
            "java Main"
        };

        if (os_control.os_name.equals("window")) {
            for (String i : command_window) {
                os_control.cmd_exec(i);
            }
        }

        else if (os_control.os_name.equals("linux")) {
            for (String i : command_linux) {
                os_control.cmd_exec(i);
                //System.out.println(i);
            }
            //os_control.cmd_exec("./compile.sh");
        }

        else {
            System.out.println("This operating system are unknown!");
        }
    }
}
