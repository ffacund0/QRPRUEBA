package com.example.qrprueba;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.qrprueba.utilidades.DatosDB;

public class ControlStockDB extends SQLiteOpenHelper {

    public ControlStockDB(@Nullable Context context) {
        super(context, "controlstockdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosDB.CREATE_TABLA_ARTICULO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS ARTICULO");
        onCreate(db);
    }
}
