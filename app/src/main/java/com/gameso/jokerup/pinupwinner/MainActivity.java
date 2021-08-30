package com.gameso.jokerup.pinupwinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private final String CURRENCYRATES_URL = "https://v6.exchangerate-api.com/v6/e431d3e5ff41e267ffb40d2e/latest/";
    private Spinner convertTo;
    private Spinner convertFrom;
    private ImageView buttonConvertCurrency;
    private JSONObject currencies;
    private EditText setAmount;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertFrom = findViewById(R.id.from);
        convertTo = findViewById(R.id.to);
        buttonConvertCurrency = findViewById(R.id.buttonConvertCurrency);
        setAmount = findViewById(R.id.setAmount);


        String[] dropDownList = {"USD", "EUR", "UAH"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertTo.setAdapter(adapter);
        convertFrom.setAdapter(adapter);

    }



    public void onClickConvertCurrency(View view) {
        DownloadCurrencyRates downloadCurrencyRates = new DownloadCurrencyRates();
        try {
            //Если не пустая -  !isEmpty()  то работай с ней, если нет то пропускай
            if (!setAmount.getText().toString().isEmpty()) {
                String fromCurrency = convertFrom.getSelectedItem().toString();
                downloadCurrencyRates.execute(CURRENCYRATES_URL + fromCurrency);
                String jsonResponse = downloadCurrencyRates.get();
                currencies = new JSONObject(jsonResponse).getJSONObject("conversion_rates");
                String toCurrency = convertTo.getSelectedItem().toString();

                double resultOfConversion = currencies.getDouble(toCurrency);

                double currency = Double.parseDouble(setAmount.getText().toString());
                result = resultOfConversion * currency;
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("res", (int) result + toCurrency);
                startActivity(intent);
                finish();

                }
            } catch(ExecutionException | InterruptedException | JSONException e){
                e.printStackTrace();
        }
    }

}







