package com.example.bodegacarga;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Util.Globales;

public class MainVerEscaneados extends AppCompatActivity {

    private Activity activity;
    private Button btnVolver;
    private TableLayout tbDatos;
    private TextView lbIPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_escaneados);
        activity = this;

        getSupportActionBar().hide();
        btnVolver = (Button) findViewById(R.id.btnVolver);
        lbIPE = (TextView) findViewById(R.id.lbIPE);

        String concat = lbIPE.getText() + Globales.ip;

        lbIPE.setText(concat);

        if (Globales.escaneo == 1) {
            TableLayout table = (TableLayout) findViewById(R.id.tbDatos);

            TableRow tableRow = new TableRow(MainVerEscaneados.this);
            TextView tvidH = new TextView(MainVerEscaneados.this);
            TextView tvidH2 = new TextView(MainVerEscaneados.this);
            TextView tvidH3 = new TextView(MainVerEscaneados.this);
            TextView tvidH4 = new TextView(MainVerEscaneados.this);

            tvidH.setText("N° ODT");
            tvidH.setGravity(Gravity.CENTER);
            tvidH.setTextColor(Color.BLACK);
            tvidH.setTextSize(16f);

            tvidH2.setText("PIEZAS");
            tvidH2.setGravity(Gravity.CENTER);
            tvidH2.setTextColor(Color.BLACK);
            tvidH2.setTextSize(16f);

            tvidH3.setText("DESTINO");
            tvidH3.setGravity(Gravity.CENTER);
            tvidH3.setTextColor(Color.BLACK);
            tvidH3.setTextSize(16f);

            tvidH4.setText("PESO");
            tvidH4.setGravity(Gravity.CENTER);
            tvidH4.setTextColor(Color.BLACK);
            tvidH4.setTextSize(16f);

            tableRow.addView(tvidH, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.3f)));
            tableRow.addView(tvidH2, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));
            tableRow.addView(tvidH3, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.2f)));
            tableRow.addView(tvidH4, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));

            table.addView(tableRow);

            for (int f = 0; f < Globales.hojarutaEscaneado.size(); f++) {

                TableRow tableRow2 = new TableRow(MainVerEscaneados.this);

                TextView tvid = new TextView(MainVerEscaneados.this);
                TextView tvid2 = new TextView(MainVerEscaneados.this);
                TextView tvid3 = new TextView(MainVerEscaneados.this);
                TextView tvid4 = new TextView(MainVerEscaneados.this);

                tvid.setText(Globales.hojarutaEscaneado.get(f).getOt());
                tvid.setGravity(Gravity.CENTER);

                tvid2.setText(Globales.hojarutaEscaneado.get(f).getPiezas());
                tvid2.setGravity(Gravity.CENTER);

                tvid3.setText(Globales.hojarutaEscaneado.get(f).getDestino());
                tvid3.setGravity(Gravity.CENTER);

                tvid4.setText(Globales.hojarutaEscaneado.get(f).getKilos());
                tvid4.setGravity(Gravity.CENTER);

                tableRow2.addView(tvid, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.3f)));

                tableRow2.addView(tvid2, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));

                tableRow2.addView(tvid3, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.2f)));

                tableRow2.addView(tvid4, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));

                table.addView(tableRow2);


            }
        } else {
            if (Globales.escaneo == 2) {
                TableLayout table = (TableLayout) findViewById(R.id.tbDatos);

                TableRow tableRow = new TableRow(MainVerEscaneados.this);
                TextView tvidH = new TextView(MainVerEscaneados.this);
                TextView tvidH2 = new TextView(MainVerEscaneados.this);
                TextView tvidH3 = new TextView(MainVerEscaneados.this);
                TextView tvidH4 = new TextView(MainVerEscaneados.this);

                tvidH.setText("N° ODT");
                tvidH.setGravity(Gravity.CENTER);
                tvidH.setTextColor(Color.BLACK);
                tvidH.setTextSize(16f);

                tvidH2.setText("PIEZAS");
                tvidH2.setGravity(Gravity.CENTER);
                tvidH2.setTextColor(Color.BLACK);
                tvidH2.setTextSize(16f);

                tvidH3.setText("DESTINO");
                tvidH3.setGravity(Gravity.CENTER);
                tvidH3.setTextColor(Color.BLACK);
                tvidH3.setTextSize(16f);

                tvidH4.setText("PESO");
                tvidH4.setGravity(Gravity.CENTER);
                tvidH4.setTextColor(Color.BLACK);
                tvidH4.setTextSize(16f);

                tableRow.addView(tvidH, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.3f)));
                tableRow.addView(tvidH2, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));
                tableRow.addView(tvidH3, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.2f)));
                tableRow.addView(tvidH4, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));

                table.addView(tableRow);

                for (int f = 0; f < Globales.hojaruta.size(); f++) {

                    TableRow tableRow2 = new TableRow(MainVerEscaneados.this);

                    TextView tvid = new TextView(MainVerEscaneados.this);
                    TextView tvid2 = new TextView(MainVerEscaneados.this);
                    TextView tvid3 = new TextView(MainVerEscaneados.this);
                    TextView tvid4 = new TextView(MainVerEscaneados.this);

                    tvid.setText(Globales.hojaruta.get(f).getOt());
                    tvid.setGravity(Gravity.CENTER);

                    tvid2.setText(Globales.hojaruta.get(f).getPiezas());
                    tvid2.setGravity(Gravity.CENTER);

                    tvid3.setText(Globales.hojaruta.get(f).getDestino());
                    tvid3.setGravity(Gravity.CENTER);

                    tvid4.setText(Globales.hojaruta.get(f).getKilos());
                    tvid4.setGravity(Gravity.CENTER);

                    tableRow2.addView(tvid, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.3f)));

                    tableRow2.addView(tvid2, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));

                    tableRow2.addView(tvid3, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.2f)));

                    tableRow2.addView(tvid4, (new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));

                    table.addView(tableRow2);
                }
            }

        }
    }


    public void onClick(View v) {
        if(v == btnVolver)
        {
            Globales.escaneo = 0;
            Intent in = new Intent(MainVerEscaneados.this, MainRecBulRecepcion.class);
            startActivity(in);
            activity.finish();
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
