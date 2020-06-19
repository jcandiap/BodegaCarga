package com.example.bodegacarga;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

import Util.Globales;
import Util.Utilidades;
import Util.WebServices;

public class MainOtroManifiesto extends AppCompatActivity {

    private Activity activity;
    private Button btnAceptar;
    private Button btnCancelar;
    private TextView lbIp;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_manifiestos);
        activity = this;

        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        lbIp = (TextView) findViewById(R.id.lbIp);

        String concat = lbIp.getText().toString();
        lbIp.setText(concat+ Globales.ip);

        getSupportActionBar().hide();

    }


    public void onClick(View v){
        if(v == btnAceptar){
            Intent in = new Intent(MainOtroManifiesto.this, MainIngresarManifiesto.class);
            startActivity(in);
            activity.finish();
        }
        else{
            if(v == btnCancelar){
                new VerificarManifiestos().execute();
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

    public class VerificarManifiestos extends AsyncTask<Void, Void, String> {
        ProgressDialog MensajeProgreso;
        String mac = "";
        boolean isInternet = false;
        boolean respuesta = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mac = Globales.mac;
            MensajeProgreso = new ProgressDialog(activity);
            MensajeProgreso.setCancelable(false);
            MensajeProgreso.setIndeterminate(true);
            MensajeProgreso.setMessage("Obteniendo información...");
            MensajeProgreso.show();


        }

        @Override
        protected String doInBackground(Void... params) {
            String respStr = "";
            Utilidades util = new Utilidades();
            WebServices ws = new WebServices();

            try {
                if (util.hasNetworkAccess(activity.getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    isInternet = true;

                    for(int i = 0; i < Globales.manifiesto.size(); i++){
                        ws.TraerHojaRutaCantidad(Globales.manifiesto.get(i).getManifiesto());
                    }

                    if(Globales.cantidad.size() < 1){
                        System.out.println("Correcto");
                    }

                } else {
                    isInternet = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return respStr;
        }

        @Override
        protected void onPostExecute(String result) {
            Utilidades util = new Utilidades();
            WebServices ws = new WebServices();
            if (MensajeProgreso.isShowing()) {
                MensajeProgreso.dismiss();
            }
            if (isInternet) {

                Intent in = new Intent(MainOtroManifiesto.this, MainRecBulRecepcion.class);
                startActivity(in);
                activity.finish();

            } else {
                Toast.makeText(activity.getApplicationContext(),
                        "Sin Conexión. No se pudo verificar.", Toast.LENGTH_LONG).show();
            }


        }
    }
}
