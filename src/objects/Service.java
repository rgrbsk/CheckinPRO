package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class Service {
    int id;
    String descricao;
    float valor;
    
    public Service() {
        
    }
    
    // Construtor adicionado
    public Service(int id, String descricao, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public static int addService(float valor, String descricao) {
        String sql = "INSERT INTO servicos_extra (valor, descricao) " +
                "VALUES (?, ?)";
        
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(valor);
        parameters.add(descricao);
        
        try {
            return Conexao.executeUpdate(sql, parameters);
        } catch (Exception e) {
            System.err.println("Erro ao inserir serviço: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    public static List<Service> buscarTodosServicos() {
        String sql = "SELECT id_servico, descricao, valor FROM servicos_extra";
        List<Service> result = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // ✅ Executa a query sem parâmetros

            while (rs.next()) {
                Service service = new Service(
                    rs.getInt("id_servico"),
                    rs.getString("descricao"),
                    rs.getFloat("valor") // ✅ Certifique-se que o tipo está correto
                );
                result.add(service);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviços: " + e.getMessage());
            e.printStackTrace();
        }

        return result; 
    }
     
    public static List<Service> filter() {
        String sql = "select * from servicos_extra";
        List<Service> result = new ArrayList<>();

        try {
            ResultSet rs = Conexao.executeQuery(sql, null);
            while (rs != null && rs.next()) {
                Service service = new Service(
                    rs.getInt("id_servico"),
                    rs.getString("descricao"),
                    rs.getFloat("valor") // Alterado para getFloat
                );
                result.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static int deleteData(int id) {
        String sql = "DELETE FROM servicos_extra WHERE id_servico = ?";
        
        ArrayList<Object> parameter = new ArrayList<>();  
        parameter.add(id);
        
        try {
            return Conexao.executeUpdate(sql, parameter);
        } catch (Exception e) {
            System.err.println("Erro ao deletar quarto: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }}}
    
