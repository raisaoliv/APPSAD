package br.com.exemplo.AppAfericaoDiaria.model;

import android.provider.BaseColumns;

public class Cliente implements BaseColumns {

    public static final String TABELA = "Clientes";

    public static final String NOME = "nome", EMAIL = "email",
            SENHA = "senha", CPF = "cpf";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS " + TABELA + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            NOME + " TEXT, "+
            EMAIL + " TEXT, "+
            SENHA + " TEXT, " +
            CPF + " TEXT"+
            ")";
    public static final String DROP = "DROP TABLE IF EXISTS " + TABELA;

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
