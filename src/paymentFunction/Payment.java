package paymentFunction;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.Conexao;
import objects.Reserve;

public class Payment {
	private int id;
	private Reserve reserveId;
	private double valorTotal;
	private String metodoPagamento;
	private String statusPagamento;
	private String dataPagamento;
	
	public Payment(int id, Reserve reserveId, double valorTotal, String metodoPagamento, String statusPagamento,
			String dataPagamento) {
		this.id = id;
		this.reserveId = reserveId;
		this.valorTotal = valorTotal;
		this.metodoPagamento = metodoPagamento;
		this.statusPagamento = statusPagamento;
		this.dataPagamento = dataPagamento;
	}

	public Payment() {
		// Construtor padrão
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reserve getReserveId() {
		return reserveId;
	}

	public void setReserveId(Reserve reserveId) {
		this.reserveId = reserveId;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
	public static int registrarPagamento(int id, int reserveId, double valorTotal, String metodoPagamento, String statusPagamento,  String dataPagamento) {
        String sql = "INSERT INTO pagamento (id_reserva, valor_total, metodo_pagamento, status_pagamento, data_pagamento) VALUES ( ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(reserveId);
        params.add(valorTotal);
        params.add(metodoPagamento);
        params.add(statusPagamento);
        params.add(dataPagamento);
        
        try {
            int rowsAffected = Conexao.executeUpdate(sql, params);
            return rowsAffected;
        } catch (Exception e) {
            System.err.println("Erro ao registrar pagamento: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }}
	

		
	
	public static boolean verificarPagamento(int idReserva) {
		String sql = "SELECT COUNT(*) FROM pagamento WHERE id_reserva = ?";
		List<Object> params = new ArrayList<>();
		params.add(idReserva);

		try {
            int count = Conexao.executeScalar(sql, params);
            if (count < 1) {
                JOptionPane.showMessageDialog(null, "Nenhum pagamento já realizado para esta reserva.");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar pagamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
	}
	
	public static int statusReserva(int idReserva) {
        String sql = "UPDATE reserva r SET r.status = 'Concluída' WHERE r.id = ?";
        List<Object> params = new ArrayList<>();
        
        params.add(idReserva);
        
        try {
            int rowsAffected = Conexao.executeUpdate(sql, params);
            return rowsAffected;
        } catch (Exception e) {
            System.err.println("Erro ao alterar status do pagamento: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
	}
	public static int estornarPagamento(int id) {
        String sql = "DELETE FROM pagamento WHERE id_reserva = ?";
        List<Object> params = new ArrayList<>();
        
        params.add(id);
        
        try {
            int rowsAffected = Conexao.executeUpdate(sql, params);
            return rowsAffected;
        } catch (Exception e) {
            System.err.println("Erro ao alterar status do pagamento: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
        
	
	
	}
	}

