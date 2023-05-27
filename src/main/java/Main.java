import java.sql.*;
import java.util.Scanner;

public class Main {

    static String jdbcUrl = "jdbc:postgresql://localhost:5432/kemmyps";
    static String username = "kemmyps";
    static String password = "";

    public static void main(String[] args) {
//        insertContact();
//        listContacts();

        MainOptions mainOptions = new MainOptions();
        mainOptions.mainOptions();

    }
}
