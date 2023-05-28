import java.sql.*;
import java.util.Scanner;

public class ServiceContacts {

    public void listContacts(boolean apenasFavorito) {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = getConnection();

            // Criação do Statement para executar a consulta SQL
            Statement statement = connection.createStatement();

            String sql;

            if (apenasFavorito) {
                sql = "SELECT * FROM contatos WHERE favorito = true";
            } else {
                sql = "SELECT * FROM contatos";
            }

            ResultSet resultSet = statement.executeQuery(sql);

            // Processamento dos resultados e contagem de registros

            System.out.println("|----------------------------------------------------------------------|");
            System.out.printf("| %-3s | %-10s | %-15s | %-20s | %-8s |\n", "ID", "Nome", "Numero", "Endereco", "Favorito");
            System.out.println("|----------------------------------------------------------------------|");
            int cont = 0;
            while (resultSet.next()) {
                // Recupere os valores das colunas do resultado
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String numero = resultSet.getString("numero");
                String endereco = resultSet.getString("endereco");
                Boolean favorito = resultSet.getBoolean("favorito");

                System.out.printf("| %-3s | %-10s | %-15s | %-20s | %-8s |\n", id, nome, numero, endereco, favorito);
                cont++;
            }
            System.out.println("|----------------------------------------------------------------------|");
            System.out.printf("| Quantidade de resultados: %-43d|\n",cont);
            System.out.println("|----------------------------------------------------------------------|");


            // Fechamento dos recursos
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insertContact() {
        try {
            //abrir conecao com o database
            Connection connection = getConnection();

            // ler informacoes do terminal
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o nome do usuário: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o numero: ");
            String numero = scanner.nextLine();

            System.out.print("Digite o seu endereço: ");
            String endereco = scanner.nextLine();

            System.out.println("Adicionar contato à lista de favoritos (s/n)?");
            String resposta = scanner.nextLine();

            Boolean favorito = resposta.equalsIgnoreCase("s");

            // Execução da consulta SQL para inserir novo registro na tabela contatos
            String sql = "INSERT INTO contatos (nome, numero, endereco, favorito) VALUES ('" + nome + "', '" + numero + "', '" + endereco + "', " + favorito + ")";

            System.out.println("Contato adicionado com sucesso!");

            Statement statement = connection.createStatement();
            statement.execute(sql);

            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteContact() {
        try {
            Connection connection = getConnection();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o id correspondente ao contato que deseja excluir: ");
            String id = scanner.nextLine();

            // Execução da consulta SQL para excluir registro na tabela contatos
            String sql = "DELETE FROM contatos WHERE id =" + id;

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            System.out.println("Contato deletado com sucesso!");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void editContact() {
        try {
            Connection connection = getConnection();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o id do contato que você deseja alterar: ");
            String id = scanner.nextLine();

            System.out.print("Digite o novo nome do contato: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o novo numero do contato: ");
            String numero = scanner.nextLine();

            System.out.print("Digite o novo endereço do contato: ");
            String endereco = scanner.nextLine();

            System.out.println("Adicionar contato à lista de favoritos (s/n)?");
            String resposta = scanner.nextLine();

            Boolean favorito = resposta.equalsIgnoreCase("s");

            String sql = "UPDATE contatos SET nome = '" + nome +
                    "', numero = '" + numero +
                    "', endereco = '" + endereco +
                    "', favorito = " + favorito +
                    " WHERE id = " + id;


            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            System.out.println("Contato editado com sucesso!");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteAllContact() {
        try {
            Connection connection = getConnection();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Tem certeza que deseja excluir todos os contatos (s/n)? ");
            String resposta = scanner.nextLine();

            Statement statement = connection.createStatement();
            if (resposta.equalsIgnoreCase("s")) {

                // Execução da consulta SQL para excluir todos os registros na tabela contatos

                String sql = "DELETE FROM contatos";
                statement.executeUpdate(sql);

                System.out.println("Todos os contatos foram excluídos com sucesso.");
            } else {
                System.out.println("Operação cancelada pelo usuário.");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/kemmyps";
        String username = "kemmyps";
        String password = "";
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}