import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DB.openConnection();
        DB.addDeal(3, 10, 2);
        DB.closeConnection();
    }
}