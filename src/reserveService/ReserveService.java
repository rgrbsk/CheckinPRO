package reserveService;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.Conexao;

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

	public static int editarQuantidade(int idReserva, int idServico, int novaQuantidade) {
		String sql = "UPDATE reserva_servico SET quantidade = ? WHERE id_reserva = ? AND id_servico = ?";

		List<Object> parametros = new ArrayList<>();
		parametros.add(novaQuantidade);
		parametros.add(idReserva);
		parametros.add(idServico);

		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
			System.out.println("Quantidade de serviço atualizada com sucesso!");
			return linhasAfetadas;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar quantidade de serviço: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return 0;
		}
	}

}
