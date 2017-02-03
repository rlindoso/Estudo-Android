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
import com.example.rlindoso.rlindosotreinamento.model.Autor;
import com.example.rlindoso.rlindosotreinamento.utils.DateUtils;

import java.util.List;

/**
 * Created by rlindoso on 02/02/2017.
 */
public class AutorAdapter extends ArrayAdapter<Autor> {
    private List<Autor> autores;

    public AutorAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    public int getCount() {
        return autores != null ? autores.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public Autor getItem(int position) {
        return autores.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Autor autor = getItem(position);
        TextView txtNome = (TextView) convertView.findViewById(android.R.id.text1);

        if (autor != null) {
            txtNome.setText(autor.getNome());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_app_treinamento, parent, false);
        }

        Autor autor = getItem(position);

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtDescData = (TextView) convertView.findViewById(R.id.txtAula);
        TextView txtData = (TextView) convertView.findViewById(R.id.txtObjetivo);

        if (autor != null) {
            txtNome.setText(autor.getNome());
            txtDescData.setText("Data de Nascimento");
            txtData.setText(autor.getDataNascimento());
        }

        return convertView;
    }

    public void setItems(List<Autor> items) {
        this.autores = items;
        notifyDataSetChanged();
    }

}
