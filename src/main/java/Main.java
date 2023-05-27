public class Main {

    static String jdbcUrl = "jdbc:postgresql://localhost:5432/kemmyps";
    static String username = "kemmyps";
    static String password = "";

    public static void main(String[] args) {
        //exibi menu de opcoes
        Agenda agenda = new Agenda();
        agenda.open();

    }
}
