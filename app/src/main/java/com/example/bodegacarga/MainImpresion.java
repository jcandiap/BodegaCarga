package com.example.bodegacarga;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Util.Common;
import Util.Globales;
import Util.PdfDocumentAdapter;
import Util.Utilidades;
import Util.WebServices;

public class MainImpresion extends AppCompatActivity {

    private Activity activity;
    Button btnVImprimir;
    Button btnAceptar;
    private AlertDialog alert;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impresion_final);
        activity = this;

        btnVImprimir = (Button) findViewById(R.id.btnVImprimir);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);

        getSupportActionBar().hide();

        new MainImpresion.GenerarArchivo().execute();

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        btnVImprimir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new MainImpresion.GenerarArchivo().execute();
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }

    public void onClick(View v){
        if(v == btnAceptar){
            new Finalizar().execute();
        }
    }

    private void createPDFFile(String path) {

        if(new File(path).exists())
            new File(path).delete();
        try {
            Document document = new Document();

            //Save
            PdfWriter.getInstance(document, new FileOutputStream(path));

            //Open to write
            document.open();

            //Setting
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("Pullman Cargo S.A.");
            document.addCreator("José Candia");

            //Font Setting
            BaseColor colorAccent = new BaseColor(0, 153, 204, 255);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;

            //Custom font
            BaseFont fontName = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font titleFont = new Font(fontName, 25.0f, Font.NORMAL, BaseColor.BLACK);
            Font datosFont = new Font(fontName, 15.0f, Font.NORMAL, BaseColor.BLACK);
            Font footerFont = new Font(fontName, 7.0f, Font.NORMAL, BaseColor.BLACK);

            String fecha = obtenerFecha();

            addNewItemWithLeftAndRight(document, fecha, "Empresas Pullman", datosFont, datosFont);

            addLineSpace(document);
            addLineSpace(document);

            addLineSeparator(document);

            String correlativo = Globales.correlativo;
            String corNuevo = "";
            if(correlativo.length()==1){
                corNuevo = "00" + correlativo;
            }else{
                if(correlativo.length()==2){
                    corNuevo = "0"+ correlativo;
                }
                else
                {
                    corNuevo = correlativo;
                }
            }

            Globales.numeroRecepcion = Globales.puntoTO.getCodOficina()+Globales.puntoTO.getCodBodega()+obtenerFechaCod()+corNuevo;

            addNewItem(document, "RECEPCION Nro. "+ Globales.numeroRecepcion, Element.ALIGN_CENTER, titleFont);
            addLineSpace(document);

            Font orderNumberFont = new Font(fontName, fontSize, Font.NORMAL, colorAccent);
            Font orderNumberValueFont = new Font(fontName, fontSize, Font.NORMAL, colorAccent);

            addNewItemWithLeftAndRight(document, "", "Chofer:", datosFont, datosFont);

            addNewItemWithLeftAndRight(document, "", "Patente: " + Globales.patente, datosFont, datosFont);


            addLineSpace(document);
            addLineSpace(document);

            addLineSeparator(document);

            float[] columnWidths = {4f, 5f, 4f, 2f, 2.5f, 2f, 1.5f, 2f};
            PdfPTable table = new PdfPTable(columnWidths);

            table.setWidthPercentage(100f);

            //insert column headings
            insertCell(table, "Nro OT", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Destino", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Fecha", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "F. Pago", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Ser. Ge", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "PCS", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Kilos", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Valor Flete", Element.ALIGN_CENTER, 1, bfBold12);
            table.setHeaderRows(1);

            int bultos = 0;
            double kilos = 0;
            int valFlete = 0;
            double metros = 0;

            for (int i = 0; i < Globales.hojarutaEscaneado.size(); i++) {
                insertCell(table, Globales.hojarutaEscaneado.get(i).getOt(), Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, Globales.hojarutaEscaneado.get(i).getDestino(), Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, Globales.hojarutaEscaneado.get(i).getFecha(), Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, Globales.hojarutaEscaneado.get(i).getFormaPago(), Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, Globales.hojarutaEscaneado.get(i).getServicioGeneral(), Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, Globales.hojarutaEscaneado.get(i).getPiezas(), Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, Globales.hojarutaEscaneado.get(i).getKilos(), Element.ALIGN_CENTER, 1, bf12);
                insertCell(table, Globales.hojarutaEscaneado.get(i).getValorFlete(), Element.ALIGN_CENTER, 1, bf12);

                bultos = bultos + Integer.parseInt(Globales.hojarutaEscaneado.get(i).getPiezas());
                kilos = kilos + Double.parseDouble(Globales.hojarutaEscaneado.get(i).getKilos());
                valFlete = valFlete + Integer.parseInt(Globales.hojarutaEscaneado.get(i).getValorFlete());
                metros = metros + Double.parseDouble(Globales.hojarutaEscaneado.get(i).getMetros());
            }

            Globales.sumaKilos = kilos+"";
            Globales.sumaPrecio = valFlete+"";
            Globales.sumaPiezas = bultos+"";
            Globales.sumaMetrosCubicos = metros+"";

            insertCell(table, "TOTALES", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, "", Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, "", Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, "", Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, bultos + "", Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, kilos + "", Element.ALIGN_CENTER, 1, bf12);
            insertCell(table, valFlete + "", Element.ALIGN_CENTER, 1, bf12);

            table.setHorizontalAlignment(Element.ALIGN_CENTER);


            document.add(table);

            float[] columnWidths3 = {5f, 5f, 5f};
            PdfPTable tableProblem = new PdfPTable(columnWidths3);

            tableProblem.setWidthPercentage(100f);


            if(Globales.hojaruta.size()>0){
                document.add(Chunk.NEWLINE);

                addNewItem(document, "BULTOS CON PROBLEMAS", Element.ALIGN_CENTER, titleFont);
                addLineSeparator(document);

                insertCell(tableProblem, "Nro OT", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(tableProblem, "Codigo de Barra", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(tableProblem, "Problema", Element.ALIGN_CENTER, 1, bfBold12);
                tableProblem.setHeaderRows(1);

                for (int i = 0; i < Globales.hojaruta.size(); i++) {
                    insertCell(tableProblem, Globales.hojaruta.get(i).getOt(), Element.ALIGN_CENTER, 1, bf12);
                    insertCell(tableProblem, Globales.hojaruta.get(i).getCodBarra(), Element.ALIGN_CENTER, 1, bf12);
                    insertCell(tableProblem, "Extravío Parcial", Element.ALIGN_CENTER, 1, bf12);
                }

                document.add(tableProblem);
            }

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            addNewItem(document, "CERTIFICO HABER ESTADO PERSONALMENTE DURANTE LA ENTREGA DE LA CARGA Y HABER RECEPCIONADO CONFORME TODAS LAS MERCADERIAS INDICADAS EN CADA UNA DE MIS ORDENES DE TRANSPORTE CONTENIDA EN EL PRESENTE MANIFIESTO", Element.ALIGN_CENTER, footerFont);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            addNewItemWithLeftAndRight(document, " __________________", "___________________ ", datosFont, datosFont);
            addNewItemWithLeftAndRight(document, "FIRMA DEL RECEPTOR", "FIRMA DEL CONDUCTOR", datosFont, datosFont);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            addNewItemWithLeftAndRight(document, "", "RECEPCIONISTA COPIA N 1", footerFont, footerFont);

            document.newPage();

            addNewItemWithLeftAndRight(document,  fecha, "Empresas Pullman", datosFont, datosFont);

            addLineSpace(document);
            addLineSpace(document);

            addLineSeparator(document);

            addNewItem(document, "RECEPCION Nro. " + Globales.numeroRecepcion, Element.ALIGN_CENTER, titleFont);
            addLineSpace(document);

            addNewItemWithLeftAndRight(document, "", "Chofer:", datosFont, datosFont);
            addNewItemWithLeftAndRight(document, "", "Patente: " + Globales.patente, datosFont, datosFont);

            addLineSpace(document);
            addLineSpace(document);
            addLineSeparator(document);

            document.add(table);

            if(Globales.hojaruta.size()>=1){
                document.add(Chunk.NEWLINE);

                addNewItem(document, "BULTOS CON PROBLEMAS", Element.ALIGN_CENTER, titleFont);
                addLineSeparator(document);

                document.add(tableProblem);
            }

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            addNewItem(document, "CERTIFICO HABER ESTADO PERSONALMENTE DURANTE LA ENTREGA DE LA CARGA Y HABER RECEPCIONADO CONFORME TODAS LAS MERCADERIAS INDICADAS EN CADA UNA DE MIS ORDENES DE TRANSPORTE CONTENIDA EN EL PRESENTE MANIFIESTO", Element.ALIGN_CENTER, footerFont);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            addNewItemWithLeftAndRight(document, " __________________", "___________________ ", datosFont, datosFont);
            addNewItemWithLeftAndRight(document, "FIRMA DEL RECEPTOR", "FIRMA DEL CONDUCTOR", datosFont, datosFont);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            addNewItemWithLeftAndRight(document, "", "CONDUCTOR COPIA N 2", footerFont, footerFont);

            document.close();

            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(MainImpresion.this, "Success", Toast.LENGTH_SHORT).show();
                }
            });

            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            printPDF();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public String obtenerFecha(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        return fecha;
    }

    public String obtenerFechaCod(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        return fecha;
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }


    private void printPDF() {

        PrintManager printManager = (PrintManager)getSystemService(Context.PRINT_SERVICE);
        try{
            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(MainImpresion.this, Common.getAppPath(MainImpresion.this)+ "text_pdf.pdf");
            printManager.print("Document", printDocumentAdapter, new PrintAttributes.Builder().build());
        }catch(Exception ex){
            Log.e("EDMTDev", ex.getMessage());
        }

    }

    private void addNewItemWithLeftAndRight(Document document, String textLeft, String textRight, Font textLeftFont, Font textRightFont) throws DocumentException {

        Chunk chunkTextLeft = new Chunk(textLeft, textLeftFont);
        Chunk chunkTextRight= new Chunk(textRight, textRightFont);
        Paragraph p = new Paragraph(chunkTextRight);
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(chunkTextLeft);
        document.add(p);

    }

    private void addLineSeparator(Document document) throws DocumentException {

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,0,68));
        addLineSpace(document);
        document.add(new Chunk(lineSeparator));
        addLineSpace(document);
    }

    private void addLineSpace(Document document) throws DocumentException {

        document.add(new Paragraph(""));

    }

    private void addNewItem(Document document, String text, int align, Font font) throws DocumentException {

        Chunk chunk = new Chunk(text, font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(align);
        document.add(paragraph);

    }

    public class GenerarArchivo extends AsyncTask<Void, Void, String> {
        ProgressDialog MensajeProgreso;
        String usuario = "";
        boolean isInternet = false;
        boolean respuesta = false;
        WebServices ws;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            MensajeProgreso = new ProgressDialog(activity);
            MensajeProgreso.setCancelable(false);
            MensajeProgreso.setIndeterminate(true);
            MensajeProgreso.setMessage("Generando Archivo...");
            MensajeProgreso.show();

        }

        @Override
        protected String doInBackground(Void... voids) {
            String respStr = "";
            Utilidades util = new Utilidades();


            try {
                if (util.hasNetworkAccess(activity.getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    isInternet = true;

                    createPDFFile(Common.getAppPath(MainImpresion.this)+"text_pdf.pdf");

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
                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Error: Problemas al generar el archivo.")
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

    public class Finalizar extends AsyncTask<Void, Void, String> {
        ProgressDialog MensajeProgreso;
        String usuario = "";
        boolean isInternet = false;
        boolean respuesta = false;
        WebServices ws;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            MensajeProgreso = new ProgressDialog(activity);
            MensajeProgreso.setCancelable(false);
            MensajeProgreso.setIndeterminate(true);
            MensajeProgreso.setMessage("Finalizando recepción...");
            MensajeProgreso.show();

        }

        @Override
        protected String doInBackground(Void... voids) {
            String respStr = "";
            Utilidades util = new Utilidades();


            try {
                if (util.hasNetworkAccess(activity.getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    isInternet = true;

                    for (int i = 0; i>Globales.cantidad.size(); i++){
                        ws.RegistarNodNubOtDocumento(Globales.cantidad.get(i).getOt());
                    }

                    for (int f = 0; f>Globales.hojarutaEscaneado.size(); f++) {

                        ws.RegistrarRecepcionNomina(Globales.hojarutaEscaneado.get(f).getPatente(), Globales.rut, Globales.puntoTO.getCodOficina());
                    }

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
                    final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Error: Problemas al finalizar la recepción.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                }
                            });
                    alert = builder.create();
                    alert.show();
                }
                else{
                    Globales.rut="";
                    Globales.imei = "";
                    Globales.ip = "";
                    Globales.mac = "00-C-29-DB-C8-96";
                    Globales.hojaruta.clear();
                    Globales.hojarutaEscaneado.clear();
                    Globales.impresora = "";
                    Globales.manifiesto.clear();
                    Globales.cobertura.clear();
                    Globales.ingreso = 0;
                    Intent in = new Intent(MainImpresion.this, MainActivity.class);
                    startActivity(in);
                    activity.finish();
                }
            } else {
                Toast.makeText(activity.getApplicationContext(),
                        "Sin Conexión. No se pudo verificar.", Toast.LENGTH_LONG).show();
            }


        }

    }

}
