package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.model.Autor;
import com.example.rlindoso.rlindosotreinamento.utils.DateUtils;

/**
 * Created by rlindoso on 02/02/2017.
 */

public class BibliotecaAutorEditAddActivity extends BaseActivity {
    Autor autor;
    Autor autorCopiar;
    TextView edtNome;
    TextView edtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edtNome = (TextView) findViewById(R.id.txtNome);
        edtData = (TextView) findViewById(R.id.txtData);

        if (getIntent().hasExtra(Autor.EXTRA)) {
            autor = (Autor) getIntent().getSerializableExtra(Autor.EXTRA);
            edtNome.setText(autor.getNome());
            edtData.setText(DateUtils.dateToStr(autor.getDataNascimento()));
        }

        if (getIntent().hasExtra(Autor.EXTRA_COPY)) {
            autorCopiar = (Autor) getIntent().getSerializableExtra(Autor.EXTRA_COPY);
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
            String nome = edtNome.getText().toString();
            String dataNasc = edtData.getText().toString();

            if (nome.equals("")) {
                edtNome.setError("Informe um valor válido");
            } else {

                autor.setNome(nome);
                if (!nome.equals("")) {
                    autor.setDataNascimento(DateUtils.strToDate(dataNasc));
                }

                Intent intent = new Intent();
                intent.putExtra(Autor.EXTRA, autor);

                setResult(RESULT_OK, intent);

                finish();
            }
        } else {
            if (autorCopiar != null) {
                edtNome.setText(autorCopiar.getNome());
                edtData.setText(DateUtils.dateToStr(autorCopiar.getDataNascimento()));
            }
        }

        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_biblioteca_autor;
    }
}
