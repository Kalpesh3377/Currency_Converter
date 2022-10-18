package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Spinner sp1,sp2;
    EditText ed;
    Button b;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sp1 = findViewById(R.id.spfrom);
        sp2 = findViewById(R.id.spto);
        ed = findViewById(R.id.txtamount);
        b = findViewById(R.id.b1);
        txt = findViewById(R.id.txt);

        String[] from = {"USD", "ARS",
                "AUD",
                "BCH",
                "BGN",
                "BNB",
                "BRL",
                "BTC",
                "CAD",
                "CHF",
                "CNY",
                "CZK",
                "DKK",
                "DOGE",
                "DZD",
                "ETH",
                "EUR",
                "GBP",
                "HKD",
                "HRK",
                "HUF",
                "IDR",
                "ILS",
                "INR",
                "ISK",
                "JPY",
                "KRW",
                "LTC",
                "MAD",
                "MXN",
                "MYR",
                "NOK",
                "NZD",
                "PHP",
                "PLN",
                "RON",
                "RUB",
                "SEK",
                "SGD",
                "THB",
                "TRY",
                "TWD",
                "XRP",
                "ZAR"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(ad);
        String[] to = {"USD", "ARS",
                "AUD",
                "BCH",
                "BGN",
                "BNB",
                "BRL",
                "BTC",
                "CAD",
                "CHF",
                "CNY",
                "CZK",
                "DKK",
                "DOGE",
                "DZD",
                "ETH",
                "EUR",
                "GBP",
                "HKD",
                "HRK",
                "HUF",
                "IDR",
                "ILS",
                "INR",
                "ISK",
                "JPY",
                "KRW",
                "LTC",
                "MAD",
                "MXN",
                "MYR",
                "NOK",
                "NZD",
                "PHP",
                "PLN",
                "RON",
                "RUB",
                "SEK",
                "SGD",
                "THB",
                "TRY",
                "TWD",
                "XRP",
                "ZAR"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        sp2.setAdapter(ad1);
        b.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    String base = sp1.getSelectedItem().toString();
                    String target = sp2.getSelectedItem().toString();
                    Double t;
                    Double amount = Double.parseDouble(ed.getText().toString());
                    try {
                        URL u = new URL("https://exchange-rates.abstractapi.com/v1/live?api_key=869471b6de6c47ef85cb4513c286536b&base=" + base + "&target=" + target);
                        HttpURLConnection h = (HttpURLConnection) u.openConnection();
                        InputStream f = h.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(f));
                        String line = br.readLine();
                        String[] arr = line.split(",");
                        String[] arr1 = arr[2].split(":");
                        String[] arr3 = arr1[2].split("\\}");
                        Double rate = Double.parseDouble(arr3[0]);
                        if (base.equals(target)) {
                            txt.setText("Please Peak the Currency to convert");
                        } else {
                            t = amount * rate;
                            txt.setText(t.toString());
                        }

                    } catch (Exception e) {

                    }
                    
                }
            });

    }
}