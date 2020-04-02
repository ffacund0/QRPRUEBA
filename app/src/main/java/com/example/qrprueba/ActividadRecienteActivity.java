package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.qrprueba.entidades.IngresoEgreso;
import com.example.qrprueba.entidades.Repuesto;
import com.example.qrprueba.utilidades.DatosDB;

import java.util.ArrayList;

public class ActividadRecienteActivity extends AppCompatActivity {

    private ControlStockDB conector;
    private Button btnAtras;
    private ListView lista;

    private ArrayList<IngresoEgreso> listaActividades;
    private ArrayList<String> informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_reciente);
        conector = new ControlStockDB(getApplicationContext());

        btnAtras=(Button)findViewById(R.id.btn_atras4);
        lista=(ListView)findViewById(R.id.list_actividad);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        consultaDB();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,informacion);
        lista.setAdapter(adapter);
    }
    private void consultaDB() {
        SQLiteDatabase db= conector.getReadableDatabase();
        listaActividades = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ DatosDB.TABLA_INGRESOEGRESO,null);

        while (cursor.moveToNext()){
            IngresoEgreso ingresoEgreso = new IngresoEgreso();
            ingresoEgreso.setIdRep(cursor.getInt(0));
            ingresoEgreso.setIdSuc(cursor.getString(1));
            ingresoEgreso.setIdUsr(cursor.getString(2));
            ingresoEgreso.setAccion(cursor.getString(3));
            ingresoEgreso.setFecha(cursor.getString(4));

            listaActividades.add(ingresoEgreso);
        }
        obtenerLista();
    }
    private void obtenerLista() {
        informacion = new ArrayList<>();
        for (IngresoEgreso ie : listaActividades){
            informacion.add(""+ie.getIdRep()+" - "+ie.getIdSuc()+" - "+ie.getIdUsr()+" - "+ie.getAccion()+" - "+ie.getFecha());
        }
    }
}
