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

public class DiaTurnoColetaActivity extends AppCompatActivity{
    private Spinner spinnerDia;
    private Spinner spinnerBairro;
    private Button buttonShowTurno;
    private DBHelper dbHelper;
    private TextView textViewColeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_turno);
        EdgeToEdge.enable(this);
        spinnerDia = findViewById(R.id.spinner_dia);
        spinnerBairro = findViewById(R.id.spinner_bairro);
        textViewColeta = findViewById(R.id.textview_coleta);
        buttonShowTurno = findViewById(R.id.button_show_coleta);
        dbHelper = new DBHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.turnoColeta), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        insereDados();

        buttonShowTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColeta();
            }
        });
    }
    private void showColeta() {
        String bairro = spinnerBairro.getSelectedItem().toString();

        Cursor cursor = dbHelper.getColeta(bairro);

        if (cursor.moveToFirst()) {
            String coleta = cursor.getString(cursor.getColumnIndexOrThrow("coleta"));
            textViewColeta.setText(coleta);
        } else {
            textViewColeta.setText("Sem coleta");
        }
        cursor.close();
    }

    private void insereDados() {
        dbHelper.insertData("Centro", "Droga Raia", "R. Mal. Deodoro, 393", "Segunda a sábado, manhã e tarde 07h as 20h", "Manhã");
        dbHelper.insertData("Iguaçu", "Farmácia Nissei", "Av. Iguaçu, 2619", "Aberto 24h", "Noite");
        dbHelper.insertData("Fazenda Velha", "Farmácia Nissei", "R. Pedro de Alcântara Meira, 37", "Segunda a sexta das 07h às 23h, sábado e domingo das 07h as 21h", "Tarde");
        dbHelper.insertData("Campina da Barra", "Farmácia FortPopular Vitória", "R. das Flores, 624", "Segunda a sábado, 08h às 20h", "Manhã");
        dbHelper.insertData("Portão", "Farmácia Droga Raia", "Av. Rep. Argentina, 2838", "24h", "Noite");
        dbHelper.insertData("Barigui", "Farmácia Droga Raia", "R. Prof. Pedro Viriato Parigot de Souza, 600", "Segunda a domingo, 10h às 22h", "Tarde");
        dbHelper.insertData("Porto das Laranjeiras", "Farmácia Maxifarma", "Av. Independência, 2079", "Segunda a sábado, 08h às 20h", "Tarde");
        dbHelper.insertData("Boqueirão", "Farmácia MaxiFarma", "R. Maestro Carlos Frank, 2812", "Segunda a sábado, 09h às 21h", "Noite");
        dbHelper.insertData("Santa Quitéria", "Farmácia DrogaRaia", "R. Prof. Ulisses Vieira, 1280", "Segunda a domingo, 07h às 23h", "Noite");
        dbHelper.insertData("Capela Velha", "Farmácia Unipreço", "R. Gralha-Azul, 568", "Todos os dias, 08h à 00h", "Tarde");
        dbHelper.insertData("Rebouças", "Farmácia Panvel", "Avenida Sete de Setembro, 4884", "Segunda a sexta, manhã e tarde", "Tarde");
        dbHelper.insertData("Vila Nova", "Farmácia MaxiFarma", "Av. Pres. Getúlio Vargas, 1352", "Segunda a sábado, 09h às 19h", "Noite");
        dbHelper.insertData("Estação", "Farmácia Hiperfarma", "Rua Luíz Armando Ohpis, 386", "Segunda a sexta, manhã e tarde", "Tarde");
        dbHelper.insertData("Costeira", "Farmácia Panvel", "Avenida do Batel, 1340", "Segunda a sábado, 08h às 20:30h", "Noite");
        dbHelper.insertData("Cachoeira", "Farmácia Masterfarma", "R. Maria Madalena, 85", "Segunda à sábado, 08h às 22h", "Noite");
        dbHelper.insertData("Passaúna", "Farmácia Confarma", "Av. Independência, 2210", "Segunda à sábado, 09h às 20h", "Tarde");
        dbHelper.insertData("CIC", "Farmácia Maxifarma", "R. Antônio Pastre, 354", "Segunda à sábado, 09h às 20:30h", "Noite");
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
