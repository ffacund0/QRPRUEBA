package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static TextView resultado;
    private Button btnNuevoProd;
    private Button btnVerProd;
    private Button btnEscanear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//textview "resultado" a modo de prueba del barcode Scanner
        resultado = (TextView) findViewById(R.id.resultado);
        btnEscanear = (Button) findViewById(R.id.btn_scan);
        btnNuevoProd = (Button) findViewById(R.id.btn_nuevoProd);

        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerActivity.class));
            }
        });
        btnNuevoProd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddproductActivity.class));
            }
        });
    }

}
