package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.model.combustiveis;
import com.example.rlindoso.rlindosotreinamento.utils.Constants;
import com.example.rlindoso.rlindosotreinamento.utils.NumberUtils;


public class MainAlcGasActivity extends BaseActivity {

    EditText edtGas;
    EditText edtAlc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

                    Intent intent = new Intent(MainAlcGasActivity.this, CalcActivity.class);

                    intent.putExtra(Constants.COMBUSTIVEIS, Combustiveis);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_alc_gas_main;
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
