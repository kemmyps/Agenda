import java.sql.*;

public class ListContact {
    public static void listContacts() {
        try {
            Connection connection = DriverManager.getConnection(Main.jdbcUrl, Main.username, Main.password);

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