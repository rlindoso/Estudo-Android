package com.example.rlindoso.gettingstartedwithmaterialdesign.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rlindoso.gettingstartedwithmaterialdesign.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome;
    private TextView txtOla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText

        edtNome = (EditText) findViewById(R.id.edtNome);
        txtOla = (TextView) findViewById(R.id.txtOla);
        Button btnOla = (Button) findViewById(R.id.btnOla);

        btnOla.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnOla) {
            String nome = edtNome.getText().toString();
            txtOla.setText("Olá " + nome);
        }
    }
}
