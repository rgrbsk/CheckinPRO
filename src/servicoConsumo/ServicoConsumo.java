package servicoConsumo;

public class ServicoConsumo {
    private String nome;
    private int quantidade;
    private double valorUnitario;

    public ServicoConsumo(String nome, int quantidade, double valorUnitario) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getTotal() {
        return quantidade * valorUnitario;
    }
}