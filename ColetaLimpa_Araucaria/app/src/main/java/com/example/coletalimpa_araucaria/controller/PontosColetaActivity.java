package com.example.coletalimpa_araucaria.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coletalimpa_araucaria.R;

public class PontosColetaActivity extends AppCompatActivity{

    private Spinner spinnerBairro;
    private Button buttonShowPontos;
    private DBHelper dbHelper;
    private TextView textViewPontos;
    private TextView textViewEndereco;
    private TextView textViewHorarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontos_coleta);
        EdgeToEdge.enable(this);
        spinnerBairro = findViewById(R.id.spinner_bairro);
        textViewPontos = findViewById(R.id.textView_pontos);
        textViewEndereco = findViewById(R.id.textView_endereco);
        textViewHorarios = findViewById(R.id.textView_horarios);
        buttonShowPontos = findViewById(R.id.button_show_pontos);
        dbHelper = new DBHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pontosColeta), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonShowPontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPontos();
                showEndereco();
                showHorarios();
            }
        });
    }
    private void showPontos() {
        String bairro = spinnerBairro.getSelectedItem().toString();

        Cursor cursor = dbHelper.getFarmacia(bairro);

        if (cursor.moveToFirst()) {
            String farmacia = cursor.getString(cursor.getColumnIndexOrThrow("farmacia"));
            textViewPontos.setText(farmacia);
        } else {
            textViewPontos.setText("Sem farmácia");
        }
        cursor.close();
    }

    private void showEndereco() {
        String bairro = spinnerBairro.getSelectedItem().toString();

        Cursor cursor = dbHelper.getEndereco(bairro);

        if (cursor.moveToFirst()) {
            String endereco = cursor.getString(cursor.getColumnIndexOrThrow("endereco"));
            textViewEndereco.setText(endereco);
        } else {
            textViewEndereco.setText("Sem endereço");
        }
        cursor.close();
    }

    private void showHorarios() {
        String bairro = spinnerBairro.getSelectedItem().toString();

        Cursor cursor = dbHelper.getHorario(bairro);

        if (cursor.moveToFirst()) {
            String horario = cursor.getString(cursor.getColumnIndexOrThrow("horario"));
            textViewHorarios.setText(horario);
        } else {
            textViewHorarios.setText("Sem informações sobre horário");
        }
        cursor.close();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            // Navigate back to MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

