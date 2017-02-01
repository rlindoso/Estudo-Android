package com.example.rlindoso.rlindosotreinamento.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.rlindoso.rlindosotreinamento.R;

/**
 * Created by rlindoso on 30/01/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        setupToolbar();
    }

    private void setupToolbar() {
        View toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar((Toolbar) toolbar);
        }
    }

    private void initContentView() {
        int layoutId = getLayoutResId();
        if (layoutId > 0) {
            setContentView(layoutId);
        }
    }

    protected void showMessage(String message) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    protected abstract int getLayoutResId();
}
