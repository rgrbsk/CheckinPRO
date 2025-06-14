package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class Service {
    int id;
    String descricao;
    float valor;
    
    public Service() {
        
    }
    
   
    public Service(int id, String descricao, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }
    public Service(int id) {
        this.id = id;
   
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
             ResultSet rs = stmt.executeQuery()) { 
            while (rs.next()) {
                Service service = new Service(
                    rs.getInt("id_servico"),
                    rs.getString("descricao"),
                    rs.getFloat("valor") 
                );
                result.add(service);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviços: " + e.getMessage());
            e.printStackTrace();
        }

        return result; 
    }
    public static int AdicionarServicosReserva(int idServico, int quantidadeServicos, int reservaId) throws Exception {
        String sql = "INSERT INTO reserva_servico (id_servico, quantidade, reservas_id) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Define os valores na query
            stmt.setInt(1, idServico);
            stmt.setInt(2, quantidadeServicos);
            stmt.setInt(3, reservaId);

            // Executa a query de inserção
            int rowsAffected = stmt.executeUpdate();
            
            // Verifica se a inserção ocorreu e retorna o ID gerado
            if (rowsAffected > 0) {
            	
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
        	
            System.err.println("Erro ao adicionar serviço à reserva: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("Erro ao vincular: " + e.getMessage()); 
        }
        
        return -1; 
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
                    rs.getFloat("valor") 
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
        }}
}


	
    
