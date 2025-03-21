package conexao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestaBanco {

    public static void main(String[] args) {

        System.out.println("Inserindo um novo quarto...");
        List<Object> parametrosInsertQuarto = List.of(101, 1, 2, 1, 1, 1, 1, 4.5, 250.00);// Essa lista pode ser trocada por variáveis em outras partes do código
        int linhasAfetadasInsertQuarto = Conexao.executeUpdate(
            "INSERT INTO quarto (nmr_quarto, andar, max_hospedes, nmr_camas, frigobar, tv, nmr_suites, avaliacao, valor_diaria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            parametrosInsertQuarto
        );

        if (linhasAfetadasInsertQuarto > 0) {
            System.out.println("Quarto inserido com sucesso! Linhas afetadas: " + linhasAfetadasInsertQuarto);
        } else {
            System.out.println("Falha ao inserir quarto.");
        }

 
        System.out.println("\nConsultando quartos...");
        List<Object> parametrosSelectQuarto = List.of(101); // Consulta pelo nmr do quarto inserido ali em cima novamente pode ser trocado por exemplo por um Scanner coletado no sistema
        ResultSet rsQuarto = Conexao.executeQuery("SELECT * FROM quarto WHERE nmr_quarto = ?", parametrosSelectQuarto); // ResultSET para pegar os valores do quarto usa-se no select

        try {
            if (rsQuarto != null) { // mesmo esquema do python, só que mais confuso, aqui que coleta os resultados do select passando para variaveis
                while (rsQuarto.next()) {
                    int nmrQuarto = rsQuarto.getInt("nmr_quarto");
                    int andar = rsQuarto.getInt("andar");
                    double avaliacao = rsQuarto.getDouble("avaliacao");
                    double valorDiaria = rsQuarto.getDouble("valor_diaria");
                    System.out.println("Número do Quarto: " + nmrQuarto + ", Andar: " + andar + ", Avaliação: " + avaliacao + ", Valor Diária: " + valorDiaria);
                }
            } else {
                System.out.println("Nenhum quarto encontrado.");
            }
        } catch (SQLException e) { // Aqui é para printar o erro de conexão
            e.printStackTrace();
        } finally {
            try { // Aqui é para fechar o ResultSet, para não dar erro de conexão
                if (rsQuarto != null) rsQuarto.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // daqui pra baixo é tudo parecido com os de cima apenas utilizando tabelas diferentes
        System.out.println("\nInserindo um novo cliente...");
        List<Object> parametrosInsertCliente = List.of("João", "Silva", "12345678901", "joao.silva@example.com", "11987654321");
        int linhasAfetadasInsertCliente = Conexao.executeUpdate(
            "INSERT INTO cliente (nome, sobrenome, cpf, email, telefone) VALUES (?, ?, ?, ?, ?)",
            parametrosInsertCliente
        );

        if (linhasAfetadasInsertCliente > 0) {
            System.out.println("Cliente inserido com sucesso! Linhas afetadas: " + linhasAfetadasInsertCliente);
        } else {
            System.out.println("Falha ao inserir cliente.");
        }
        System.out.println("\nConsultando clientes...");
        ResultSet rsCliente = Conexao.executeQuery("SELECT * FROM cliente WHERE cpf = ?", List.of("12345678901"));

        try {
            if (rsCliente != null) {
                while (rsCliente.next()) {
                    int idCliente = rsCliente.getInt("id_cliente");
                    String nome = rsCliente.getString("nome");
                    String sobrenome = rsCliente.getString("sobrenome");
                    String cpf = rsCliente.getString("cpf");
                    System.out.println("ID Cliente: " + idCliente + ", Nome: " + nome + " " + sobrenome + ", CPF: " + cpf);
                }
            } else {
                System.out.println("Nenhum cliente encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsCliente != null) rsCliente.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        System.out.println("\nInserindo uma nova reserva....");
        List<Object> parametrosInsertReserva = List.of(101, 1, "2023-10-15", "2023-10-20", 1, 1, 1, 1250.00);
        int linhasAfetadasInsertReserva = Conexao.executeUpdate(
            "INSERT INTO reservas (nmr_quarto, id_cliente, data_checkin, data_checkout, cafe_incluso, servico_quarto, faxineira, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            parametrosInsertReserva
        );

        if (linhasAfetadasInsertReserva > 0) {
            System.out.println("Reserva inserida com sucesso! Linhas afetadas: " + linhasAfetadasInsertReserva);
        } else {
            System.out.println("Falha ao inserir reserva.");
        }
        System.out.println("\nAtualizando o valor da diária do quarto 101...");
        List<Object> parametrosUpdateQuarto = List.of(300.00, 101);
        int linhasAfetadasUpdateQuarto = Conexao.executeUpdate(
            "UPDATE quarto SET valor_diaria = ? WHERE nmr_quarto = ?",
            parametrosUpdateQuarto
        );

        if (linhasAfetadasUpdateQuarto > 0) {
            System.out.println("Valor da diária atualizado com sucesso! Linhas afetadas: " + linhasAfetadasUpdateQuarto);
        } else {
            System.out.println("Falha ao atualizar valor da diária.");
        }
        
        
        System.out.println("\nExcluindo o cliente com CPF 12345678901...");
        List<Object> parametrosDeleteCliente = List.of("12345678901");
        int linhasAfetadasDeleteCliente = Conexao.executeUpdate(
            "DELETE FROM cliente WHERE cpf = ?",
            parametrosDeleteCliente
        );

        if (linhasAfetadasDeleteCliente > 0) {
            System.out.println("Cliente excluído com sucesso! Linhas afetadas: " + linhasAfetadasDeleteCliente);
        } else {
            System.out.println("Falha ao excluir cliente.");
        }
    }
}}