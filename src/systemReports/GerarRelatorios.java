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
	       
	        List<Object> parametrosSQL = new ArrayList<>();
	        parametrosSQL.add(reservaId);

	       
	        Map<String, Object> parametrosRelatorio = new HashMap<>();
	        parametrosRelatorio.put("reservaId", reservaId);


	        String caminhoRelatorio = "src/systemReports/ReciboPdf.jasper";
	        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parametrosRelatorio, Conexao.getConnection());

	        // Exibir o relatório
	        JasperViewer.viewReport(jasperPrint, false);

	    } catch (JRException e) {
            e.printStackTrace();
            System.err.println("Erro ao gerar o relatório: " + e.getMessage());
        }
	}}
