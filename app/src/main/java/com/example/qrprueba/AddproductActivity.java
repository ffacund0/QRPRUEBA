package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddproductActivity extends AppCompatActivity {

    private EditText campoNombre;
    private EditText campoDescrip;
    private EditText campoCantidad;
    private EditText campoCodigo;

    private Button btnScanear;
    private Button btnAddProd;
    private Button btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        btnScanear = (Button)findViewById(R.id.btn_scan2);
        btnAtras = (Button)findViewById(R.id.btn_atras);


        btnScanear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerActivity.class));
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
