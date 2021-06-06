package br.com.exemplo.AppAfericaoDiaria.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.exemplo.AppAfericaoDiaria.model.AfericaoDiabeticos;

public class AfericaoDiabeticoController extends DBController {
    private final Context context;

    public AfericaoDiabeticoController(Context context) {
        super(context, AfericaoDiabeticos.TABELA);
        this.context = context;
    }

    public void inserir(AfericaoDiabeticos afericao){
        inserir(deserializar(afericao));
    }

    public AfericaoDiabeticos obter(int id) {
        Cursor cursor = select(id);
        AfericaoDiabeticos afericaoDiabeticos = new AfericaoDiabeticos();

        while (cursor.moveToNext()) {
            afericaoDiabeticos = serializar(cursor);
        }

        cursor.close();

        return afericaoDiabeticos;
    }

    public List<AfericaoDiabeticos> obterDoCliente(Integer idCliente){
        Cursor cursor = select("SELECT * FROM " + AfericaoDiabeticos.TABELA + " WHERE id_cliente = ?",
                new String[]{idCliente.toString()});
        List<AfericaoDiabeticos> afericaoDiabeticos = new ArrayList<>();

        while (cursor.moveToNext()){
            afericaoDiabeticos.add(serializar(cursor));
        }

        cursor.close();

        return afericaoDiabeticos;
    }

    public List<AfericaoDiabeticos> obterTodas(){
        Cursor cursor = select();
        List<AfericaoDiabeticos> afericaoDiabeticos = new ArrayList<>();

        while (cursor.moveToNext()){
            afericaoDiabeticos.add(serializar(cursor));
        }

        cursor.close();

        return afericaoDiabeticos;
    }

    private AfericaoDiabeticos serializar(Cursor cursor) {
        AfericaoDiabeticos afericaoDiabeticos = new AfericaoDiabeticos();
        afericaoDiabeticos.setData(cursor.getInt(cursor.getColumnIndex(AfericaoDiabeticos.DATA)));
        afericaoDiabeticos.setHora(cursor.getInt(cursor.getColumnIndex(AfericaoDiabeticos.HORA)));
        afericaoDiabeticos.setNivelglicose(cursor.getInt(cursor.getColumnIndex(AfericaoDiabeticos.NIVEL_GLICOSE)));
        afericaoDiabeticos.setObservacao(cursor.getString(cursor.getColumnIndex(AfericaoDiabeticos.OBSERVACAO)));
        afericaoDiabeticos.setIdCliente(cursor.getInt(cursor.getColumnIndex(AfericaoDiabeticos.ID_CLIENTE)));

        return afericaoDiabeticos;
    }

    private ContentValues deserializar(AfericaoDiabeticos afericaoDiabeticos) {
        ContentValues valores = new ContentValues();
        valores.put(AfericaoDiabeticos.DATA, afericaoDiabeticos.getData());
        valores.put(AfericaoDiabeticos.HORA, afericaoDiabeticos.getHora());
        valores.put(AfericaoDiabeticos.NIVEL_GLICOSE, afericaoDiabeticos.getNivelglicose());
        valores.put(AfericaoDiabeticos.OBSERVACAO, afericaoDiabeticos.getobservacao());
        valores.put(AfericaoDiabeticos.ID_CLIENTE, afericaoDiabeticos.getIdCliente());

        return valores;
    }


}
