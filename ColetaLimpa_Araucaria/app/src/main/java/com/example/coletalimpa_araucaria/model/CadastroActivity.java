package com.example.coletalimpa_araucaria.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.coletalimpa_araucaria.R;
import com.example.coletalimpa_araucaria.controller.ContactAdapter;
import com.example.coletalimpa_araucaria.controller.DetailsActivity;
import com.example.coletalimpa_araucaria.model.DataModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CadastroActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactAdapter adapter = new ContactAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerView);
        DataModel.getInstance().createDataBase(getApplicationContext());


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(CadastroActivity.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                CadastroActivity.this, DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                goToDetailActivity(position);
            }
        });
        adapter.setOnItemLongClickListener(new ContactAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {

                Contact c = DataModel.getInstance().getContact(position);
                DataModel.getInstance().removeContact(position);
                adapter.notifyItemRemoved(position);
                View contextView = findViewById(android.R.id.content);
                Snackbar.make(contextView,R.string.remover_cadastro,Snackbar.LENGTH_LONG)
                                .setAction(R.string.desfazer, new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view){
                                        DataModel.getInstance().insertContact(c, position);
                                        adapter.notifyItemInserted(position);
                                    }
                                })
                                .show();
                return true;

            }
        });



    }
    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.adicionar_cadastro) {
            goToDetailActivity(-1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void goToDetailActivity(int index) {
        Intent intent = new Intent(CadastroActivity.this, DetailsActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }


}
