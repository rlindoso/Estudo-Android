package com.example.rlindoso.rlindosotreinamento.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.databinding.ActivityAppTreinamentoDetailBinding;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;
import com.squareup.picasso.Picasso;

public class AppTreinamentoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAppTreinamentoDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_app_treinamento_detail);
        //binding..setTitle(R.string.app_name);
        AppTreinamento appTreinamento = (AppTreinamento) getIntent().getSerializableExtra(AppTreinamento.EXTRA);
        appTreinamento.setImageUrl("https://imagens.canaltech.com.br/27337.243861-Raspberry-Pi.jpg");

        binding.setMinhaVar(appTreinamento);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }
}
