package com.example.bodegacarga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Util.Globales;

public class MainSelRecepcion extends AppCompatActivity {

    private Button btnSalir;
    private Button btnManifiesto;
    private Button btnPlanilla;
    private Button btnPatente;
    private Activity activity;
    private TextView lbIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepcion_opciones);
        activity = this;

        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnManifiesto = (Button) findViewById(R.id.btnManifiesto);
        btnPlanilla = (Button) findViewById(R.id.btnPlanilla);
        btnPatente = (Button) findViewById(R.id.btnPatente);
        lbIp = (TextView) findViewById(R.id.lbIp);

        getSupportActionBar().hide();


        String concat = lbIp.getText().toString();

        lbIp.setText(concat+Globales.ip);
    }

    public void onClick(View v) {
        if(v == btnSalir)
        {
            Intent in = new Intent(MainSelRecepcion.this, MainSelModulo.class);
            startActivity(in);
            activity.finish();
        }
        else{
            if(v == btnManifiesto){
                Globales.ingreso = 1;
                Intent in = new Intent(MainSelRecepcion.this, MainIngresarManifiesto.class);
                startActivity(in);
                activity.finish();
            }else{
                if(v == btnPlanilla){
                    Globales.ingreso = 2;
                    Intent in = new Intent(MainSelRecepcion.this, MainIngresarManifiesto.class);
                    startActivity(in);
                    activity.finish();
                }
                else{
                    if(v == btnPatente){
                        Globales.ingreso = 3;
                        Intent in = new Intent(MainSelRecepcion.this, MainIngresarManifiesto.class);
                        startActivity(in);
                        activity.finish();
                    }
                }
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            /*Intent in = new Intent(MainSelModulo.this, MainActivity.class);
            startActivity(in);
            activity.finish();*/
        }
        return true;
    }
}
