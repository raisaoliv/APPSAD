package br.com.exemplo.AppAfericaoDiaria.Controller;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.exemplo.AppAfericaoDiaria.model.AfericaoDiabeticos;
import br.com.exemplo.AppAfericaoDiaria.model.AfericaoHipertensos;

public class AfericaoHipertensoController extends DBController {
    private final Context context;

    public AfericaoHipertensoController(Context context) {
        super(context, AfericaoHipertensos.TABELA);
        this.context = context;
    }

    public void inserirAfericaoHipertensos(AfericaoHipertensos afericao){
        inserir(deserializar(afericao));
    }

    public AfericaoHipertensos obter(int id) {
        Cursor cursor = select(id);
        AfericaoHipertensos afericaoHipertensos = new AfericaoHipertensos();

        while (cursor.moveToNext()) {
            afericaoHipertensos = serializar(cursor);
        }

        cursor.close();

        return afericaoHipertensos;
    }

    public List<AfericaoHipertensos> obterDoCliente(Integer idCliente){
        Cursor cursor = select("SELECT * FROM " + AfericaoHipertensos.TABELA + " WHERE id_cliente = ?",
                new String[]{idCliente.toString()});
        List<AfericaoHipertensos> afericaoHipertensos = new ArrayList<>();

        while (cursor.moveToNext()){
            afericaoHipertensos.add(serializar(cursor));
        }

        cursor.close();

        return afericaoHipertensos;
    }

    public List<AfericaoHipertensos> obterTodas(){
        Cursor cursor = select();
        List<AfericaoHipertensos> afericaoHipertensos = new ArrayList<>();

        while (cursor.moveToNext()){
            afericaoHipertensos.add(serializar(cursor));
        }

        cursor.close();

        return afericaoHipertensos;
    }


    private AfericaoHipertensos serializar(Cursor cursor) {
        AfericaoHipertensos afericaoHipertensos = new AfericaoHipertensos();
        afericaoHipertensos.setData(cursor.getInt(cursor.getColumnIndex(AfericaoHipertensos.DATA)));
        afericaoHipertensos.setHora(cursor.getInt(cursor.getColumnIndex(AfericaoHipertensos.HORA)));
        afericaoHipertensos.setDia(cursor.getString(cursor.getColumnIndex(AfericaoHipertensos.DIA)));
        afericaoHipertensos.setPulso(cursor.getString(cursor.getColumnIndex(AfericaoHipertensos.PULSO)));
        afericaoHipertensos.setSis(cursor.getString(cursor.getColumnIndex(AfericaoHipertensos.SIS)));
        afericaoHipertensos.setIdCliente(cursor.getInt(cursor.getColumnIndex(AfericaoHipertensos.ID_CLIENTE)));

        return afericaoHipertensos;
    }

    private ContentValues deserializar(AfericaoHipertensos afericaoHipertensos) {
        ContentValues valores = new ContentValues();
        valores.put(AfericaoHipertensos.DATA, afericaoHipertensos.getData());
        valores.put(AfericaoHipertensos.HORA, afericaoHipertensos.getHora());
        valores.put(AfericaoHipertensos.DIA, afericaoHipertensos.getDia());
        valores.put(AfericaoHipertensos.SIS, afericaoHipertensos.getSis());
        valores.put(AfericaoHipertensos.PULSO, afericaoHipertensos.getPulso());
        valores.put(AfericaoHipertensos.ID_CLIENTE, afericaoHipertensos.getIdCliente());


        return valores;
    }

}
