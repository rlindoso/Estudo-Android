package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;

public class CadBasicoEditAddActivity extends BaseActivity {

    AppTreinamento appTreinamento;
    AppTreinamento appCopiar;
    TextView edtNome;
    TextView edtAula;
    TextView edtObjetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edtNome = (TextView) findViewById(R.id.txtNome);
        edtAula = (TextView) findViewById(R.id.txtAula);
        edtObjetivo = (TextView) findViewById(R.id.txtObjetivo);

        if (getIntent().hasExtra(AppTreinamento.EXTRA)) {
            appTreinamento = (AppTreinamento) getIntent().getSerializableExtra(AppTreinamento.EXTRA);
            edtNome.setText(appTreinamento.getNome());
            edtAula.setText(String.valueOf(appTreinamento.getAula()));
            edtObjetivo.setText(appTreinamento.getObjetivo());
        }

        if (getIntent().hasExtra(AppTreinamento.EXTRA_COPY)) {
            appCopiar = (AppTreinamento) getIntent().getSerializableExtra(AppTreinamento.EXTRA_COPY);
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
            String aula = edtAula.getText().toString();

            if (nome.equals("")) {
                edtNome.setError("Informe um valor válido");
            } else if ((aula.equals("")) || ((Integer.parseInt(aula) <= 0))) {
                edtAula.setError("Informe um valor válido");
            } else {

                String objetivo = edtObjetivo.getText().toString();
                appTreinamento.setNome(nome);
                appTreinamento.setAula(Integer.parseInt(aula));
                appTreinamento.setObjetivo(objetivo);

                Intent intent = new Intent();
                intent.putExtra(AppTreinamento.EXTRA, appTreinamento);

                setResult(RESULT_OK, intent);

                finish();
            }
        } else {
            if (appCopiar != null) {
                edtNome.setText(appCopiar.getNome());
                edtAula.setText(String.valueOf(appCopiar.getAula()));
                edtObjetivo.setText(appCopiar.getObjetivo());
            }
        }

        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cad_basico_edit_add;
    }
}
