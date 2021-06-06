package br.com.exemplo.AppAfericaoDiaria.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.exemplo.AppAfericaoDiaria.model.AfericaoDiabeticos;
import br.com.exemplo.AppAfericaoDiaria.model.AfericaoHipertensos;
import br.com.exemplo.AppAfericaoDiaria.model.Cliente;
import br.com.exemplo.AppAfericaoDiaria.model.Medicamento;

public class DBHelper extends SQLiteOpenHelper {
    public static final String NOME_DB = "BancoApp.db";

    private static final int VERSAO = 2;

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Cliente.CREATE);
        sqLiteDatabase.execSQL(Medicamento.CREATE);
        sqLiteDatabase.execSQL(AfericaoDiabeticos.CREATE);
        sqLiteDatabase.execSQL(AfericaoHipertensos.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        drop(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        drop(db);
        onCreate(db);
    }

    private void drop(SQLiteDatabase db) {
        db.execSQL(Cliente.DROP);
        db.execSQL(Medicamento.DROP);
        db.execSQL(AfericaoDiabeticos.DROP);
        db.execSQL(AfericaoHipertensos.DROP);
    }
}
