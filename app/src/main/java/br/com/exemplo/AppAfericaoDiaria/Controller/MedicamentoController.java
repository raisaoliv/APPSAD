package br.com.exemplo.AppAfericaoDiaria.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.exemplo.AppAfericaoDiaria.model.AfericaoDiabeticos;
import br.com.exemplo.AppAfericaoDiaria.model.Medicamento;

public class MedicamentoController extends DBController{
    public final Context context;

    public MedicamentoController(Context context){
        super(context, Medicamento.TABELA);
        this.context = context;
    }

    public void inserir(Medicamento medicamento){
        inserir(deserializar(medicamento));
    }

    public Medicamento obter(int id) {
        Cursor cursor = select(id);
        Medicamento medicamento = new Medicamento();

        while (cursor.moveToNext()) {
            medicamento = serializar(cursor);
        }

        cursor.close();

        return medicamento;
    }

    public List<Medicamento> obterDoCliente(Integer idCliente){
        Cursor cursor = select("SELECT * FROM " + Medicamento.TABELA + " WHERE id_cliente = ?",
                new String[]{idCliente.toString()});
        List<Medicamento> medicamentos = new ArrayList<>();

        while (cursor.moveToNext()){
            medicamentos.add(serializar(cursor));
        }

        cursor.close();

        return medicamentos;
    }

    public List<Medicamento> obterTodos(){
        Cursor cursor = select();
        List<Medicamento> medicamentos = new ArrayList<>();

        while (cursor.moveToNext()){
            medicamentos.add(serializar(cursor));
        }

        cursor.close();

        return medicamentos;
    }


    private Medicamento serializar(Cursor cursor) {
        Medicamento medicamento = new Medicamento();
        medicamento.setNomemedicamento(cursor.getString(cursor.getColumnIndex(Medicamento.NOME)));
        medicamento.setQuantidade(cursor.getInt(cursor.getColumnIndex(Medicamento.QUANTIDADE)));
        medicamento.setFrequencia(cursor.getInt(cursor.getColumnIndex(Medicamento.FREQUENCIA)));
        medicamento.setunidadedemedida(cursor.getString(cursor.getColumnIndex(Medicamento.UNIDADEDEMEDIDA)));
        medicamento.setObservacoes(cursor.getString(cursor.getColumnIndex(Medicamento.OBSERVACOES)));
        medicamento.setIdCliente(cursor.getInt(cursor.getColumnIndex(Medicamento.ID_CLIENTE)));

        return medicamento;
    }

    private ContentValues deserializar(Medicamento medicamento) {
        ContentValues valores = new ContentValues();
        valores.put(Medicamento.NOME, medicamento.getNomemedicamneto());
        valores.put(Medicamento.QUANTIDADE, medicamento.getQuantidade());
        valores.put(Medicamento.FREQUENCIA, medicamento.getFrequencia());
        valores.put(Medicamento.UNIDADEDEMEDIDA, medicamento.getunidadedemedida());
        valores.put(Medicamento.OBSERVACOES, medicamento.getObservacoes());
        valores.put(Medicamento.ID_CLIENTE, medicamento.getIdCliente());

        return valores;
    }
}
