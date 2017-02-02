package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import com.example.rlindoso.rlindosotreinamento.adapter.LivroAdapter;
import com.example.rlindoso.rlindosotreinamento.model.Livro;
import com.example.rlindoso.rlindosotreinamento.repository.LivroRepository;

import java.util.List;

public class BibliotecaActivity extends BaseActivity {
    TextView edtPesquisa;
    LivroRepository repo;
    LivroAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repo = new LivroRepository(this);

        final ListView listView = (ListView) findViewById(R.id.lista);
        edtPesquisa = (TextView) findViewById(R.id.edtPesquisa);

        adapter = new LivroAdapter(this);
        listView.setAdapter(adapter);
        refreshList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Livro livro = adapter.getItem(position);

                //Mostrar "showMessage" com sinopse do livro
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCad);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BibliotecaActivity.this, BibliotecaLivroActivity.class));
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
    protected int getLayoutResId() {
        return R.layout.activity_biblioteca;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_biblioteca, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuAutor) {
            startActivity(new Intent(BibliotecaActivity.this, BibliotecaAutorActivity.class));
        }

        return true;
    }

    private void refreshList() {
        List<Livro> livros = repo.getAll();
        adapter.setItems(livros);
    }
}
