package com.example.qrprueba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrprueba.utilidades.DatosDB;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnNuevoProd;
    private Button btnVerProd;
    private Button btnConsultar;
    private Button btnIngreso;
    private Button btnSalida;
    private Button btnActReciente;

    public static int flag = 0;
    private ControlStockDB conector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conector = new ControlStockDB(getApplicationContext());
//textview "resultado" a modo de prueba del barcode Scanner
        btnNuevoProd = (Button) findViewById(R.id.btn_nuevoProd);
        btnVerProd = (Button) findViewById(R.id.btn_verProd);
        btnConsultar = (Button) findViewById(R.id.btn_consult);
        btnIngreso = (Button) findViewById(R.id.btn_ingreso);
        btnSalida = (Button) findViewById(R.id.btn_salida);
        btnActReciente= (Button) findViewById(R.id.btn_actividad);


        btnNuevoProd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddproductActivity.class));
            }
        });
        btnVerProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),VerproductActivity.class));
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ConsultarActivity.class));
            }
        });
        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                startActivity(new Intent(getApplicationContext(),ScannerActivity.class));
            }
        });
        btnSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 2;
                startActivity(new Intent(getApplicationContext(),ScannerActivity.class));
            }
        });
        btnActReciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ActividadRecienteActivity.class));
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        if(flag!=0) startActivity(new Intent(getApplicationContext(),IngresoEgresoActivity.class));
    }

}
