package reserveService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.Conexao;
import objects.Service;
import servicoConsumo.ServicoConsumo;

public class ReserveService {
	private int idReserva;
	private int idServico;
	private int quantidade = 1;

	public ReserveService() {
	}

	public ReserveService(int idReserva, int idServico, int quantidade) {
		this.idReserva = idReserva;
		this.idServico = idServico;
		this.quantidade = quantidade;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getIdServico() {
		return idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "ReserveService{" + "idReserva=" + idReserva + ", idServico=" + idServico + ", quantidade=" + quantidade
				+ '}';
	}

	public static int cadastrarReserva(int idReserva, int idServico, int quantidade) {
		String sql = "INSERT INTO reserva_servico (id_reserva, id_servico, quantidade) VALUES (?, ?, ?)";

		List<Object> parametros = new ArrayList<>();
		parametros.add(idReserva);
		parametros.add(idServico);
		parametros.add(quantidade);

		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
			System.out.println("Reserva de serviço cadastrada com sucesso!");
			return linhasAfetadas;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar reserva de serviço: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return 0;

		}
	}

	public static int cancelarReserva(int idReserva, int idServico) {
		String sql = "DELETE FROM reserva_servico WHERE id_reserva = ? AND id_servico = ?";

		List<Object> parametros = new ArrayList<>();
		parametros.add(idReserva);
		parametros.add(idServico);

		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
			System.out.println("Reserva de serviço cancelada com sucesso!");
			return linhasAfetadas;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cancelar reserva de serviço: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(); 
			return 0;
		}
	}
	public static List<ServicoConsumo> buscarServicosVinculados(int reservaId) {
	    List<ServicoConsumo> lista = new ArrayList<>();

	    String sql = "SELECT s.descricao, rs.quantidade, s.valor " +
	                 "FROM reserva_servico rs " +
	                 "JOIN servicos_extra s ON rs.id_servico = s.id_servico " +
	                 "WHERE rs.reservas_id = ?";

	    List<Object> params = Arrays.asList(reservaId);

	    try (ResultSet rs = Conexao.executeQuery(sql, params)) {
	        while (rs != null && rs.next()) {
	            ServicoConsumo item = new ServicoConsumo(
	                rs.getString("descricao"),
	                rs.getInt("quantidade"),
	                rs.getDouble("valor")
	            );
	            lista.add(item);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	public static boolean verificarServicosReserva(int reservaId, int idServico) {
	    String sqlCheck = "SELECT COUNT(*) FROM reserva_servico WHERE reservas_id = ? AND id_servico = ?";
	    List<Object> parametrosCheck = new ArrayList<>();
	    parametrosCheck.add(reservaId);
	    parametrosCheck.add(idServico);

	    try {
	        ResultSet rs = (ResultSet) Conexao.executeQuery(sqlCheck, parametrosCheck);

	        if (rs != null) {
	            if (rs.next() && rs.getInt(1) > 0) {
	                System.out.println("Serviço já vinculado à reserva.");
	                
	                return true;
	            }
	        } else {
	            System.err.println("ResultSet retornou null. Verifique a conexão ou a consulta.");
	        }

	    } catch (Exception e) {
	        System.err.println("Erro ao verificar serviços vinculados à reserva: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public static int buscarQuantidadeServicoReserva(int reservaId, int idServico) {
	    String sql = "SELECT quantidade FROM reserva_servico WHERE reservas_id = ? AND id_servico = ?";
	    List<Object> params = new ArrayList<>();
	    params.add(reservaId);
	    params.add(idServico);

	    try (ResultSet rs = (ResultSet) Conexao.executeQuery(sql, params)) {
	        if (rs != null && rs.next()) {
	            return rs.getInt("quantidade");
	        }
	    } catch (Exception e) {
	        System.err.println("Erro ao buscar quantidade do serviço: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return 0; 
	}

	public static int editarQuantidade(int novaQuantidade, int idReserva, int idServico) throws Exception {
		String sql = "UPDATE reserva_servico SET quantidade = ? WHERE reservas_id = ? AND id_servico = ?";

		List<Object> parametros = new ArrayList<>();
		parametros.add(novaQuantidade);
		parametros.add(idReserva);
		parametros.add(idServico);
		
		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
			System.out.println("Quantidade de serviço atualizada com sucesso!");
			return linhasAfetadas;
		} catch (Exception ex) {
			throw new Exception("Erro no banco de dados: " + ex.getMessage()); // 
			//e.printStackTrace();
			//>return 0;
		}
	}

}
