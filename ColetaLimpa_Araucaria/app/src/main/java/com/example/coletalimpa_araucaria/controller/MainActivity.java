package com.example.coletalimpa_araucaria.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coletalimpa_araucaria.R;
import com.example.coletalimpa_araucaria.model.CadastroActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button cadastroButton = findViewById(R.id.cadastroButton);
        Button buttonPontosColeta = findViewById(R.id.button_coleta);
        Button buttonDiaTurnoColeta = findViewById(R.id.button_turno);

        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCadastroActivity();
            }
        });

        buttonPontosColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaPontosColeta();
            }
        });

        buttonDiaTurnoColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaDiaTurnoColeta();
            }
        });

    }

    void goToCadastroActivity() {
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    void abrirTelaPontosColeta() {
        Intent intent = new Intent(MainActivity.this, PontosColetaActivity.class);
        startActivity(intent);
    }

    void abrirTelaDiaTurnoColeta() {
        Intent intent = new Intent(MainActivity.this, DiaTurnoColetaActivity.class);
        startActivity(intent);
    }
}
