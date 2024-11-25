import database.DB;

public class test {
    public static void main(String[] args) {
        String sql = "select * from user";
        DB.display_query(sql, "%-20s", 50);
    }
}
