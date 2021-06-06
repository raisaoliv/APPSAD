package br.com.exemplo.AppAfericaoDiaria.model;

public class AfericaoDiabeticos {

    public static final String TABELA = "AfericaoDiabeticos";

    public static final String DATA = "data", HORA = "hora", NIVEL_GLICOSE = "nivel_glicose",
            OBSERVACAO = "observacao", ID_CLIENTE = "id_cliente";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS " + TABELA + " (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DATA + " INTEGER, "+
                    HORA + " INTEGER, "+
                    NIVEL_GLICOSE + " TEXT, "+
                    OBSERVACAO + " TEXT, " +
                    ID_CLIENTE + " INTEGER" +
                    ")";
    public static final String DROP = "DROP TABLE IF EXISTS " + TABELA;


    private int data;
    private int hora;
    private int nivelglicose;
    private String observacao;
    private int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getNivelglicose() {
        return nivelglicose;
    }

    public void setNivelglicose(int nivelglicose) {
        this.nivelglicose = nivelglicose;
    }

    public String getobservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
