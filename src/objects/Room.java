package objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class Room {
    private int numero;
    private String tipo;
    private String descricao;
    private int capacidade;
    private int preco_diaria;
    private String status;
    private int camas;
    private int andar;

    public Room(int numero, String tipo, String descricao, int capacidade, int preco_diaria, 
                String status, int camas, int andar) {
        this.numero = numero;
        this.tipo = tipo;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.preco_diaria = preco_diaria;
        this.status = status;
        this.camas = camas;
        this.andar = andar;
    }

    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getPreco_diaria() {
        return preco_diaria;
    }

    public void setPreco_diaria(int preco_diaria) {
        this.preco_diaria = preco_diaria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCamas() {
        return camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    @Override
    public String toString() {
        return "Room [numero=" + numero + ", tipo=" + tipo + ", descricao=" + descricao + 
               ", capacidade=" + capacidade + ", preco_diaria=" + preco_diaria + 
               ", status=" + status + ", camas=" + camas + ", andar=" + andar + "]";
    }

    public static class Filter {
        public static List<Object[]> searchRoom(List<Object> parametersSearch, String op) {
            String sql;
            List<Object[]> result = new ArrayList<>();

            if (op.equals("NÚMERO")) {
                sql = "SELECT * FROM quarto WHERE nmr_quarto LIKE ?";
            } else if (op.equals("ANDAR")) {
                sql = "SELECT * FROM quarto WHERE andar = ?";
            } else if (op.equals("TODOS")) {
                sql = "SELECT * FROM quarto";
                parametersSearch = null; 
            } else {
                return result;
            }

            try (ResultSet rs = Conexao.executeQuery(sql, parametersSearch)) {
                while (rs != null && rs.next()) {
                    Object[] linha = {
                        rs.getInt("id"),
                        rs.getInt("nmr_quarto"),
                        rs.getInt("andar"),
                        rs.getInt("capacidade"),
                        rs.getInt("camas"),
                        "R$" + rs.getInt("preco_diaria"),
                        rs.getString("tipo"),
                        rs.getString("status")
                    };
                    result.add(linha);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public static int addRoom(int nmrQuarto, int andar, int valorDiaria, int maxHospedes, 
                                    int nmrCamas, String descricao, String tipo) {
        String sql = "INSERT INTO quarto (nmr_quarto, tipo, descricao, capacidade, preco_diaria, camas, andar) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        ArrayList<Object> parameter = new ArrayList<>();
        parameter.add(nmrQuarto);
        parameter.add(tipo);
        parameter.add(descricao);
        parameter.add(maxHospedes);
        parameter.add(valorDiaria);
        parameter.add(nmrCamas);
        parameter.add(andar);
        
        
        
        try {
            int insertRoomRows = Conexao.executeUpdate(sql, parameter);
            return insertRoomRows;
        } catch (Exception e) {
            System.err.println("Erro ao inserir quarto: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteData(int id) {
        String sql = "DELETE FROM quarto WHERE id = ?";
        
        ArrayList<Object> parameter = new ArrayList<>();  
        parameter.add(id);
        
        try {
            int deleteRoomRows = Conexao.executeUpdate(sql, parameter);
            return deleteRoomRows;
        } catch (Exception e) {
            System.err.println("Erro ao deletar quarto: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static int editRoom(int id, int nmrQuarto, String tipo, String descricao, int maxHospedes, int valorDiaria, String status, int nmrCamas, int andar) {
        
            ArrayList<Object> parameter = new ArrayList<>();  
            parameter.add(nmrQuarto);
            parameter.add(andar);
            parameter.add(maxHospedes);
            parameter.add(nmrCamas);
            parameter.add(valorDiaria);
            parameter.add(descricao);
            parameter.add(tipo);
            parameter.add(id);

            
            String sql = "UPDATE QUARTO SET nmr_quarto = ?, andar = ?, capacidade = ?, camas = ?, " +
                         "preco_diaria = ?, descricao = ?, tipo = ? WHERE id = ?";
            
            try {
                int updatedRows = Conexao.executeUpdate(sql, parameter);
                return updatedRows;
            } catch (Exception e) {
                System.err.println("Erro ao editar quarto: " + e.getMessage());
                e.printStackTrace();
                return 0;
            }
        }
    
    
    public static int searchPrice(int id) {
        String sql = "SELECT preco_diaria FROM quarto WHERE id = ?;";
        ArrayList<Object> parameter = new ArrayList<>(); 

        parameter.add(id);

        try {
            ResultSet rs = Conexao.executeQuery(sql, parameter);
            
            if (rs.next()) {  
                int price = rs.getInt("preco_diaria");
                return price;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar preço: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}