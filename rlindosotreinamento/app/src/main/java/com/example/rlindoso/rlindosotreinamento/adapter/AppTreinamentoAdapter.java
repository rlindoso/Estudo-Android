package com.example.rlindoso.rlindosotreinamento.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;

import java.util.List;

/**
 * Created by rlindoso on 26/01/2017.
 */

public class AppTreinamentoAdapter extends ArrayAdapter<AppTreinamento> {
    private List<AppTreinamento> appTreinamentos;

    public AppTreinamentoAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public int getCount() {
        return appTreinamentos != null ? appTreinamentos.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public AppTreinamento getItem(int position) {
        return appTreinamentos.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        AppTreinamento appTreinamento = getItem(position);
        TextView txtNome = (TextView) convertView.findViewById(android.R.id.text1);

        if (appTreinamento != null) {
            txtNome.setText(appTreinamento.getNome());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_app_treinamento, parent, false);
        }

        AppTreinamento appTreinamento = getItem(position);

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtAula = (TextView) convertView.findViewById(R.id.txtAula);
        TextView txtObjetivo = (TextView) convertView.findViewById(R.id.txtObjetivo);

        if (appTreinamento != null) {
            txtNome.setText(appTreinamento.getNome());

            txtObjetivo.setText(String.valueOf(appTreinamento.getObjetivo()));

            if (appTreinamento.getAula() > 0) {
                txtAula.setText(getContext().getString(R.string.numero_aula, appTreinamento.getAula()));
            } else {
                txtAula.setText("");
            }
        }

        return convertView;
    }

    public void setItems(List<AppTreinamento> items) {
        this.appTreinamentos = items;
        notifyDataSetChanged();
    }
}
