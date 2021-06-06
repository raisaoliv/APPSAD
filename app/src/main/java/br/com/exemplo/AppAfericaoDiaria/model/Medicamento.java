package br.com.exemplo.AppAfericaoDiaria.model;

public class Medicamento {
    public static final String TABELA = "Medicamentos";

    public static final String NOME = "nomemedicamento", QUANTIDADE = "quantidade",
            UNIDADEDEMEDIDA = "unidadedemedida", FREQUENCIA = "frequencia",
            OBSERVACOES = "observacoes", ID_CLIENTE = "id_cliente";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS " + TABELA + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            NOME + " TEXT, "+
            QUANTIDADE + " INTEGER, "+
            UNIDADEDEMEDIDA + " TEXT, "+
            OBSERVACOES + " TEXT, " +
            FREQUENCIA + " INTEGER, "+
            ID_CLIENTE + " INTEGER" +
            ")";
    public static final String DROP = "DROP TABLE IF EXISTS " + TABELA;

    private int quantidade;
    private String unidadedemedida;
    private String nomemedicamento;
    private String observacoes;
    private int frequencia;
    private int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomemedicamneto() {
        return nomemedicamento;
    }

    public void setNomemedicamento(String nomemedicamento) {
        this.nomemedicamento = nomemedicamento;
    }

    public String getunidadedemedida() {
        return unidadedemedida;
    }

    public void setunidadedemedida(String unidadedemedida) {
        this.unidadedemedida = unidadedemedida;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String Observacoes) {
        this.observacoes = observacoes;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
