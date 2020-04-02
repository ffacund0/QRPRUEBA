package com.example.qrprueba;

import android.content.ContentValues;
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
        db.execSQL(DatosDB.CREATE_TABLA_REPUESTO);
        db.execSQL(DatosDB.CREATE_TABLA_SUCURSAL);
        db.execSQL(DatosDB.CREATE_TABLA_USUARIO);
        db.execSQL(DatosDB.CREATE_TABLA_INGRESOEGRESO);

        //db.execSQL("ALTER TABLE "+DatosDB.TABLA_REPUESTO+" ADD CONSTRAINT fk_sir FOREIGN KEY ("+DatosDB.REP_SUC_ID+") REFERENCES "+DatosDB.TABLA_SUCURSAL+" ("+DatosDB.SUC_ID+")");

        //*******VALORES DE PRUEBA*****
        db.execSQL("INSERT INTO SUCURSAL VALUES ('S1','SUC1')");
        db.execSQL("INSERT INTO SUCURSAL VALUES ('S2','SUC3')");
        db.execSQL("INSERT INTO SUCURSAL VALUES ('S3','SUC3')");

        db.execSQL("INSERT INTO USUARIO VALUES ('U1','USR1')");
        db.execSQL("INSERT INTO USUARIO VALUES ('U2','USR2')");
        db.execSQL("INSERT INTO USUARIO VALUES ('U3','USR3')");
        //********VALORES DE PRUEBA*******//
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS REPUESTO");
        db.execSQL("DROP TABLE IF EXISTS SUCURSAL");
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        db.execSQL("DROP TABLE IF EXISTS INGRESOEGRESO");
        onCreate(db);
    }
}
