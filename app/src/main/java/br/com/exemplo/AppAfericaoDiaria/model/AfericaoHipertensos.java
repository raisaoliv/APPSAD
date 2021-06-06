package br.com.exemplo.AppAfericaoDiaria.model;

public class AfericaoHipertensos {
    public static final String TABELA = "AfericaoHipertensos";

    public static final String DATA = "data", HORA = "hora", SIS = "sis", DIA = "dia",
            PULSO = "pulso", ID_CLIENTE = "id_cliente";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS " + TABELA + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            DATA + " INTEGER, "+
            HORA + " INTEGER, "+
            SIS + " TEXT, "+
            DIA + " TEXT, " +
            PULSO + " TEXT, "+
            ID_CLIENTE + " INTEGER" +
            ")";
    public static final String DROP = "DROP TABLE IF EXISTS " + TABELA;

    private int data;
    private int hora;
    private String sis;  //mmHg pressão sistolica que é o valor menor
    private String dia; //mmHG pressão diastolica, que é o valor maior
    private String pulso; //pulsação do paciente
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

    public String getSis() {
        return sis;
    }

    public void setSis(String sis) {
        this.sis = sis;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

}


