package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.qrprueba.entidades.Repuesto;
import com.example.qrprueba.utilidades.DatosDB;

import java.util.ArrayList;

public class VerproductActivity extends AppCompatActivity {

    private ListView lista;
    private Button btnAtras;
    private ControlStockDB conector;
    private ArrayList<String> informacion;
    private ArrayList<Repuesto> listarepuestos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verproduct);

        conector= new ControlStockDB(getApplicationContext());

        btnAtras = (Button) findViewById(R.id.btn_atras2);
        lista = (ListView)findViewById(R.id.listaRepuestos);

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
        listarepuestos = new ArrayList<Repuesto>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ DatosDB.TABLA_REPUESTO,null);

        while (cursor.moveToNext()){
            Repuesto repuesto = new Repuesto();
            repuesto.setId(cursor.getInt(0));
            repuesto.setCodigo(cursor.getLong(1));
            repuesto.setNombre(cursor.getString(2));
            repuesto.setCantidad(cursor.getInt(4));
            repuesto.setIdSuc1(cursor.getString(7));
            repuesto.setFechaRegistro(cursor.getString(6));

            listarepuestos.add(repuesto);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        informacion = new ArrayList<String>();
        for (Repuesto r : listarepuestos){
            informacion.add(""+r.getCodigo()+" - "+r.getNombre()+" - "+r.getCantidad()+" - "+r.getIdSuc1()+" - "+r.getFechaRegistro());
        }
    }
}
