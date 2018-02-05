package com.example.rlindoso.alcool_gasolina.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rlindoso.alcool_gasolina.R;
import com.example.rlindoso.alcool_gasolina.model.combustiveis;
import com.example.rlindoso.alcool_gasolina.utils.Constants;
import com.example.rlindoso.alcool_gasolina.utils.NumberUtils;

public class MainActivity extends AppCompatActivity {

    EditText edtGas;
    EditText edtAlc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtGas = (EditText) findViewById(R.id.edtGas);
        edtAlc = (EditText) findViewById(R.id.edtAlc);


        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double valGas = NumberUtils.strToDlouble(edtGas.getText().toString());
                double valAlc = NumberUtils.strToDlouble(edtAlc.getText().toString());

                if (validaValores(valGas, valAlc)) {
                    combustiveis Combustiveis = new combustiveis();
                    Combustiveis.setValGas(valGas);
                    Combustiveis.setValAlc(valAlc);

                    Intent intent = new Intent(MainActivity.this, CalcActivity.class);

                    intent.putExtra(Constants.COMBUSTIVEIS, Combustiveis);

                    startActivity(intent);
                }
            }
        });
    }

    private boolean validaValores(double valGas, double valAlc) {
        if (valGas <= 0) {
            edtGas.setError("Informe um valor válido");
        }

        if (valAlc <= 0) {
            edtAlc.setError("Informe um valor válido");
        }

        return (valAlc > 0) && (valGas > 0);
    }
}
