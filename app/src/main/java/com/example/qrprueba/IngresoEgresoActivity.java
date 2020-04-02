package com.example.qrprueba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qrprueba.entidades.IngresoEgreso;
import com.example.qrprueba.utilidades.DatosDB;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class IngresoEgresoActivity extends AppCompatActivity {

    private ControlStockDB conector;
    private Button atras;
    public static String sucursal="";
    private String seleccion="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_egreso);

        conector = new ControlStockDB(getApplicationContext());


        lanzarDialogo();

        atras = (Button)findViewById(R.id.btn_atras3);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.flag=0;
                onBackPressed();
                ScannerActivity.result = "";
            }
        });
    }

    private void checkFlag() {
        switch (MainActivity.flag){
            case 1:
                ingresoProd();
                break;
            case 2:
                salidaProd();
                break;
        }
    }
    private void ingresoProd() {
        Integer cantidad;
        cantidad = consultaDB("ingreso");
        cantidad++;
        actualizarDatos(cantidad);
    }

    private void salidaProd() {
        Integer cantidad;
        cantidad = consultaDB("egreso");
        cantidad--;
        actualizarDatos(cantidad);
    }
    private int consultaDB(String accion){
        SQLiteDatabase db = conector.getReadableDatabase();
        String[] campos = {DatosDB.REP_CANTIDAD,DatosDB.REP_ID,DatosDB.REP_SUC_ID};
        String[] parametros = {ScannerActivity.result};
        Cursor cursor;
        Integer cantidad=0;
        cursor = db.query(DatosDB.TABLA_REPUESTO,campos,DatosDB.REP_CODIGO+"=?",parametros,null,null,null);
        //cursor = db.rawQuery("SELECT "+DatosDB.REP_ID+","+DatosDB.REP_CANTIDAD+" FROM "+DatosDB.TABLA_REPUESTO+" WHERE "+DatosDB.REP_CODIGO+" = "+ScannerActivity.result
        //        +" OR "+DatosDB.REP_SUC_ID+"="+MainActivity.sucursal,null);
        if(cursor.getCount()>1){
            cursor.moveToFirst();
            do{
                if(cursor.getString(2).equals(sucursal)){
                    cantidad = cursor.getInt(0);
                    registroIngEgr(cursor.getInt(1),cursor.getString(2),null,accion);
                    break;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cantidad;
    }

    private void registroIngEgr(int id_rep,String id_suc,String id_usr,String accion) {
        SQLiteDatabase db = conector.getWritableDatabase();
        ContentValues values = new ContentValues();
        String fechaActual = obtenerFecha();

        values.put(DatosDB.IER_REP_ID,id_rep);
        values.put(DatosDB.IER_SUC_ID,id_suc);
        values.put(DatosDB.IER_USU_ID,id_usr);
        values.put(DatosDB.IER_ACCION,accion);
        values.put(DatosDB.IER_FECHA,fechaActual);

        db.insert(DatosDB.TABLA_INGRESOEGRESO,null,values);
        db.close();
    }

    private void actualizarDatos(Integer cant) {

        SQLiteDatabase db = conector.getWritableDatabase();
        String[] parametros = {ScannerActivity.result,sucursal};

        ContentValues values = new ContentValues();
        //ContentValues ingEgrValues = new ContentValues();
        values.put(DatosDB.REP_CANTIDAD,cant);

        db.update(DatosDB.TABLA_REPUESTO,values,DatosDB.REP_CODIGO+"=? AND "+DatosDB.REP_SUC_ID+"=?",parametros);
        //db.insert(DatosDB.TABLA_IEREPUESTO,null,ingEgrValues);

        Toast.makeText(getApplicationContext(),"Nueva cantidad: "+cant,Toast.LENGTH_SHORT).show();
        db.close();
    }
    private void lanzarDialogo() {

        final String[] opciones= {"Sucursal 1","Sucursal 2","Sucursal 3"};
        AlertDialog.Builder dialogo = new AlertDialog.Builder(IngresoEgresoActivity.this);
        seleccion = "S1";
        dialogo.setTitle("Seleccione sucursal.").setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                seleccion=opciones[i];
                switch (seleccion){
                    case "Sucursal 1":
                        sucursal="S1";
                        break;
                    case "Sucursal 2":
                        sucursal="S2";
                        break;
                    case "Sucursal 3":
                        sucursal ="S3";
                        break;

                }
                checkFlag();
            }
        });

        dialogo.create().show();
    }
    private String obtenerFecha(){
        String fechaActual="";
        Calendar fecha = GregorianCalendar.getInstance();
        fechaActual="";

        fechaActual+=(String.format("%04d",fecha.get(Calendar.YEAR))+"-");
        fechaActual+=(String.format("%02d",fecha.get(Calendar.MONTH)+1)+"-");
        fechaActual+=(String.format("%02d",fecha.get(Calendar.DATE))+" ");
        fechaActual+=(String.format("%02d",fecha.get(Calendar.HOUR_OF_DAY))+":");
        fechaActual+=(String.format("%02d",fecha.get(Calendar.MINUTE))+":");
        fechaActual+=(String.format("%02d",fecha.get(Calendar.SECOND)));
        return fechaActual;
    }
}
