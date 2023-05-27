import java.sql.*;
import java.util.Scanner;

public class Main {

    static String jdbcUrl = "jdbc:postgresql://localhost:5432/kemmyps";
    static String username = "kemmyps";
    static String password = "";

    public static void main(String[] args) {
        insertContact();
        listContacts();
    }

    public static void insertContact() {
        try {
             //abrir conecao com o database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

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

    public static void listContacts() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Criação do Statement para executar a consulta SQL
            Statement statement = connection.createStatement();

            // Execução da consulta SQL para listar a tabela
            String sql = "SELECT * FROM contatos";
            ResultSet resultSet = statement.executeQuery(sql);

            // Processamento dos resultados
            while (resultSet.next()) {
                // Recupere os valores das colunas do resultado
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String numero = resultSet.getString("numero");
                String endereco = resultSet.getString("endereco");


                System.out.println("ID: " + id + ", Nome: " + nome + ", Numero: " + numero + ", Endereço: " + endereco);
            }

            // Fechamento dos recursos
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
