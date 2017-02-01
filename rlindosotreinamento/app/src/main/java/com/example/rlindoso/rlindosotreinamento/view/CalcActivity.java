package com.example.rlindoso.rlindosotreinamento.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.model.combustiveis;
import com.example.rlindoso.rlindosotreinamento.utils.Constants;


public class CalcActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView txtResultado = (TextView) findViewById(R.id.txtResultado);
        ImageView image = (ImageView) findViewById(R.id.image);

        if (getIntent().hasExtra(Constants.COMBUSTIVEIS)) {
            combustiveis Combustiveis = (combustiveis) getIntent().getSerializableExtra(Constants.COMBUSTIVEIS);

            double ValComp = Combustiveis.getValAlc() / Combustiveis.getValGas();

            if (ValComp < 0.7) {
                txtResultado.setText("Melhor usar Alcool");
                image.setImageResource(R.drawable.ic_bomba_alcool);
            } else {
                txtResultado.setText("Melhor usar Gasolina");
                image.setImageResource(R.drawable.ic_bomba_gasolina);
            }

        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_calc;
    }
}
