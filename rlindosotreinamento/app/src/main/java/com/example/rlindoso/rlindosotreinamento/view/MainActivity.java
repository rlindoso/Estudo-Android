package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.adapter.AppTreinamentoAdapter;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<AppTreinamento> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lista = new ArrayList<>();
        lista.add(new AppTreinamento("", 0, ""));
        lista.add(new AppTreinamento("Gasolina ou Alcool", 1, "Abastecer com gasolina ou alcool"));
        lista.add(new AppTreinamento("Lista", 2, "Listar alguma coisa"));
        lista.add(new AppTreinamento("Cadastro Básico", 3, "Listar, detalhar, editar, excluir e add"));
        lista.add(new AppTreinamento("Biblioteca", 4, "Cadastrar Autores e Livros"));

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        AppTreinamentoAdapter adapter = new AppTreinamentoAdapter(this);
        adapter.setItems(lista);

        spinner.setAdapter(adapter);

        Button btnStartApp = (Button) findViewById(R.id.btnStartApp);

        btnStartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = spinner.getSelectedItemPosition();
                switch (lista.get(id).getAula()) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, MainAlcGasActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ListActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, CadBasicoActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, BibliotecaActivity.class));
                        break;
                    default:
                        Toast.makeText(MainActivity.this, R.string.selecione_app, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
