package br.com.exemplo.AppAfericaoDiaria.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.exemplo.AppAfericaoDiaria.model.Cliente;

public class ClienteController extends DBController{

    public ClienteController(Context context) {
        super(context, Cliente.TABELA);
    }

    public void inserir(Cliente cliente){
        inserir(deserializar(cliente));
    }

    public int login(String email, String senha){
        Cursor cursor = select(" SELECT * FROM " + Cliente.TABELA + " WHERE "+Cliente.EMAIL + " = ? AND "
                + Cliente.SENHA +" = ?", new String[]{email, senha});

        if(cursor.moveToNext())
            return serializar(cursor).getId();
        else
            return 0; //Significa que não foi encontrado nenhum cliente com essas credenciais
    }

    public Cliente obter(int id) {   //colocar private e criar um get
        Cursor cursor = select(id);
        Cliente cliente = new Cliente();

        while (cursor.moveToNext()) {
            cliente = serializar(cursor);
        }

        cursor.close();

        return cliente;
    }

    public List<Cliente> obterTodos(){
        Cursor cursor = select();
        List<Cliente> clientes = new ArrayList<>();

        while (cursor.moveToNext()){
            clientes.add(serializar(cursor));
        }

        cursor.close();

        return clientes;
    }

    public void atualizar(Cliente cliente){
        atualizar(cliente.getId(), deserializar(cliente));
    }

    private Cliente serializar(Cursor cursor) {
        Cliente cliente = new Cliente();
        cliente.setId(cursor.getInt(cursor.getColumnIndex(Cliente._ID)));
        cliente.setNome(cursor.getString(cursor.getColumnIndex(Cliente.NOME)));
        cliente.setEmail(cursor.getString(cursor.getColumnIndex(Cliente.EMAIL)));
        cliente.setCpf(cursor.getString(cursor.getColumnIndex(Cliente.CPF)));
        cliente.setSenha(cursor.getString(cursor.getColumnIndex(Cliente.SENHA)));

        return cliente;
    }

    private ContentValues deserializar(Cliente cliente) {
        ContentValues valores = new ContentValues();
        valores.put(Cliente.NOME, cliente.getNome());
        valores.put(Cliente.EMAIL, cliente.getEmail());
        valores.put(Cliente.CPF, cliente.getCpf());
        valores.put(Cliente.SENHA, cliente.getSenha());

        return valores;
    }
}
