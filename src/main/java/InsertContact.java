import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertContact {
    public static void insertContact() {
        try {
            //abrir conecao com o database
            Connection connection = DriverManager.getConnection(Main.jdbcUrl, Main.username, Main.password);

            // ler informacoes do terminal
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o nome do usuário: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o numero: ");
            String numero = scanner.nextLine();

            System.out.print("Digite o seu endereço: ");
            String endereco = scanner.nextLine();

            // Execução da consulta SQL para inserir novo registro na tabela contatos
            String sql = "INSERT INTO contatos (nome, numero, endereco) VALUES ('" + nome + "', '" + numero + "', '" + endereco + "')";

            Statement statement = connection.createStatement();
            statement.execute(sql);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}