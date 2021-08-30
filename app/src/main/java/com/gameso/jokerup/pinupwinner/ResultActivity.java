package com.gameso.jokerup.pinupwinner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewResultCurrency;
    private Button buttonBackToMainMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String res = intent.getStringExtra("res");
        textViewResultCurrency = findViewById(R.id.textViewResultCurrency);
        buttonBackToMainMenu = findViewById(R.id.buttonBackToMenu);



        textViewResultCurrency.setText(res);

        buttonBackToMainMenu.setOnClickListener(view -> {
            Intent menuIntent = new Intent(this, MainActivity.class);
            startActivity(menuIntent);
            finish();
        });
    }
}