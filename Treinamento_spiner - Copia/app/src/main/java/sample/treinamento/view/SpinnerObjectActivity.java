package sample.treinamento.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import sample.treinamento.R;
import sample.treinamento.adapter.PessoaAdapter;
import sample.treinamento.model.Pessoa;

public class SpinnerObjectActivity extends AppCompatActivity {

    private static final Pessoa[] pessoas = {new Pessoa("Rafa", 30), new Pessoa("Pablo", 17), new Pessoa("JP", 40)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final TextView textView = (TextView) findViewById(R.id.textView);

        PessoaAdapter adapter = new PessoaAdapter(this, pessoas);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                textView.setText(getString(R.string.nome_idade, pessoas[pos].getNome(), pessoas[pos].getIdade()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
