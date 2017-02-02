package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.adapter.AutorAdapter;
import com.example.rlindoso.rlindosotreinamento.model.Autor;
import com.example.rlindoso.rlindosotreinamento.repository.AutorRepository;

import java.util.List;

public class BibliotecaAutorActivity extends BaseActivity {
    TextView edtPesquisa;
    AutorRepository repo;
    AutorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repo = new AutorRepository(this);

        final ListView listView = (ListView) findViewById(R.id.lista);
        edtPesquisa = (TextView) findViewById(R.id.edtPesquisa);

        adapter = new AutorAdapter(this);
        listView.setAdapter(adapter);
        refreshList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Livro livro = adapter.getItem(position);

                //Mostrar "showMessage" com nome e data de nascimento
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCad);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BibliotecaAutorActivity.this, BibliotecaAutorEditAddActivity.class));
            }
        });

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuAutor) {
            startActivity(new Intent(BibliotecaAutorActivity.this, BibliotecaAutorActivity.class));
        }

        return true;
    }

    private void refreshList() {
        List<Autor> autor = repo.getAll();
        adapter.setItems(autor);
    }



    @Override
    protected int getLayoutResId() {
        return R.layout.activity_biblioteca;
    }
}
