package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.adapter.AutorAdapter;
import com.example.rlindoso.rlindosotreinamento.model.Autor;
import com.example.rlindoso.rlindosotreinamento.model.Livro;
import com.example.rlindoso.rlindosotreinamento.repository.AutorRepository;

import java.util.List;


public class BibliotecaLivroActivity extends BaseActivity {

    AutorRepository repoAutor;
    private TextView edtTitulo;
    private TextView edtSinopse;
    private Livro livro;
    private Livro livroCopiar;
    private AutorAdapter adapter;
    private Spinner spinner;
    private List<Autor> autores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repoAutor = new AutorRepository(this);

        autores = repoAutor.getAll();

        spinner = (Spinner) findViewById(R.id.spnAutor);
        adapter = new AutorAdapter(this);
        adapter.setItems(autores);

        spinner.setAdapter(adapter);

        edtTitulo = (TextView) findViewById(R.id.txtTitulo);
        edtSinopse = (TextView) findViewById(R.id.txtSinopse);

        if (getIntent().hasExtra(Livro.EXTRA)) {
            livro = (Livro) getIntent().getSerializableExtra(Livro.EXTRA);
            edtTitulo.setText(livro.getTitulo());
            edtSinopse.setText(livro.getSinopse());
            if (livro.getAutor() != null) {
                setPositionSpiner(livro.getAutor());
            }
        }

        if (getIntent().hasExtra(Livro.EXTRA_COPY)) {
            livroCopiar = (Livro) getIntent().getSerializableExtra(Livro.EXTRA_COPY);
        }

    }


    protected void setPositionSpiner (Autor autor) {
        for(int i=0; (i <= adapter.getCount()-1) ; i++){

            if(adapter.getItem(i).getId() == (autor.getId())){
                spinner.setSelection(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_salvar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSalvar) {
            String titulo = edtTitulo.getText().toString();
            String sinopse = edtSinopse.getText().toString();

            if (titulo.equals("")) {
                edtTitulo.setError("Informe um valor válido");
            } else {
                int id = spinner.getSelectedItemPosition();

                livro.setTitulo(titulo);
                livro.setSinopse(sinopse);
                livro.setAutor(autores.get(id));

                Intent intent = new Intent();
                intent.putExtra(Livro.EXTRA, livro);

                setResult(RESULT_OK, intent);

                finish();
            }
        } else {
            if (livroCopiar != null) {
                edtTitulo.setText(livroCopiar.getTitulo());
                edtSinopse.setText(livroCopiar.getSinopse());
                if (livroCopiar.getAutor() != null) {
                    setPositionSpiner (livroCopiar.getAutor());
                }
            }
        }

        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_biblioteca_livro;
    }

}
