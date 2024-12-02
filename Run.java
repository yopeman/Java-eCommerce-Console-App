import menu.*;

public class Run {
    public static void main(String[] args) {
        OS_Control os_control = new OS_Control();

        os_control.cmd_exec("export", "CLASSPATH", "=.:sqlite3jdbc.jar");
        os_control.cmd_exec("javac", "-classpath", "$CLASSPATH", "cart/*.java");
        os_control.cmd_exec("javac", "-classpath", "$CLASSPATH", "database/*.java");
        os_control.cmd_exec("javac", "-classpath", "$CLASSPATH", "menu/*.java");
        os_control.cmd_exec("javac", "-classpath", "$CLASSPATH", "product/*.java");
        os_control.cmd_exec("javac", "-classpath", "$CLASSPATH", "user/*.java");
        os_control.cmd_exec("javac", "-classpath", "$CLASSPATH", "*.java");
        os_control.clear_scrn();
        os_control.cmd_exec("java", "Main");
    }
}
