package systemReports;

import conexao.Conexao;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerarRelatorios {

	public static void gerarRecibo(int reservaId) {
	    try {
	        // Consulta SQL trazendo cliente, reserva e serviços
	        String sql = "SELECT r.id AS reservaId, c.nome AS clienteNome, " +
	                     "s.descricao AS servicoDescricao, s.valor AS servicoValor " +
	                     "FROM reserva r " +
	                     "JOIN cliente c ON r.id_cliente = c.id_cliente " +
	                     "LEFT JOIN reserva_servico sr ON r.id = sr.reservas_id " +
	                     "LEFT JOIN servicos_extra s ON sr.id_servico = s.id_servico " +
	                     "WHERE r.id = ?";

	        // Passando o ID da reserva como parâmetro
	        List<Object> parametrosSQL = new ArrayList<>();
	        parametrosSQL.add(reservaId);

	        ResultSet rs = Conexao.executeQuery(sql, parametrosSQL);

	        Map<String, Object> parametrosRelatorio = new HashMap<>();
	        parametrosRelatorio.put("reservaId", reservaId);

	       

	        // Caminho do relatório compilado (.jasper)
	        String caminhoRelatorio = "src/systemReports/ReciboPdf.jasper";
	        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parametrosRelatorio, Conexao.getConnection());

	        // Exibir o relatório
	        JasperViewer.viewReport(jasperPrint, false);

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Erro ao gerar recibo: " + e.getMessage());
	    }
	}
}