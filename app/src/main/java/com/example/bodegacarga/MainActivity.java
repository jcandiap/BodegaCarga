package com.example.bodegacarga;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;

import java.io.IOException;

import Util.Utilidades;
import Util.WebServices;

public class MainActivity extends AppCompatActivity{

    private Button btnSesion;
    private Button btnBorrar;
    private EditText txtRut;
    private boolean isInternet = false;
    private AlertDialog alert;
    private Activity activity;
    private int READ_PHONE_STATE_CODE = 1;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;


        txtRut = (EditText) findViewById(R.id.txtManifiesto);
        btnSesion = (Button) findViewById(R.id.btnSesion);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);

        getSupportActionBar().hide();

        txtRut.requestFocus();

        int permissionCheck = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.READ_PHONE_STATE );
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso.");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE }, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso!");
        }

        int permissionCheck2 = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE );
        if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso.");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE }, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso!");
        }

        txtRut.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        new ValidaLogin().execute();
                        txtRut.setText("");
                    }
                }
                return false;
            }
        });
    }

    public void onClick(View v) {
        if(v == btnSesion)
        {
            if(txtRut.toString() == "" || txtRut == null) {
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle("Información");
                alertDialog.setMessage("Debe ingresar un rut valido");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            else{
                new ValidaLogin().execute();
            }
        }
        else{
            if(v == btnBorrar){
                txtRut.setText("");
            }
        }
    }



    public class ValidaLogin extends AsyncTask<Void, Void, String> {
        ProgressDialog MensajeProgreso;
        String usuario = "";
        boolean isInternet = false;
        boolean respuesta = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            usuario = txtRut.getText().toString().trim();
            MensajeProgreso = new ProgressDialog(activity);
            MensajeProgreso.setCancelable(false);
            MensajeProgreso.setIndeterminate(true);
            MensajeProgreso.setMessage("Validando Usuario...");
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
                    respuesta = ws.ValidarUsuario(usuario);
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
                    builder.setMessage("Error: Usuario existe pero no tiene bodega asociada.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                }
                            });
                    alert = builder.create();
                    alert.show();
                    txtRut.setText("");

                } else
                {
                    Intent in = new Intent(MainActivity.this, MainSelModulo.class);
                    startActivity(in);
                    activity.finish();
                }
            } else {
                Toast.makeText(activity.getApplicationContext(),
                        "Sin Conexión. No se pudo verificar.", Toast.LENGTH_LONG).show();
                txtRut.setText("");
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
