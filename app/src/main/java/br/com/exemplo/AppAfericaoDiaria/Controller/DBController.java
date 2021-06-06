package br.com.exemplo.AppAfericaoDiaria.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.exemplo.AppAfericaoDiaria.database.DBHelper;

public class DBController {
    private final DBHelper dbHelper;
    private final String tabela;

    DBController(Context context, String tabela) {
        dbHelper = new DBHelper(context);
        this.tabela = tabela;
    }

    Cursor select(String query, String[] params) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return database.rawQuery(query, params);
    }

    Cursor select(Integer id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        return database.rawQuery("SELECT * FROM " + tabela + " WHERE _id = ?",
                new String[]{id.toString()});
    }

    Cursor select() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return database.rawQuery("SELECT * FROM " + tabela, null);
    }

    long inserir(ContentValues valores) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.insertOrThrow(tabela, null, valores);
    }

    public int atualizar(String where, String[] params, ContentValues valores) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.update(tabela, valores, where, params);
    }

    public int atualizar(Integer id, ContentValues valores) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.update(tabela, valores, "_id = ?",
                new String[]{id.toString()});
    }

    public boolean remover(Integer id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.delete(tabela, "_id = ?", new String[]{id.toString()}) > 0;
    }

    boolean remover(String where, String[] params) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.delete(tabela, where, params) > 0;
    }
}

