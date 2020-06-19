package Util;

import java.util.ArrayList;

import TO.CoberturaTO;
import TO.HojaRutaTO;
import TO.ManifiestoTO;
import TO.PuntoTO;

public class Globales {
    public static String rut="";
    public static String imei = "";
    public static String ip = "";
    public static String mac = "00-C-29-DB-C8-96";
    public static ArrayList<HojaRutaTO> hojaruta = new ArrayList<>();
    public static ArrayList<HojaRutaTO> hojarutaEscaneado = new ArrayList<>();
    public static ArrayList<HojaRutaTO> hojarutaRespaldo = new ArrayList<>();
    public static PuntoTO puntoTO;
    public static String impresora = "";
    public static ArrayList<ManifiestoTO> manifiesto = new ArrayList<>();
    public static ArrayList<CoberturaTO> cobertura = new ArrayList<>();
    public static ArrayList<HojaRutaTO> cantidad = new ArrayList<>();
    public static int escaneo = 0;
    public static String patente = "";
    /*
    * Ingreso = 1 (Ingreso por manifiesto)
    * Ingreso = 3 (Ingreso por patente)
    * Ingreso = 2 (Ingreso por planilla)
    */
    public static int ingreso = 0;
    public static String correlativo = "";
    public static String numeroRecepcion = "";


    public static String sumaPiezas = "";
    public static String sumaKilos = "";
    public static String sumaMetrosCubicos = "";
    public static String sumaPrecio = "";
}
