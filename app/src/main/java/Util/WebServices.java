package Util;

import android.net.Uri;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import TO.BultosTO;
import TO.CoberturaTO;
import TO.CorrelativoTO;
import TO.DocInternoDO;
import TO.HojaRutaTO;
import TO.ManifiestoTO;
import TO.PuntoTO;
import TO.RegistroTO;
import TO.RespuestaTO;

public class WebServices {

    private String RUTA_WEB_SERVICE_ANDROID = "http://webservicespro.pullman.cl/DbCarga/rest/Servicios";

    public boolean ValidarUsuario(String rut) throws IOException, JSONException {
        boolean respuesta = false;
        String respStr;
        String respStrClass;
        RespuestaTO res;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/ValidaUsuario/rut=" + rut;
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");
        resp = httpClient.execute(httpPost);
        respStr = EntityUtils.toString(resp.getEntity());

        responseObject = new JSONObject(respStr);

        res = new RespuestaTO();
        res.setRespuesta(responseObject.getString("Mensaje"));

        respStrClass = res.getRespuesta();

        if(respStrClass.equals("0")){
            respuesta = false;
        }
        else{
            Globales.rut = rut;
            respuesta = true;
        }

        return respuesta;
    }

    public boolean ObtenerPunto(String mac) throws IOException, JSONException {
        boolean respuesta = false;
        String respStr;
        String respStrClass;
        RespuestaTO res;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/TraerPunto/mac=" + mac;
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");
        resp = httpClient.execute(httpPost);
        respStr = EntityUtils.toString(resp.getEntity());

        responseObject = new JSONObject(respStr);

        if(respStr.length() > 2){
            Globales.puntoTO = new PuntoTO(responseObject.getString("Bod_Descripcion"),
                    responseObject.getString("Bod_CodBodega"),
                    responseObject.getString("Odp_CodOficina"),
                    responseObject.getString("Odp_Descripcion"),
                    responseObject.getString("Pun_CodPunto"),
                    responseObject.getString("Pun_Descripcion"),
                    responseObject.getString("Inm_Ciudad"),
                    responseObject.getString("Inm_CiudadDescripcion"),
                    responseObject.getString("Pun_DapCodPunto"),
                    responseObject.getString("Dap_Valor"),
                    responseObject.getString("Inm_Comuna"),
                    responseObject.getString("Inm_CiudadDescripcion2"),
                    responseObject.getString("Tp_PunCodTipoPunto"),
                    responseObject.getString("Inm_codinmueble"),
                    responseObject.getString("CodAgencia"),
                    responseObject.getString("CodInmueble"),
                    responseObject.getString("CodOficina"),
                    responseObject.getString("DescOficina"),
                    responseObject.getString("CodPunto"),
                    responseObject.getString("DescPunto"),
                    responseObject.getString("CodCiudad"),
                    responseObject.getString("DescCiudad"),
                    responseObject.getString("CodComuna"),
                    responseObject.getString("DescComuna"),
                    responseObject.getString("CodBodega"),
                    responseObject.getString("DescBodega"));
        }


        if(Globales.puntoTO == null){
            respuesta = false;
        }else
        {
            respuesta = true;
        }

        return respuesta;
    }

    public boolean BuscarImpresora(String codAgencia) throws IOException, JSONException {
        boolean respuesta;
        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/TraerImpresora/codigoagencia=" + codAgencia;
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");
        resp = httpClient.execute(httpPost);
        respStr = EntityUtils.toString(resp.getEntity());

        responseObject = new JSONObject(respStr);

        Globales.impresora = responseObject.getString("impresora");

        if(Globales.impresora.length() < 1){
            respuesta = false;
        }else{
            respuesta = true;
        }

        return respuesta;
    }

    public ArrayList<HojaRutaTO> ObtenerHojadeRuta(String manifiesto) throws IOException, JSONException {
        ArrayList<HojaRutaTO> hoja = new ArrayList<>();

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/traerHojaRuta";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        ManifiestoTO man = new ManifiestoTO(manifiesto);
        String json = gson.toJson(man);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(man));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        respStr = EntityUtils.toString(resp.getEntity());

        HojaRutaTO hojaR;

        responseObject = new JSONObject(respStr);


        try{
            JSONArray ot = responseObject.getJSONArray("hojarutaTO");
            for (int i = 0; i < ot.length(); i++){

                JSONObject objJson = ot.getJSONObject(i);
                boolean estado = false;
                int f = 0;
                String cobertura = "";

                while(estado == false){
                    if(Globales.cobertura.get(f).getCiudad_Cobertura().equals(objJson.getString("Ciudad_Destino"))){
                        cobertura = "39";
                        estado = true;
                    }else{
                        if(f == Globales.cobertura.size()-1){
                            cobertura = "22";
                            estado = true;
                        }
                        else{
                            f++;
                            estado = false;
                        }
                    }
                }





                hojaR = new HojaRutaTO(objJson.getString("OT"),objJson.getString("Forma_Pago"),objJson.getString("Destino"),objJson.getString("Piezas"),
                        objJson.getString("Metros"),objJson.getString("Kilos"),objJson.getString("Valor_Flete"),objJson.getString("Ciudad_Destino"),objJson.getString("Destino_IATA"),
                        objJson.getString("Tipo_Documento"),objJson.getString("Numero_Documento"),objJson.getString("Patente"),objJson.getString("Estado_ODT"),objJson.getString("Empaque"),
                        objJson.getString("Codigo_Barra"),objJson.getString("Estado"),objJson.getString("Estado_DOS"),objJson.getString("Pallet"),objJson.getString("Fecha"),
                        objJson.getString("Servicio_General"), cobertura);
                if(!hojaR.getEstado().equals("REC")){
                    Globales.hojaruta.add(hojaR);
                    hoja.add(hojaR);
                }

                Globales.patente = hojaR.getPatente();
            }
        }catch (Exception e) {
            JSONObject objJson = responseObject.getJSONObject("hojarutaTO");

            boolean estado = false;
            int f = 0;
            String cobertura = "";

            while(estado == false){
                if(Globales.cobertura.get(f).getCiudad_Cobertura().equals(objJson.getString("Ciudad_Destino"))){
                    cobertura = "39";
                    estado = true;
                }else{
                    if(f == Globales.cobertura.size()-1){
                        cobertura = "22";
                        estado = true;
                    }
                    else{
                        f++;
                        estado = false;
                    }
                }
            }

            hojaR = new HojaRutaTO(objJson.getString("OT"), objJson.getString("Forma_Pago"), objJson.getString("Destino"), objJson.getString("Piezas"),
                    objJson.getString("Metros"), objJson.getString("Kilos"), objJson.getString("Valor_Flete"), objJson.getString("Ciudad_Destino"), objJson.getString("Destino_IATA"),
                    objJson.getString("Tipo_Documento"), objJson.getString("Numero_Documento"), objJson.getString("Patente"), objJson.getString("Estado_ODT"), objJson.getString("Empaque"),
                    objJson.getString("Codigo_Barra"), objJson.getString("Estado"), objJson.getString("Estado_DOS"), objJson.getString("Pallet"), objJson.getString("Fecha"),
                    objJson.getString("Servicio_General"), cobertura);

            Globales.hojaruta.add(hojaR);
            hoja.add(hojaR);

            Globales.patente = hojaR.getPatente();
        }

        return hoja;
    }

    public int ObtenerCobertura(String inmueble) throws IOException, JSONException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/traerCobertura";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        CoberturaTO cob = new CoberturaTO(inmueble);
        String json = gson.toJson(cob);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(cob));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        respStr = EntityUtils.toString(resp.getEntity());

        CoberturaTO coTO;

        responseObject = new JSONObject(respStr);


        try{
            JSONArray ot = responseObject.getJSONArray("coberturaTO");
            for (int i = 0; i < ot.length(); i++){
                JSONObject objJson = ot.getJSONObject(i);
                coTO = new CoberturaTO(objJson.getString("Ciudad_Cobertura"),objJson.getString("Servicio_Generico"));

                Globales.cobertura.add(coTO);
            }
        }catch (Exception e) {
            JSONObject objJson = responseObject.getJSONObject("hojarutaTO");
            coTO = new CoberturaTO(objJson.getString("Ciudad_Cobertura"),objJson.getString("Servicio_Generico"));

            Globales.cobertura.add(coTO);
        }

        int respuesta = 0;

        if(Globales.cobertura.size() < 1){
            respuesta = 0;
        }
        else{
            respuesta = 1;
        }

        return respuesta;

    }

    public void RegistrarContenido (String rnumeroOt, String rempaqueOt, String rcodigoBarra, String ritemCodigo, String ragencia, String rbodega, String restado, String restadoOperacional) throws IOException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/InsertarContenido";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        RegistroTO cob = new RegistroTO(rnumeroOt, rempaqueOt, rcodigoBarra, ritemCodigo, ragencia, rbodega, restado, restadoOperacional);
        String json = gson.toJson(cob);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(cob));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        System.out.println("1");

    }

    public void RegistrarContenedor (String codigoBarraBulto, String estadoOperacional) throws IOException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/InsertarContenedor";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        RegistroTO cob = new RegistroTO(codigoBarraBulto, estadoOperacional);
        String json = gson.toJson(cob);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(cob));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        System.out.println("1");
    }

    public void RegistrarTracking(String odt, String codigoBarra, String itemPistoleo, String agencia, String bodega, String estado, String usuario, String mac, String ip) throws IOException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/registrarTrackingPistola";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        RegistroTO cob = new RegistroTO(odt, codigoBarra, itemPistoleo, agencia, bodega, estado, usuario, mac, ip);
        String json = gson.toJson(cob);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(cob));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        System.out.println("1");
    }

    public void RecepcionarOT(String numeroOt, String agencia, String bodega, String estadoOperacion, String ip, String mac, String usuario, String patente, String tipoDoc, String numDoc) throws IOException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/recepcionarOT";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        RegistroTO cob = new RegistroTO(numeroOt, agencia, bodega, estadoOperacion, ip, mac, usuario, patente, tipoDoc, numDoc);
        String json = gson.toJson(cob);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(cob));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        System.out.println("1");
    }

    public void RegistrarPegaso(String documento, String usuario, String estadoOperacional, String agencia, String ip, String mac, String movil) throws IOException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/registrarPegaso/";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        RegistroTO cob = new RegistroTO(documento, usuario, estadoOperacional, agencia, ip, mac, movil);
        String json = gson.toJson(cob);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(cob));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        System.out.println("1");
    }


    public BultosTO ContarBultos(String odt) throws IOException, JSONException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/traerConteoRecepcion/odt="+odt;
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        resp = httpClient.execute(httpPost);
        System.out.println("1");

        respStr = EntityUtils.toString(resp.getEntity());

        BultosTO buTO = new BultosTO();

        responseObject = new JSONObject(respStr);

        try{
            buTO = new BultosTO(responseObject.getString("Bultos_Totales"),responseObject.getString("Bultos_Recepcionados"),responseObject.getString("Bultos_No_Recepcionados"));
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return buTO;
    }


    public void verCorrelativo() throws IOException, JSONException {

        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/TraerCorrelativo";
        Uri myUri = Uri.parse(url);
        Utilidades util = new Utilidades();
        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        String fecha = util.verFechaCorrelativo();
        String agencia = Globales.puntoTO.getCodOficina();
        String bodega = Globales.puntoTO.getCodBodega();

        Gson gson = new Gson();
        CorrelativoTO cor = new CorrelativoTO(fecha, agencia, bodega);
        String json = gson.toJson(cor);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(cor));

        httpPost.setEntity(postingString);

        resp = httpClient.execute(httpPost);
        respStr = EntityUtils.toString(resp.getEntity());

        responseObject = new JSONObject(respStr);

        Globales.correlativo =  responseObject.getString("Correlativo");

        System.out.println("Correlativo = " + Globales.correlativo);
    }

    public void RegistarNodNubOtDocumento(String numeroOt) throws IOException, JSONException {

        HttpClient httpClient;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/registrarNodNubOtDocumento";
        Uri myUri = Uri.parse(url);
        Utilidades util = new Utilidades();
        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        DocInternoDO doc = new DocInternoDO(numeroOt, Globales.numeroRecepcion);
        String json = gson.toJson(doc);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(doc));

        httpPost.setEntity(postingString);

        httpClient.execute(httpPost);
    }


    public void RegistrarDocumentoInterno(String manifiesto) throws IOException {

        HttpClient httpClient;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/registrarDocumentoInterno";
        Uri myUri = Uri.parse(url);
        Utilidades util = new Utilidades();
        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        DocInternoDO doc = new DocInternoDO(manifiesto, Globales.puntoTO.getCodAgencia(), Globales.sumaPiezas, Globales.sumaKilos, Globales.sumaMetrosCubicos, Globales.sumaPrecio);
        String json = gson.toJson(doc);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(doc));

        httpPost.setEntity(postingString);

        httpClient.execute(httpPost);

    }

    public void TraerHojaRutaCantidad(String manifiesto) throws JSONException, IOException {
        String respStr;
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse resp;
        JSONObject responseObject;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/traerHojaRutaCantidad";
        Uri myUri = Uri.parse(url);

        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        ManifiestoTO man = new ManifiestoTO(manifiesto);
        String json = gson.toJson(man);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(man));

        httpPost.setEntity(postingString);


        resp = httpClient.execute(httpPost);
        respStr = EntityUtils.toString(resp.getEntity());

        HojaRutaTO hrTO;

        responseObject = new JSONObject(respStr);


        try{
            JSONArray ot = responseObject.getJSONArray("hojarutacantidadTO");
            for (int i = 0; i < ot.length(); i++){
                JSONObject objJson = ot.getJSONObject(i);
                hrTO = new HojaRutaTO(objJson.getString("OT"),objJson.getString("CANTIDAD"));

                Globales.cantidad.add(hrTO);
            }
        }catch (Exception e) {
            JSONObject objJson = responseObject.getJSONObject("hojarutacantidadTO");
            hrTO = new HojaRutaTO(objJson.getString("OT"),objJson.getString("CANTIDAD"));

            Globales.cantidad.add(hrTO);
        }
    }

    public void RegistrarRecepcionNomina(String patente, String usuario, String agencia) throws IOException, JSONException {
        HttpClient httpClient;
        HttpPost httpPost;
        httpClient = new DefaultHttpClient();
        String url = RUTA_WEB_SERVICE_ANDROID + "/registrarRecepcionNomina";
        Uri myUri = Uri.parse(url);
        Utilidades util = new Utilidades();
        httpPost = new HttpPost(String.valueOf(myUri));
        httpPost.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        HojaRutaTO doc = new HojaRutaTO(patente, usuario, agencia);
        String json = gson.toJson(doc);
        System.out.println(json);

        StringEntity postingString = new StringEntity(gson.toJson(doc));

        httpPost.setEntity(postingString);

        httpClient.execute(httpPost);
    }
}


