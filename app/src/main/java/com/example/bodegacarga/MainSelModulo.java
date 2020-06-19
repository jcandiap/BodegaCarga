package com.example.bodegacarga;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

import Util.Globales;
import Util.Utilidades;
import Util.WebServices;

public class MainSelModulo extends AppCompatActivity {

    private Button btnRecepcion;
    private Button btnAdmision;
    private Button btnDespacho;
    private Button btnBodega;
    private Activity activity;
    private AlertDialog alert;
    private int READ_PHONE_STATE_CODE = 1;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_bodega);
        activity = this;


        btnRecepcion = (Button) findViewById(R.id.btnRecepcion);
        btnAdmision = (Button) findViewById(R.id.btnAdmision);

        getSupportActionBar().hide();

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Globales.imei = telephonyManager.getDeviceId();
        System.out.println(Globales.imei);
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        Globales.ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        WifiInfo info = wm.getConnectionInfo();

        new ObtenerPunto().execute();
        new ObtenerImpresora().execute();

        System.out.println(Globales.mac);
    }

    public void onClick(View v) {
        if(v == btnRecepcion)
        {
            /*
            Intent in = new Intent(MainSelModulo.this, MainSelRecepcion.class);
            */
            Globales.ingreso = 1;
            Intent in = new Intent(MainSelModulo.this, MainIngresarManifiesto.class);
            startActivity(in);
            activity.finish();
        }
        else{
            if(v == btnAdmision){
                Intent in = new Intent(MainSelModulo.this, MainIngresarBodega.class);
                startActivity(in);
                activity.finish();
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

    public class ObtenerPunto extends AsyncTask<Void, Void, String> {
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
            MensajeProgreso.setMessage("Validando bodega...");
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
                    respuesta = ws.ObtenerPunto(mac);
                    if(respuesta == true)
                    {
                        int cobertura = ws.ObtenerCobertura(Globales.puntoTO.getCodInmueble());
                        System.out.println(Globales.cobertura);
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
                if (respuesta == false) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Error: El equipo no tiene una bodega asociada.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                }
                            });
                    alert = builder.create();
                    alert.show();

                }
            } else {
                Toast.makeText(activity.getApplicationContext(),
                        "Sin Conexión. No se pudo verificar.", Toast.LENGTH_LONG).show();
            }


        }
    }

    public class ObtenerImpresora extends AsyncTask<Void, Void, String> {
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
            MensajeProgreso.setMessage("Obteniendo información del punto...");
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
                    respuesta = ws.BuscarImpresora(Globales.puntoTO.getInm_codinmueble());
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
            if (MensajeProgreso.isShowing()) {
                MensajeProgreso.dismiss();
            }
            if (isInternet) {
                if (respuesta == false) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Error: La bodega no tiene una impresora asociada.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                }
                            });
                    alert = builder.create();
                    alert.show();

                }
            } else {
                Toast.makeText(activity.getApplicationContext(),
                        "Sin Conexión. No se pudo verificar.", Toast.LENGTH_LONG).show();
            }


        }
    }

}
