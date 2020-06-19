package com.example.bodegacarga;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

import TO.HojaRutaTO;
import Util.Globales;
import Util.Utilidades;
import Util.WebServices;

public class MainRecBulRecepcion extends AppCompatActivity {

    private Activity activity;
    private EditText txtEscaneados;
    private EditText txtBulto;
    private EditText txtManifiesto;
    private Button btnImprimir;
    private AlertDialog alert;
    private Button btnCancelar;
    private Button btnFinalizar;
    private int total = Globales.hojaruta.size();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_bultos_manifiestos);
        activity = this;

        txtEscaneados = (EditText) findViewById(R.id.txtEscaneados);
        txtBulto = (EditText) findViewById(R.id.txtBulto);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        txtManifiesto =(EditText) findViewById(R.id.txtManifiesto);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        Globales.hojarutaRespaldo = Globales.hojaruta;

        txtEscaneados.setText(""+Globales.hojarutaEscaneado.size());
        txtManifiesto.setText(""+Globales.hojaruta.size());

        getSupportActionBar().hide();

        txtBulto.requestFocus();

        new ObtenerInformacion().execute();

        txtBulto.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        new IngresarBultos().execute();
                    }
                }
                return true;
            }
        });
    }



    public void onClick(View v) {
        if(v == btnCancelar)
        {
            Globales.hojaruta.clear();
            Globales.manifiesto.clear();
            Globales.hojarutaEscaneado.clear();
            Intent in = new Intent(MainRecBulRecepcion.this, MainSelModulo.class);
            startActivity(in);
            activity.finish();
        }else{
            if(v == txtEscaneados){
                Globales.escaneo = 1;
                Intent in = new Intent(MainRecBulRecepcion.this, MainVerEscaneados.class);
                startActivity(in);
                activity.finish();
            }else{
                if(v == btnFinalizar){
                    finalizar();
                }else{
                    if(v == txtManifiesto){
                        Globales.escaneo = 2;
                        Intent in = new Intent(MainRecBulRecepcion.this, MainVerEscaneados.class);
                        startActivity(in);
                        activity.finish();
                    }
                }
            }
        }
    }


    public void ejecutar(String bulto2) throws IOException, JSONException {
        String respStr = "";
        Utilidades util = new Utilidades();
        WebServices ws = new WebServices();
        txtEscaneados.setText("");
        boolean ingreso = false;
        boolean duplicado = true;
        boolean ingresoN = false;
        boolean existe = false;
        int i = 0;

        if(Globales.hojaruta.isEmpty()){

            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Error: Ya se escanearon todos los bultos")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        }
                    });
            alert = builder.create();
            alert.show();

        }
        else
        {

            System.out.println("Bulto: " + bulto2);

            while(ingreso == false){

                boolean contador = false;

                while(existe == false){
                    if(Globales.hojaruta.get(i).getCodBarra().contains(txtBulto.getText())){
                        existe = true;

                        //Se valida si existen datos en el arreglo
                        if(Globales.hojarutaEscaneado.isEmpty()){

                            int cuenta = 0;
                            while(contador == false){
                                if(Globales.hojaruta.get(cuenta).getCodBarra().contains(txtBulto.getText())) {
                                    HojaRutaTO hr = new HojaRutaTO(Globales.hojaruta.get(cuenta).getOt(), Globales.hojaruta.get(cuenta).getFormaPago(), Globales.hojaruta.get(cuenta).getDestino(), Globales.hojaruta.get(cuenta).getPiezas(),
                                            Globales.hojaruta.get(cuenta).getMetros(), Globales.hojaruta.get(cuenta).getKilos(), Globales.hojaruta.get(cuenta).getValorFlete(), Globales.hojaruta.get(cuenta).getCiudadDestino(), Globales.hojaruta.get(cuenta).getDestinoIata(),
                                            Globales.hojaruta.get(cuenta).getTipoDocumento(), Globales.hojaruta.get(cuenta).getNumDocumento(), Globales.hojaruta.get(cuenta).getPatente(), Globales.hojaruta.get(cuenta).getEstadoOdt(), Globales.hojaruta.get(cuenta).getEmpaque(),
                                            Globales.hojaruta.get(cuenta).getCodBarra(), Globales.hojaruta.get(cuenta).getEstado(), Globales.hojaruta.get(cuenta).getEstadoDos(), Globales.hojaruta.get(cuenta).getPallet(), Globales.hojaruta.get(cuenta).getFecha(),
                                            Globales.hojaruta.get(cuenta).getServicioGeneral(), Globales.hojaruta.get(cuenta).getCobertura());

                                    if(Globales.hojaruta.get(cuenta).getPallet().equals("0")){

                                        int rec = Integer.parseInt(ws.ContarBultos(Globales.hojaruta.get(cuenta).getOt()).getBultos_No_Recepcionados());

                                        if(rec <= 1){
                                            ws.RecepcionarOT(Globales.hojaruta.get(cuenta).getOt(),
                                                    Globales.puntoTO.getCodOficina(),
                                                    "BDREC",
                                                    Globales.hojaruta.get(cuenta).getCobertura(),
                                                    Globales.ip,
                                                    Globales.imei,
                                                    Globales.rut,
                                                    Globales.hojaruta.get(cuenta).getPatente(),
                                                    Globales.hojaruta.get(cuenta).getTipoDocumento(),
                                                    Globales.hojaruta.get(cuenta).getNumDocumento());
                                        }


                                        ws.RegistrarContenido(Globales.hojaruta.get(cuenta).getOt(),
                                                Globales.hojaruta.get(cuenta).getEmpaque(),
                                                Globales.hojaruta.get(cuenta).getCodBarra(),
                                                "",
                                                Globales.puntoTO.getCodOficina(),
                                                "BDREC",
                                                "REC",
                                                Globales.hojaruta.get(cuenta).getCobertura());

                                        ws.RegistrarTracking(Globales.hojaruta.get(cuenta).getOt(),
                                                Globales.hojaruta.get(cuenta).getCodBarra(),
                                                "1",
                                                Globales.puntoTO.getCodOficina(),
                                                "BDREC",
                                                Globales.hojaruta.get(cuenta).getCobertura(),
                                                Globales.rut,
                                                Globales.imei,
                                                Globales.ip);

                                        ws.RegistrarPegaso(Globales.hojaruta.get(i).getOt(),
                                                Globales.rut,
                                                Globales.hojaruta.get(cuenta).getCobertura(),
                                                Globales.puntoTO.getCodOficina(),
                                                Globales.ip,
                                                Globales.mac,
                                                Globales.hojaruta.get(cuenta).getPatente());
                                    }else{

                                        int rec = Integer.parseInt(ws.ContarBultos(Globales.hojaruta.get(cuenta).getOt()).getBultos_No_Recepcionados());

                                        if(rec <= 1){
                                            ws.RecepcionarOT(Globales.hojaruta.get(cuenta).getOt(),
                                                    Globales.puntoTO.getCodOficina(),
                                                    "BDREC",
                                                    Globales.hojaruta.get(cuenta).getCobertura(),
                                                    Globales.ip,
                                                    Globales.imei,
                                                    Globales.rut,
                                                    Globales.hojaruta.get(cuenta).getPatente(),
                                                    Globales.hojaruta.get(cuenta).getTipoDocumento(),
                                                    Globales.hojaruta.get(cuenta).getNumDocumento());
                                        }

                                        ws.RegistrarContenedor(Globales.hojaruta.get(cuenta).getCodBarra(),
                                                Globales.hojaruta.get(cuenta).getCobertura());

                                        ws.RegistrarTracking(Globales.hojaruta.get(cuenta).getOt(),
                                                Globales.hojaruta.get(cuenta).getCodBarra(),
                                                "1",
                                                Globales.puntoTO.getCodOficina(),
                                                "BDREC",
                                                Globales.hojaruta.get(cuenta).getCobertura(),
                                                Globales.rut,
                                                Globales.imei,
                                                Globales.ip);

                                        ws.RegistrarPegaso(Globales.hojaruta.get(cuenta).getOt(),
                                                Globales.rut,
                                                Globales.hojaruta.get(cuenta).getCobertura(),
                                                Globales.puntoTO.getCodOficina(),
                                                Globales.ip,
                                                Globales.mac,
                                                Globales.hojaruta.get(cuenta).getPatente());
                                    }

                                    Globales.hojarutaEscaneado.add(hr);
                                    Globales.hojaruta.remove(cuenta);
                                    txtManifiesto.setText(Globales.hojaruta.size()+"");
                                    contador = true;

                                    ingreso = true;
                                }
                                else{

                                    cuenta++;
                                    contador = false;

                                }
                            }

                        }
                        else{

                            int contador1 = 0;

                            while(duplicado == true){

                                //Se valida si el bulto esta escaneado
                                if(Globales.hojarutaEscaneado.get(contador1).getCodBarra().contains(txtBulto.getText())){

                                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                    builder.setMessage("Error: Bulto duplicado o se ha escaneado dos veces.")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                                }
                                            });
                                    alert = builder.create();
                                    alert.show();

                                    duplicado = false;
                                    ingreso = true;
                                }
                                else{

                                    if(contador1 == Globales.hojarutaEscaneado.size()-1){

                                        int contador2 = 0;
                                        while (ingresoN == false){

                                            if(Globales.hojaruta.get(contador2).getCodBarra().contains(txtBulto.getText())){

                                                HojaRutaTO hr = new HojaRutaTO(Globales.hojaruta.get(contador2).getOt(), Globales.hojaruta.get(contador2).getFormaPago(), Globales.hojaruta.get(contador2).getDestino(), Globales.hojaruta.get(contador2).getPiezas(),
                                                        Globales.hojaruta.get(contador2).getMetros(), Globales.hojaruta.get(contador2).getKilos(), Globales.hojaruta.get(contador2).getValorFlete(), Globales.hojaruta.get(contador2).getCiudadDestino(), Globales.hojaruta.get(contador2).getDestinoIata(),
                                                        Globales.hojaruta.get(contador2).getTipoDocumento(), Globales.hojaruta.get(contador2).getNumDocumento(), Globales.hojaruta.get(contador2).getPatente(), Globales.hojaruta.get(contador2).getEstadoOdt(), Globales.hojaruta.get(contador2).getEmpaque(),
                                                        Globales.hojaruta.get(contador2).getCodBarra(), Globales.hojaruta.get(contador2).getEstado(), Globales.hojaruta.get(contador2).getEstadoDos(), Globales.hojaruta.get(contador2).getPallet(), Globales.hojaruta.get(contador2).getFecha(),
                                                        Globales.hojaruta.get(contador2).getServicioGeneral(), Globales.hojaruta.get(contador2).getCobertura());

                                                if(Globales.hojaruta.get(contador2).getPallet().equals("0")){

                                                    int rec = Integer.parseInt(ws.ContarBultos(Globales.hojaruta.get(contador2).getOt()).getBultos_No_Recepcionados());

                                                    if(rec <= 1){
                                                        ws.RecepcionarOT(Globales.hojaruta.get(contador2).getOt(),
                                                                Globales.puntoTO.getCodOficina(),
                                                                "BDREC",
                                                                Globales.hojaruta.get(contador2).getCobertura(),
                                                                Globales.ip,
                                                                Globales.imei,
                                                                Globales.rut,
                                                                Globales.hojaruta.get(contador2).getPatente(),
                                                                Globales.hojaruta.get(contador2).getTipoDocumento(),
                                                                Globales.hojaruta.get(contador2).getNumDocumento());
                                                    }

                                                    ws.RegistrarContenido(Globales.hojaruta.get(contador2).getOt(),
                                                            Globales.hojaruta.get(contador2).getEmpaque(),
                                                            Globales.hojaruta.get(contador2).getCodBarra(),
                                                            "",
                                                            Globales.puntoTO.getCodOficina(),
                                                            "BDREC",
                                                            "REC",
                                                            Globales.hojaruta.get(contador2).getCobertura());

                                                    ws.RegistrarTracking(Globales.hojaruta.get(contador2).getOt(),
                                                            Globales.hojaruta.get(contador2).getCodBarra(),
                                                            "1",
                                                            Globales.puntoTO.getCodOficina(),
                                                            "BDREC",
                                                            Globales.hojaruta.get(contador2).getCobertura(),
                                                            Globales.rut,
                                                            Globales.imei,
                                                            Globales.ip);

                                                    ws.RegistrarPegaso(Globales.hojaruta.get(i).getOt(),
                                                            Globales.rut,
                                                            Globales.hojaruta.get(contador2).getCobertura(),
                                                            Globales.puntoTO.getCodOficina(),
                                                            Globales.ip,
                                                            Globales.mac,
                                                            Globales.hojaruta.get(contador2).getPatente());
                                                }else{

                                                    int rec = Integer.parseInt(ws.ContarBultos(Globales.hojaruta.get(contador2).getOt()).getBultos_No_Recepcionados());

                                                    if(rec <= 1){
                                                        ws.RecepcionarOT(Globales.hojaruta.get(contador2).getOt(),
                                                                Globales.puntoTO.getCodOficina(),
                                                                "BDREC",
                                                                Globales.hojaruta.get(contador2).getCobertura(),
                                                                Globales.ip,
                                                                Globales.imei,
                                                                Globales.rut,
                                                                Globales.hojaruta.get(contador2).getPatente(),
                                                                Globales.hojaruta.get(contador2).getTipoDocumento(),
                                                                Globales.hojaruta.get(contador2).getNumDocumento());
                                                    }

                                                    ws.RegistrarContenedor(Globales.hojaruta.get(contador2).getCodBarra(),
                                                            Globales.hojaruta.get(contador2).getCobertura());

                                                    ws.RegistrarTracking(Globales.hojaruta.get(contador2).getOt(),
                                                            Globales.hojaruta.get(contador2).getCodBarra(),
                                                            "1",
                                                            Globales.puntoTO.getCodOficina(),
                                                            "BDREC",
                                                            Globales.hojaruta.get(contador2).getCobertura(),
                                                            Globales.rut,
                                                            Globales.imei,
                                                            Globales.ip);

                                                    ws.RegistrarPegaso(Globales.hojaruta.get(contador2).getOt(),
                                                            Globales.rut,
                                                            Globales.hojaruta.get(contador2).getCobertura(),
                                                            Globales.puntoTO.getCodOficina(),
                                                            Globales.ip,
                                                            Globales.mac,
                                                            Globales.hojaruta.get(contador2).getPatente());
                                                }

                                                Globales.hojarutaEscaneado.add(hr);
                                                Globales.hojaruta.remove(contador2);
                                                txtManifiesto.setText(Globales.hojaruta.size()+"");
                                                ingresoN = true;
                                                duplicado = false;
                                                ingreso = true;

                                            }else{

                                                ingresoN = false;
                                                contador2++;

                                            }

                                        }

                                    }
                                    else
                                    {
                                        if(contador1 == Globales.hojarutaEscaneado.size()-1)
                                        {

                                            duplicado = false;

                                        }else{

                                            contador1++;
                                            duplicado = true;

                                        }

                                    }

                                }

                            }

                        }
                    }
                    else
                    {
                        if(i == Globales.hojaruta.size()-1)
                        {
                            existe = true;
                            ingreso = true;
                            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setMessage("Error: Bulto escaneado no existe o no corresponde al manifiesto")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                        }
                                    });
                            alert = builder.create();
                            alert.show();

                        }else{

                            existe = false;
                            i++;
                        }
                    }
                }

            }
        }


        txtEscaneados.setText(Globales.hojarutaEscaneado.size()+"");
        txtBulto.setText("");

    }


    public class IngresarBultos extends AsyncTask<Void, Void, String> {
        ProgressDialog MensajeProgreso;
        String usuario = "";
        boolean isInternet = false;
        boolean respuesta = false;
        String bulto = "";

        @Override
        protected void onPreExecute() {
            bulto = txtBulto.getText().toString().trim();
            super.onPreExecute();
            MensajeProgreso = new ProgressDialog(activity);
            MensajeProgreso.setCancelable(false);
            MensajeProgreso.setIndeterminate(true);
            MensajeProgreso.setMessage("Guardando Registros...");
            MensajeProgreso.show();

        }

        @Override
        protected String doInBackground(Void... params) {
            String respStr = "";
            Utilidades util = new Utilidades();


            try {
                if (util.hasNetworkAccess(activity.getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    isInternet = true;

                    ejecutar(bulto);

                    respuesta = true;
                } else {
                    isInternet = false;
                }
            } catch (Exception e) {
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
                if (respuesta == false) {
                    txtEscaneados.setText(Globales.hojarutaEscaneado.size()+"");
                    txtBulto.setText("");

                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Error: El bulto no corresponde al manifiesto o ya ha sido escaneado.")
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            /*Intent in = new Intent(MainSelModulo.this, MainActivity.class);
            startActivity(in);
            activity.finish();*/
        }
        return true;
    }

    public void finalizar(){
        if(Globales.hojaruta.size() > 0){
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Aun quedan " + Globales.hojaruta.size() + " por escanear\n¿Esta seguro que desea finalizar?")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Intent in = new Intent(MainRecBulRecepcion.this, MainImpresion.class);
                            startActivity(in);
                            activity.finish();

                        }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        }
                    });
            alert = builder.create();
            alert.show();
        }
        else{
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Recepción exitosa, presione Aceptar para regresar a la pantalla de inicio")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Intent in = new Intent(MainRecBulRecepcion.this, MainImpresion.class);
                            startActivity(in);
                            activity.finish();
                        }
                    });
            alert = builder.create();
            alert.show();
        }
    }


    public class ObtenerInformacion extends AsyncTask<Void, Void, String> {
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
                    ws.verCorrelativo();
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

            } else {
                Toast.makeText(activity.getApplicationContext(),
                        "Sin Conexión. No se pudo verificar.", Toast.LENGTH_LONG).show();
            }


        }
    }


}
