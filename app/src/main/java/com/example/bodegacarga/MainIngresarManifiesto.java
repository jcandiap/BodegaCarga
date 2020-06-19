package com.example.bodegacarga;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import TO.HojaRutaTO;
import TO.ManifiestoTO;
import Util.Globales;
import Util.Utilidades;
import Util.WebServices;

public class MainIngresarManifiesto extends AppCompatActivity {

    private Activity activity;
    private Button btnCancelar;
    private Button btnFinalizar;
    private TextView lbIp;
    private AlertDialog alert;
    private EditText txtManifiesto;
    private TextView txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_manifiesto_recepcion);
        activity = this;
        getSupportActionBar().hide();

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        lbIp = (TextView) findViewById(R.id.lbIp);
        txtManifiesto = (EditText) findViewById(R.id.txtManifiesto);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);

        String concat = lbIp.getText().toString();
        lbIp.setText(concat+ Globales.ip);

        txtManifiesto.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        new ObtenerManifiesto().execute();
                    }
                }
                return false;
            }
        });

        txtTitulo.setText("");

        if(Globales.ingreso == 1){
            txtTitulo.setText("Ingrese o escanee el manifiesto");
            txtManifiesto.setFilters(new InputFilter[] {new InputFilter.LengthFilter(11)});
        }
        else{
            if(Globales.ingreso == 2){
                txtTitulo.setText("Ingrese o escanee la planilla");
                txtManifiesto.setFilters(new InputFilter[] {new InputFilter.LengthFilter(11)});
            }else{
                txtTitulo.setText("Ingrese la patente del vehiculo");
                txtManifiesto.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7), new InputFilter.AllCaps()});
            }
        }
    }

    public void onClick(View v){
        if(v == btnCancelar){
            Globales.manifiesto.clear();
            Globales.hojaruta.clear();
            Globales.ingreso = 0;
            /*
            Intent in = new Intent(MainIngresarManifiesto.this, MainSelRecepcion.class);
            */
            Intent in = new Intent(MainIngresarManifiesto.this, MainSelModulo.class);
            startActivity(in);
            activity.finish();
        }
        else{
            if(v == btnFinalizar){
                new ObtenerManifiesto().execute();
            }
        }
    }


    public class ObtenerManifiesto extends AsyncTask<Void, Void, String> {
        ProgressDialog MensajeProgreso;
        String mac = "";
        boolean isInternet = false;
        ArrayList<HojaRutaTO> manifiesto;
        String mani = "";
        int respuesta = 0;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mani = txtManifiesto.getText().toString();
            MensajeProgreso = new ProgressDialog(activity);
            MensajeProgreso.setCancelable(false);
            MensajeProgreso.setIndeterminate(true);
            MensajeProgreso.setMessage("Validando Manifiesto...");
            MensajeProgreso.show();


        }

        @Override
        protected String doInBackground(Void... params) {
            String respStr = "";
            Utilidades util = new Utilidades();
            WebServices ws = new WebServices();
            //Respuestas
            //1 El manifiesto ingresado es valido
            //2 Manifiesto no existe
            //3 Manifiesto ya fue ingresado
            System.out.println("Manifiesto:" + mani);
            try {
                if (util.hasNetworkAccess(activity.getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    isInternet = true;
                    if(mani.length()<10){
                        respuesta = 0;
                    }else {
                        if(Globales.manifiesto.isEmpty()){
                            manifiesto = ws.ObtenerHojadeRuta(mani);
                            if(manifiesto.isEmpty()){
                                respuesta = 2;
                            }
                            else
                            {
                                ManifiestoTO manifiestoTO = new ManifiestoTO(mani);
                                Globales.manifiesto.add(manifiestoTO);
                                respuesta = 1;
                            }
                        }else{
                            int i = 0;
                            while (respuesta == 0){
                                if(Globales.manifiesto.get(i).getManifiesto().contains(mani)){
                                    respuesta = 3;
                                }
                                else{
                                    if(i == Globales.hojaruta.size()-1){
                                        manifiesto = ws.ObtenerHojadeRuta(mani);
                                        if(manifiesto.isEmpty()){
                                            respuesta = 2;
                                        }
                                        else
                                        {
                                            ManifiestoTO manifiestoTO = new ManifiestoTO(mani);
                                            Globales.manifiesto.add(manifiestoTO);
                                            respuesta = 1;
                                        }
                                    }
                                    else{
                                        manifiesto = ws.ObtenerHojadeRuta(mani);
                                        if(manifiesto.isEmpty()){
                                            respuesta = 2;
                                        }
                                        else
                                        {
                                            ManifiestoTO manifiestoTO = new ManifiestoTO(mani);
                                            Globales.manifiesto.add(manifiestoTO);
                                            respuesta = 1;
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    isInternet = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return respStr;
        }

        @Override
        protected void onPostExecute(String result) {
            if (MensajeProgreso.isShowing()) {
                MensajeProgreso.dismiss();
            }
            if (isInternet) {
                if (respuesta == 0) {
                    switch (Globales.ingreso){
                        case 1:
                            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setMessage("Error: El manifiesto ingresado no es valido o ya ha sido ingresado.")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                        }
                                    });
                            alert = builder.create();
                            alert.show();
                            txtManifiesto.setText("");
                            break;
                        case 2:
                            final AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
                            builder2.setMessage("Error: La planilla ingresada no es valida.")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                        }
                                    });
                            alert = builder2.create();
                            alert.show();
                            txtManifiesto.setText("");
                            break;
                        case 3:
                            final AlertDialog.Builder builder3 = new AlertDialog.Builder(activity);
                            builder3.setMessage("Error: La patente ingresada no es valida.")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                        }
                                    });
                            alert = builder3.create();
                            alert.show();
                            txtManifiesto.setText("");
                            break;
                    }

                }
                else{
                    if(respuesta == 1){
                        txtManifiesto.setText("");
                        Intent in = new Intent(MainIngresarManifiesto.this, MainOtroManifiesto.class);
                        startActivity(in);
                        activity.finish();
                    }
                    else{
                        if(respuesta == 2){

                            switch (Globales.ingreso){
                                case 1:
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                    builder.setMessage("Error: El manifiesto no existe o no tiene bultos asociados.")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                                }
                                            });
                                    alert = builder.create();
                                    alert.show();
                                    txtManifiesto.setText("");
                                    break;
                                case 2:
                                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
                                    builder2.setMessage("Error: La planilla no existe o no tiene bultos asociados.")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                                }
                                            });
                                    alert = builder2.create();
                                    alert.show();
                                    txtManifiesto.setText("");
                                    break;
                                case 3:
                                    final AlertDialog.Builder builder3 = new AlertDialog.Builder(activity);
                                    builder3.setMessage("Error: La patente no existe o no tiene bultos asociados.")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                                }
                                            });
                                    alert = builder3.create();
                                    alert.show();
                                    txtManifiesto.setText("");
                                    break;
                            }
                        }
                        else{
                            if(respuesta == 3){
                                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                builder.setMessage("Error: El manifiesto ya fue ingresado.")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                            }
                                        });
                                alert = builder.create();
                                alert.show();
                                txtManifiesto.setText("");
                            }
                            else{
                                if(respuesta == 4){
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                    builder.setMessage("Error: Debe ingresar un minimo de 9 caracteres.")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                                }
                                            });
                                    alert = builder.create();
                                    alert.show();
                                    txtManifiesto.setText("");
                                }
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(activity.getApplicationContext(),
                        "Sin Conexión. No se pudo verificar.", Toast.LENGTH_LONG).show();
            }


        }
    }
}
