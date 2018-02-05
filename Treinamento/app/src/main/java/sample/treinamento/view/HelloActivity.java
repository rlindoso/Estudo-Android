package sample.treinamento.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sample.treinamento.R;
import sample.treinamento.model.Pessoa;
import sample.treinamento.utils.Constants;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);

        if (getIntent().hasExtra(Constants.PESSOA)) {
            Pessoa pessoa = (Pessoa) getIntent().getSerializableExtra(Constants.PESSOA);
            txtTitle.setText(pessoa.getNome());
        }
    }
}
