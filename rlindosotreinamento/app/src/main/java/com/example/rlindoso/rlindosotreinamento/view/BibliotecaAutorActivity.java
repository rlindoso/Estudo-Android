package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.ContextMenu;
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
import com.example.rlindoso.rlindosotreinamento.utils.Constants;

import java.util.List;

public class BibliotecaAutorActivity extends BaseActivity {
    TextView edtPesquisa;
    AutorRepository repo;
    AutorAdapter adapter;
    Autor copiarAutor;

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
                //Autor autor = adapter.getItem(position);

                //Mostrar "showMessage" com nome e data de nascimento
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCad);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Autor autor = new Autor(-1);
                openEditActivity(autor);
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
        List<Autor> autores = repo.getAll();
        adapter.setItems(autores);
    }

    private void salvar(Autor autor) {

        repo.save(autor);
        edtPesquisa.setText("");

        refreshList();
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Autor autor = adapter.getItem(info.position);

        switch (item.getItemId()) {
            case R.id.mnuEditar:
                openEditActivity(autor);
                break;
            case R.id.mnuCopiar:
                copiarAutor = adapter.getItem(info.position);
                break;
            case R.id.mnuExcluir:
                if (autor != null) {
                    repo.delete(autor.getId());
                    refreshList();
                }
                break;
            default:

        }

        return super.onContextItemSelected(item);
    }

    private void openEditActivity(Autor autor) {
        Intent intent = new Intent(BibliotecaAutorActivity.this, BibliotecaAutorEditAddActivity.class);
        intent.putExtra(Autor.EXTRA, autor);
        intent.putExtra(Autor.EXTRA_COPY, copiarAutor);

        startActivityForResult(intent, Constants.REQUEST_CAD_AUTOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_CAD_AUTOR) {
                Autor autor = (Autor) data.getSerializableExtra(Autor.EXTRA);
                salvar(autor);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_biblioteca;
    }
}
