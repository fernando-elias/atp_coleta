package com.example.coletalimpa_araucaria.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coletalimpa_araucaria.R;
import com.example.coletalimpa_araucaria.model.Contact;
import com.example.coletalimpa_araucaria.model.DataModel;

public class DetailsActivity extends AppCompatActivity {

    EditText nomeEditText;
    EditText telefoneEditText;
    int index;
    Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nomeEditText = findViewById(R.id.nomeEditText);
        telefoneEditText = findViewById(R.id.telefoneEditText);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            index = extra.getInt("index", -1);
            if (index != -1) {
                Contact c = DataModel.getInstance().getContact(index);
                nomeEditText.setText(c.getNome());
                telefoneEditText.setText(c.getTelefone());
            }
        }

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDados();
            }
        });
    }

    public void salvarDados() {
        String nome = nomeEditText.getText().toString();
        String telefone = telefoneEditText.getText().toString();

        if (nome.length() > 0 && telefone.length() > 0) {
            if (index == -1) {
                DataModel.getInstance().addContact(new Contact(nome, telefone));
            } else {
                Contact c = DataModel.getInstance().getContact(index);
                c.setNome(nome);
                c.setTelefone(telefone);
                DataModel.getInstance().updateContact(c,index);
            }
            Toast.makeText(DetailsActivity.this, "Dados salvos!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
            builder.setTitle(R.string.atencao);
            builder.setMessage(R.string.cadastro_vazio_msg_alert);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }
}
