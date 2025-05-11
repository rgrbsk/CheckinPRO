package objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class Room {

  
	
	public class Filter {
	    public static List<Object[]> searchRoom(List<Object> parametersSearch, String op) {
	        String sql;
	        List<Object[]> result = new ArrayList<>();

	        if (op.equals("NÚMERO")) {
	            sql = "SELECT * FROM quartos WHERE nmr_quarto LIKE ?";
	        } else if (op.equals("ANDAR")) {
	            sql = "SELECT * FROM quartos WHERE andar = ?";
	        } else if (op.equals("TODOS")) {
	            sql = "SELECT * FROM quartos";
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
	                    ("R$") + rs.getInt("preco_diaria"),
	                    rs.getString("tipo"),
	                    rs.getString("status"),
	                    
	                    
	                };
	            
	                
	                result.add(linha);
	               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return result;
	    }
	}
	
	
	public class CadastrarQuarto {
	    public static int ListAddRoom(List<Object> parameters) {
	        String sql = "INSERT INTO quartos (nmr_quarto, andar, capacidade, camas, preco_diaria, descricao, tipo) " +
	                     "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	        
	        try {
	            int insertRoomRows = Conexao.executeUpdate(sql, parameters);
	            
	         
	            return insertRoomRows;
	        } catch (Exception e) {
	            System.err.println("Erro ao inserir quarto: " + e.getMessage());
	            e.printStackTrace();
	            return 0;
	        }
	    }
	    
	    public class deleteData {
		    public static int ListDeleteRoom(List<Object> parameters) {
		        String sql = "DELETE  FROM quartos WHERE id = ? "; 
		                     
		        
		        try {
		            int deleteRoomRows = Conexao.executeUpdate(sql, parameters);
		            
		         
		            return deleteRoomRows;
		        } catch (Exception e) {
		            System.err.println("Erro ao deletar quarto " + e.getMessage());
		            e.printStackTrace();
		            return 0;
	    
		        }
		    }

			}
	    public class EditarQuarto {
	        
	        public static List<Object[]> Id(List<Object> IdSearch) {
	            List<Object[]> result = new ArrayList<>();
	            
	            String sql = "SELECT * FROM quartos WHERE id = ?";
	            
	            try (ResultSet rs = Conexao.executeQuery(sql, IdSearch)) {
	                while (rs != null && rs.next()) {
	                    String status = rs.getString("status");
	                 // Adicionar condição de status    
	                    
	                    Object[] quarto = {
	                        rs.getInt("id"),
	                        rs.getInt("nmr_quarto"),
	                        rs.getInt("andar"),
	                        rs.getInt("capacidade"),
	                        rs.getInt("camas"),
	                        rs.getDouble("preco_diaria"),
	                        rs.getString("descricao"),
	                        rs.getString("tipo"),
	                        status
	                    };
	                    result.add(quarto);
	                }
	            } catch (Exception e) {
	                System.err.println("Erro ao buscar quarto: " + e.getMessage());
	                e.printStackTrace();
	            }
	            
	            return result;
	        }

	        public static int ListEditRoom(List<Object> parameters) {
	            
	            List<Object> idSearch = List.of(parameters.get(parameters.size() - 1)); 
	            List<Object[]> quarto = Id(idSearch);
	            
	            if (!quarto.isEmpty()) {
	                String status = (String) quarto.get(0)[8]; 
	                if (!"Disponível".equals(status)) {
	                    System.err.println("Não é possível editar quarto com status: " + status);
	                    return 0;
	                }
	            }
	            
	            String sql = "UPDATE QUARTOS SET nmr_quarto = ?, andar = ?, capacidade = ?, camas = ?, "
	                       + "preco_diaria = ?, descricao = ?, tipo = ? WHERE id = ?";
	            
	            try {
	                int updatedRows = Conexao.executeUpdate(sql, parameters);
	                return updatedRows;
	            } catch (Exception e) {
	                System.err.println("Erro ao editar quarto: " + e.getMessage());
	                e.printStackTrace();
	                return 0;
	            }
	        }
	    }  
	}
}
	