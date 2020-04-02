package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qrprueba.utilidades.DatosDB;

public class ConsultarActivity extends AppCompatActivity {

    private EditText campoCodigo;
    private EditText campoNombre;
    private EditText campoCantidad;

    private Button btnScanear;
    private Button btnModificar;
    private Button btnDelete;
    private Button btnConsultar;

    private boolean flag=false;
    private ControlStockDB conector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        conector= new ControlStockDB(this);

        campoCodigo = (EditText)findViewById(R.id.campo_cod_consult);
        campoNombre = (EditText)findViewById(R.id.campo_nombre_consult);
        campoCantidad = (EditText)findViewById(R.id.campo_cant_consult);

        btnScanear = (Button)findViewById(R.id.btn_scan);
        btnModificar = (Button)findViewById(R.id.btn_modificar);
        btnDelete = (Button)findViewById(R.id.btn_delete);
        btnConsultar = (Button) findViewById(R.id.btn_consultar);

        btnScanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerActivity.class));
                flag = true;
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarArticulo();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarArticulo();
            }
        });
    }

    private void eliminarArticulo() {
        SQLiteDatabase db = conector.getWritableDatabase();
        String[] parametros = {campoCodigo.getText().toString()};

        db.delete(DatosDB.TABLA_REPUESTO,DatosDB.REP_CODIGO+"=?",parametros);
        campoCodigo.setText("");
        campoCantidad.setText("");
        campoNombre.setText("");
        Toast.makeText(getApplicationContext(),"Eliminado correctamente",Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void actualizarArticulo() {
        SQLiteDatabase db = conector.getWritableDatabase();
        String[] parametros = {campoCodigo.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(DatosDB.REP_NOMBRE,campoNombre.getText().toString());
        values.put(DatosDB.REP_CANTIDAD,campoCantidad.getText().toString());

        db.update(DatosDB.TABLA_REPUESTO,values,DatosDB.REP_CODIGO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Modificado correctamente",Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void consultar() {
        SQLiteDatabase db = conector.getReadableDatabase();
        Cursor cursor;
        String[] parametros= {campoCodigo.getText().toString()};
        String[] campos= {DatosDB.REP_ID,DatosDB.REP_NOMBRE,DatosDB.REP_CANTIDAD,DatosDB.REP_SUC_ID};

        try{
            cursor = db.query(DatosDB.TABLA_REPUESTO,campos,DatosDB.REP_CODIGO+"=?",parametros,null,null,null);
            cursor.moveToFirst();

            campoNombre.setText(cursor.getString(1));
            campoCantidad.setText(cursor.getString(2));
            Toast.makeText(getApplicationContext(),""+cursor.getString(3),Toast.LENGTH_SHORT).show();
            cursor.close();

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Articulo no encontrado",Toast.LENGTH_SHORT).show();
            campoNombre.setText("");
            campoCantidad.setText("");
        }
        db.close();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(flag) {
            campoCodigo.setText(ScannerActivity.result);
            flag = false;
        }
    }
}
