package sample.treinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sample.treinamento.R;
import sample.treinamento.model.Pessoa;
import sample.treinamento.utils.Constants;

/**
 * Created by palves on 25/01/2017.
 */

public class HelloMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_main);

        Button btnOla = (Button) findViewById(R.id.btnOla);
        btnOla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtNome = (EditText) findViewById(R.id.edtNome);
                String nome = edtNome.getText().toString();

                Pessoa pessoa = new Pessoa(nome);

                Intent intent = new Intent(HelloMainActivity.this, HelloActivity.class);
                intent.putExtra(Constants.PESSOA, pessoa);

                startActivity(intent);
            }
        });
    }
}
