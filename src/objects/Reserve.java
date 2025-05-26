package objects;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import conexao.Conexao;

public class Reserve {
	
	
		private  int id;
		private String checkinDate;
		private String checkoutDate;
		private Room nmrQuarto;
		private Client clientCpf;
		private boolean cafeIncluso;
		private boolean servicoQuarto;
		private boolean faxineira;
		private double valorTotal;
		private String status;

		public Reserve(int id, String checkinDate, String checkoutDate, Room nmrQuarto, Client clientCpf,
				boolean cafeIncluso, boolean servicoQuarto, boolean faxineira, double valorTotal, String status) {

			if (checkinDate == null || checkoutDate == null || nmrQuarto == null || clientCpf == null) {
				throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos.");
			}

			if (checkinDate.compareTo(checkoutDate) >= 0) {
				throw new IllegalArgumentException("A data de check-out deve ser posterior à data de check-in.");
			}

			if (valorTotal < 0) {
				throw new IllegalArgumentException("O valor total não pode ser negativo.");
			}

			this.id = id;
			this.checkinDate = checkinDate;
			this.checkoutDate = checkoutDate;
			this.nmrQuarto = nmrQuarto;
			this.clientCpf = clientCpf;
			this.cafeIncluso = cafeIncluso;
			this.servicoQuarto = servicoQuarto;
			this.faxineira = faxineira;
			this.valorTotal = valorTotal;
			this.status = status;
		}
		
		@Override
		public String toString() {
			return "Reserve [id=" + id + ", checkinDate=" + checkinDate + ", checkoutDate=" + checkoutDate + ", nmrQuarto="
					+ nmrQuarto + ", clientCpf=" + clientCpf + ", cafeIncluso=" + cafeIncluso + ", servicoQuarto="
					+ servicoQuarto + ", faxineira=" + faxineira + ", valorTotal=" + valorTotal + "" + ", status=" + status + "]";
		}

		public int getId() {
			return id;
		}
		public String getCheckinDate() {
			return checkinDate;
		}

		public String getCheckoutDate() {
			return checkoutDate;
		}

		public Room getNmrQuarto() {
			return nmrQuarto;
		}

		public void setNmrQuarto(Room nmrQuarto) {
			this.nmrQuarto = nmrQuarto;
		}

		public Client getClientCpf() {
			return clientCpf;
		}

		public void setClientCpf(Client clientCpf) {
			this.clientCpf = clientCpf;
		}

		public boolean isCafeIncluso() {
			return cafeIncluso;
		}

		public void setCafeIncluso(boolean cafeIncluso) {
			this.cafeIncluso = cafeIncluso;
		}

		public boolean isServicoQuarto() {
			return servicoQuarto;
		}

		public void setServicoQuarto(boolean servicoQuarto) {
			this.servicoQuarto = servicoQuarto;
		}

		public boolean isFaxineira() {
			return faxineira;
		}

		public void setFaxineira(boolean faxineira) {
			this.faxineira = faxineira;
		}

		public double getValorTotal() {
			return valorTotal;
		}

		public void setValorTotal(double valorTotal) {
		    if (valorTotal < 0) {
		        throw new IllegalArgumentException("Valor total não pode ser negativo.");
		    }
		    this.valorTotal = valorTotal;
		}

		public void setCheckinDate(String checkinDate) {
		    if (checkinDate == null || (this.checkoutDate != null && checkinDate.compareTo(this.checkoutDate) >= 0)) {
		        throw new IllegalArgumentException("Data de check-in inválida.");
		    }
		    this.checkinDate = checkinDate;
		}

		public void setCheckoutDate(String checkoutDate) {
		    if (checkoutDate == null || (this.checkinDate != null && checkoutDate.compareTo(this.checkinDate) <= 0)) {
		        throw new IllegalArgumentException("Data de check-out inválida.");
		    }
		    this.checkoutDate = checkoutDate;
		}
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		public static boolean quartoDisponivel(int nmrQuarto, String checkinDate, String checkoutDate) {
			
			String sql = "SELECT COUNT(*) " +
	                 "FROM reserva r " +
	                 "JOIN quarto q ON r.id_quarto = q.id " +
	                 "WHERE q.id = ? " +
	                 "AND NOT (r.data_checkout <= ? OR r.data_checkin >= ?)";
			
			
		    List<Object> params = new ArrayList<>();
		    params.add(nmrQuarto);
		    params.add(checkinDate);         
		    params.add(checkoutDate);        

		    try {
		        ResultSet rs = (ResultSet) Conexao.executeQuery(sql, params);
		        if (rs.next()) {
		            int count = rs.getInt(1);
		            return count == 0;
		        } else {
		            System.err.println("Erro: Resultado vazio na verificação de disponibilidade.");
		            return false;
		        }
		    } catch (Exception e) {
		        System.err.println("Erro ao verificar disponibilidade do quarto: " + e.getMessage());
		        e.printStackTrace();
		        return false;
		    }
		}
		

		
		public static  Integer  clienteExistente(String cpf) {
			String sql = "SELECT * FROM cliente where cpf = ?";
			List<Object> params = new ArrayList<>();
			params.add(cpf);
			try {
				ResultSet rs = (ResultSet) Conexao.executeQuery(sql, params);
				if(rs.next()) {
					int clientId = rs.getInt("id_cliente");
					
					
	                return clientId;
	            } else {
	                System.err.println("Erro: Resultado vazio na verificação de cliente existente.");
	                
				}
				
			} catch (Exception e) {
				System.err.println("Erro ao verificar cliente existente: " + e.getMessage());
				e.printStackTrace();
			}
			return null;
			
		}
		
		public static int adicionarReserva(int nmrQuarto, int  clientId, String checkinDate, String checkoutDate, int qntPessoas, int valor) {
			
	
			
			Date data_reserva = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
            String formattedData_reserva = sdf.format(data_reserva);
            
			
			 
			 
			String sql = "INSERT INTO reserva (id_cliente, id_quarto , data_checkin, data_checkout, quantidade_pessoas, data_reserva, valor ) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			List<Object> params = new ArrayList<>();
			params.add(clientId);
			params.add(nmrQuarto);
			params.add(checkinDate);
			params.add(checkoutDate);
			params.add(qntPessoas);
			params.add(formattedData_reserva);
			params.add(valor);
			
			try {
	            int linhasAfetadas = Conexao.executeUpdate(sql, params);
	  
	            return linhasAfetadas;
	        } catch (Exception e) {
	            System.err.println("Erro ao cadastrar reserva: " + e.getMessage());
	            e.printStackTrace();
	            return 0;
	        }
		}


		public static int confirmarEntrada(int id) {
		    String sql = "UPDATE reserva SET status = 'Confirmada' WHERE id = ?";
		    List<Object> params = new ArrayList<>();
		    params.add(id);
		    try {
		        int linhasAfetadas = Conexao.executeUpdate(sql, params);
		        if (linhasAfetadas > 0) {
		      
		            return linhasAfetadas;  
		        }
		    } catch (Exception e) {
		        
		    }
		    return 0;  
	
			
		}
		
		
		public static int cancelarEntrada(int id) {
		    String sql = "DELETE FROM  reserva WHERE ID = ?";
		    List<Object> params = new ArrayList<>();
		    params.add(id);
		    try {
		        int linhasAfetadas = Conexao.executeUpdate(sql, params);
		        if (linhasAfetadas > 0) {
		      
		            return linhasAfetadas;  
		        }
		    } catch (Exception e) {
		        
		    }
		    return 0;  
	
			
		}
		
		public static void pesquisarReservasCheckout(DefaultTableModel modelTable, JTable tabelaReservas) {
		    modelTable.setRowCount(0); // Clear previous data

		    try (Connection conn = Conexao.getConnection()) {
		        String sql = "SELECT r.id, c.nome, r.data_checkin, r.data_checkout, r.id_quarto FROM reserva r "
		                   + "LEFT JOIN cliente c ON r.id_cliente = c.id_cliente";

		        try (PreparedStatement stmt = conn.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {

		            while (rs.next()) {
		                modelTable.addRow(new Object[]{
		                    rs.getInt("id"),
		                    rs.getString("nome"), // Fix alias for 'c.nome'
		                    rs.getDate("data_checkin"),
		                    rs.getDate("data_checkout"),
		                    rs.getInt("id_quarto")
		                });
		            }
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }

		    tabelaReservas.setModel(modelTable); // ✅ Refresh table
		}
		
		
	
	
    public class Filter {
        public static List<Object[]> pesquisarReserva(List<Object> parametersSearch, String op) {
            String sql;
            List<Object[]> result = new ArrayList<>();

            if (op.equals("NOME DO CLIENTE")) {
                sql = "SELECT\r\n"
                        + "    r.id AS id_reserva,\r\n"
                        + "    c.nome,\r\n"
                        + "    c.cpf,\r\n"
                        + "    q.nmr_quarto,\r\n"
                        + "    r.data_checkin AS 'data checkin',\r\n"
                        + "    r.data_checkout AS 'data checkout',\r\n"
                        + "    r.status\r\n"
                        + "FROM \r\n"
                        + "    reserva r\r\n"
                        + "INNER JOIN \r\n"
                        + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                        + "INNER JOIN \r\n"
                        + "    quarto q ON r.id_quarto = q.id\r\n"
                        + "WHERE \r\n"
                        + "    q.status = 'Disponível' AND c.nome LIKE ? ;";

            } else if (op.equals("CPF")) {
                sql = "SELECT\r\n"
                        + "    r.id AS id_reserva,\r\n"
                        + "    c.nome,\r\n"
                        + "    c.cpf,\r\n"
                        + "    q.nmr_quarto,\r\n"
                        + "    r.data_checkin AS 'data checkin',\r\n"
                        + "    r.data_checkout AS 'data checkout',\r\n"
                        + "    r.status\r\n"
                        + "FROM \r\n"
                        + "    reserva r\r\n"
                        + "INNER JOIN \r\n"
                        + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                        + "INNER JOIN \r\n"
                        + "    quarto q ON r.id_quarto = q.id\r\n"
                        + "WHERE \r\n"
                        + "    q.status = 'Disponível' AND c.cpf LIKE ? ;";
                
            } else if (op.equals("QUARTO")) {
                sql = "SELECT\r\n"
                        + "    r.id AS id_reserva,\r\n"
                        + "    c.nome,\r\n"
                        + "    c.cpf,\r\n"
                        + "    q.nmr_quarto,\r\n"
                        + "    r.data_checkin AS 'data checkin',\r\n"
                        + "    r.data_checkout AS 'data checkout',\r\n"
                        + "    r.status\r\n"
                        + "FROM \r\n"
                        + "    reserva r\r\n"
                        + "INNER JOIN \r\n"
                        + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                        + "INNER JOIN \r\n"
                        + "    quarto q ON r.id_quarto = q.id\r\n"
                        + "WHERE \r\n"
                        + "    q.status = 'Disponível' AND q.nmr_quarto LIKE ?;";


            } else if (op.equals("TODOS")) {
                sql = "SELECT \r\n"
                        + "    r.id AS id_reserva,\r\n"
                        + "    c.nome,\r\n"
                        + "    c.cpf,\r\n"
                        + "    q.nmr_quarto,\r\n"
                        + "    r.data_checkin AS 'data checkin',\r\n"
                        + "    r.data_checkout AS 'data checkout',\r\n"
                        + "    r.status\r\n"
                        + "FROM \r\n"
                        + "    reserva r\r\n"
                        + "INNER JOIN \r\n"
                        + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                        + "INNER JOIN \r\n"
                        + "    quarto q ON r.id_quarto = q.id\r\n"
                        + "WHERE \r\n"
                        + "    q.status = 'Disponível';";
            
                parametersSearch = null; 
            } else {
                return result;
            }

            try (ResultSet rs = Conexao.executeQuery(sql, parametersSearch)) {
                while (rs != null && rs.next()) {
                    Object[] linha;
                    
                   
                        linha = new Object[] {
                            rs.getInt("id_reserva"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getInt("nmr_quarto"),
                            rs.getString("data checkin"),
                            rs.getString("data checkout"),
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




	



	}
	

	
	

