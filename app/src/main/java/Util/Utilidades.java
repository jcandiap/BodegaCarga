package Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Utilidades {


    public boolean hasActiveInternetConnection(Object obj) {
        ConnectivityManager cm = (ConnectivityManager) obj;
        try {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            System.out.println("Debug: [Network] Status: " + (activeNetwork != null && activeNetwork.isConnectedOrConnecting()));
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            System.out.println("Debug: [Network] Status: NOT CONNECTED.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasNetworkAccess(Object obj) {
        if (hasActiveInternetConnection(obj)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://webservices.pullman.cl/").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(8000);
                urlc.connect();
                System.out.println("NETWORK CHECK");
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("NETWORK", "Error checking internet connection.");
            }
        } else {
            Log.d("NETWORK", "No network available!");
        }
        return false;
    }

    public boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < 2; i++) {
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    public String verFechaCorrelativo(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        return fecha;
    }

}
