package com.example.qrprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.qrprueba.utilidades.DatosDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddproductActivity extends AppCompatActivity {

    private EditText campoNombre;
    private EditText campoDescrip;
    private EditText campoCantidad;
    private EditText campoCodigo;
    private EditText campoProveedor;

    private Spinner comboSuc;

    private Button btnScanear;
    private Button btnAddProd;
    private Button btnAtras;

    private int flag = 0;
    private static int idArt=1;
    private ControlStockDB conector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
         conector = new ControlStockDB(this);

        btnScanear = (Button)findViewById(R.id.btn_scan2);
        btnAddProd = (Button)findViewById(R.id.btn_add_prod);
        btnAtras = (Button)findViewById(R.id.btn_atras);

        comboSuc = (Spinner)findViewById(R.id.combo_suc_add);

        campoCodigo = (EditText)findViewById(R.id.campo_cod);
        campoNombre = (EditText)findViewById(R.id.campo_nombre);
        campoDescrip = (EditText)findViewById(R.id.campo_desc);
        campoCantidad = (EditText)findViewById(R.id.campo_cant);
        campoProveedor = (EditText)findViewById(R.id.campo_proveedor);

        ArrayList<String> contenidoSpinner=new ArrayList<>();
        contenidoSpinner.add("Seleccione");
        contenidoSpinner.add("- sucursal 1");
        contenidoSpinner.add("- sucursal 2");
        contenidoSpinner.add("- sucursal 3");
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,contenidoSpinner);
        comboSuc.setAdapter(adaptador);

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

        String validacion = validarCampos();
        if(!validacion.isEmpty()){
            Toast.makeText(getApplicationContext(),"llenar campo(s): "+validacion,Toast.LENGTH_LONG).show();
            return;
        }
        long idResult=insertArt();

        if(idResult!=-1){
            Toast.makeText(getApplicationContext(),"ID carga: "+idResult,Toast.LENGTH_SHORT).show();
            campoCodigo.setText("");
            campoNombre.setText("");
            campoDescrip.setText("");
            campoCantidad.setText("");
            campoProveedor.setText("");
        }else{
            Toast.makeText(getApplicationContext(),"ERROR al cargar",Toast.LENGTH_SHORT).show();
        }
    }

    private long insertArt() {
        SQLiteDatabase db = conector.getWritableDatabase();

        ContentValues values = new ContentValues();
        String fechaActual=obtenerFecha();
        //values.put(DatosDB.REP_ID,idArt);
        Toast.makeText(getApplicationContext(),fechaActual,Toast.LENGTH_SHORT).show();
        //if(sucursal.isEmpty())return -1;
        values.put(DatosDB.REP_CODIGO,campoCodigo.getText().toString());
        values.put(DatosDB.REP_NOMBRE,campoNombre.getText().toString());
        values.put(DatosDB.REP_DESCRIPCION,campoDescrip.getText().toString());
        values.put(DatosDB.REP_CANTIDAD,campoCantidad.getText().toString());
        values.put(DatosDB.REP_PROVEEDOR,campoProveedor.getText().toString());
        values.put(DatosDB.REP_FECHA, fechaActual);
        values.put(DatosDB.REP_SUC_ID,obtenerSucursal());

        Long idResult = db.insert(DatosDB.TABLA_REPUESTO,null,values);
        db.close();
        idArt++;
        return idResult;
    }

    private String obtenerSucursal() {

        switch ((String)comboSuc.getSelectedItem()){
            case "- sucursal 1":
                return "S1";
            case "- sucursal 2":
                return "S2";
            case "- sucursal 3":
                return "S3";
        }
        return "";
    }

    private String validarCampos() {
        String retorno="";
        if(obtenerSucursal().isEmpty()){
            retorno+= ("sucursal, ");
        }
        if(campoCodigo.getText().toString().isEmpty()){
            campoCodigo.setError("Obligatorio");
            retorno+= (DatosDB.REP_CODIGO+", ");
        }if(campoNombre.getText().toString().isEmpty()){
            campoNombre.setError("Obligatorio");
            retorno+= (DatosDB.REP_NOMBRE+", ");
        }if(campoDescrip.getText().toString().isEmpty()){
            campoDescrip.setError("Obligatorio");
            retorno+= (DatosDB.REP_DESCRIPCION+",");
        }if(campoCantidad.getText().toString().isEmpty()){
            campoCantidad.setError("Obligatorio");
            retorno+= (DatosDB.REP_CANTIDAD+", ");
        }if(campoProveedor.getText().toString().isEmpty()){
            campoProveedor.setError("Obligatorio");
            retorno+= (DatosDB.REP_PROVEEDOR+", ");
        }
        return retorno;
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

    @Override
    protected void onResume() {
        super.onResume();
        if(flag==1) campoCodigo.setText(ScannerActivity.result);
        flag = 0;
    }
}
