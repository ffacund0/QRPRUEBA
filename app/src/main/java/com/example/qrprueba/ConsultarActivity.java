package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button btnConsultar;
    private int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        campoCodigo = (EditText)findViewById(R.id.campo_cod_consult);
        campoNombre = (EditText)findViewById(R.id.campo_nombre_consult);
        campoCantidad = (EditText)findViewById(R.id.campo_cant_consult);

        btnScanear = (Button)findViewById(R.id.btn_scan);
        btnConsultar = (Button)findViewById(R.id.btn_consultar);

        btnScanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerActivity.class));
                flag = 1;
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });
    }

    private void consultar() {
        ControlStockDB conector = new ControlStockDB(this);
        SQLiteDatabase db = conector.getReadableDatabase();

        String[] parametros= {campoCodigo.getText().toString()};
        String[] campos= {DatosDB.NOMBRE,DatosDB.CANTIDAD};

        try{
            Cursor cursor = db.query(DatosDB.TABLA_ARTICULO,campos,DatosDB.CODIGO+"=?",parametros,null,null,null);
            cursor.moveToFirst();

            campoNombre.setText(cursor.getString(0));
            campoCantidad.setText(cursor.getString(1));
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
        campoCodigo.setText(ScannerActivity.result);
        flag = 0;
    }
}
