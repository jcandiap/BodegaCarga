package com.example.bodegacarga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Util.Globales;

public class MainIngresarBodega extends AppCompatActivity {

    private Activity activity;
    EditText txtBodega;
    private TextView lbIp;
    Button btnCancelar;
    Button btnAceptar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_bodega);
        activity = this;
        getSupportActionBar().hide();

        txtBodega = (EditText) findViewById(R.id.txtBodega);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        lbIp = (TextView) findViewById(R.id.lbIp);

        String concat = lbIp.getText().toString();
        lbIp.setText(concat+ Globales.ip);

        txtBodega.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        //new MainIngresarManifiesto.ObtenerManifiesto().execute();
                    }
                }
                return false;
            }
        });
    }

    public void onClick(View v){
        if(v == btnCancelar){
            Globales.manifiesto.clear();
            Globales.hojaruta.clear();
            Globales.ingreso = 0;
            /*
            Intent in = new Intent(MainIngresarManifiesto.this, MainSelRecepcion.class);
            */
            Intent in = new Intent(MainIngresarBodega.this, MainSelModulo.class);
            startActivity(in);
            activity.finish();
        }
        else{
            if(v == btnAceptar){
                //new MainIngresarManifiesto.ObtenerManifiesto().execute();
            }
        }
    }

}
