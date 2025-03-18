package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {

    // Configuração do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3309/patp_ads3"; // Substitua pelo seu banco
    private static final String USER = "root";  // Seu usuário do MySQL
    private static final String PASSWORD = "1234567890"; // Sua senha do MySQL



    // Método para obter conexão
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }

    // Método para inserir dados na tabela usuarios
    public static void inserirUsuario(String nome, String sobrenome, String cpf, String email, String telefone) {
        String sql = "INSERT INTO cliente (nome, sobrenome, cpf, email, telefone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, cpf);
            stmt.setString(4, email);
            stmt.setString(5, telefone);
            stmt.executeUpdate();

            System.out.println("Usuário inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        inserirUsuario("João ", "Silva","12345678900", "joaosilva@gmail.com", "55999445566");



    }
}