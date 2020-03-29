package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qrprueba.utilidades.DatosDB;

public class AddproductActivity extends AppCompatActivity {

    private EditText campoNombre;
    private EditText campoDescrip;
    private EditText campoCantidad;
    private EditText campoCodigo;
    private EditText campoProveedor;

    private Button btnScanear;
    private Button btnAddProd;
    private Button btnAtras;

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        btnScanear = (Button)findViewById(R.id.btn_scan2);
        btnAddProd = (Button)findViewById(R.id.btn_add_prod);
        btnAtras = (Button)findViewById(R.id.btn_atras);

        campoCodigo = (EditText)findViewById(R.id.campo_cod);
        campoNombre = (EditText)findViewById(R.id.campo_nombre);
        campoDescrip = (EditText)findViewById(R.id.campo_desc);
        campoCantidad = (EditText)findViewById(R.id.campo_cant);
        campoProveedor = (EditText)findViewById(R.id.campo_proveedor);


        btnScanear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerActivity.class));
                flag = 1;
            }
        });
        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registroArticulo();
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void registroArticulo() {
        ControlStockDB conector = new ControlStockDB(this);
        SQLiteDatabase db = conector.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatosDB.CODIGO,campoCodigo.getText().toString());
        values.put(DatosDB.NOMBRE,campoNombre.getText().toString());
        values.put(DatosDB.DESCRIPCION,campoDescrip.getText().toString());
        values.put(DatosDB.CANTIDAD,campoCantidad.getText().toString());
        values.put(DatosDB.PROVEEDOR,campoProveedor.getText().toString());

        Long idResult = db.insert(DatosDB.TABLA_ARTICULO,null,values);

        Toast.makeText(getApplicationContext(),"IDrow: "+idResult,Toast.LENGTH_SHORT).show();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(flag==1) campoCodigo.setText(ScannerActivity.result);
        flag = 0;
    }
}
