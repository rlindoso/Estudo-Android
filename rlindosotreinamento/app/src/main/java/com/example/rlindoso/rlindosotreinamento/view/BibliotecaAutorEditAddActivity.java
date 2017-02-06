package com.example.rlindoso.rlindosotreinamento.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

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
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edtNome = (TextView) findViewById(R.id.txtNome);
        edtData = (TextView) findViewById(R.id.txtData);

        if (getIntent().hasExtra(Autor.EXTRA)) {
            autor = (Autor) getIntent().getSerializableExtra(Autor.EXTRA);
            edtNome.setText(autor.getNome());
            edtData.setText(DateUtils.dateToStr(autor.getDataNascimento(), DateUtils.DATE_FORMAT_UI));
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
                if (!dataNasc.equals("")) {
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
                edtData.setText(DateUtils.dateToStr(autorCopiar.getDataNascimento(), DateUtils.DATE_FORMAT_UI));
            }
        }

        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_biblioteca_autor;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String data = String.valueOf(dayOfMonth) + " /"
                    + String.valueOf(monthOfYear + 1) + " /" + String.valueOf(year);
            Toast.makeText(BibliotecaAutorEditAddActivity.this,
                    "DATA = " + data, Toast.LENGTH_SHORT)
                    .show();
        }
    };

}
