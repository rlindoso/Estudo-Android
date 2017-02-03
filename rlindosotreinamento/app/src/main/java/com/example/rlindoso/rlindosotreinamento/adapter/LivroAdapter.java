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
import com.example.rlindoso.rlindosotreinamento.model.Livro;
import com.example.rlindoso.rlindosotreinamento.utils.DateUtils;

import java.util.List;

/**
 * Created by rlindoso on 02/02/2017.
 */

public class LivroAdapter extends ArrayAdapter<Livro> {
    private List<Livro> livros;

    public LivroAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    public int getCount() {
        return livros != null ? livros.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public Livro getItem(int position) {
        return livros.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_app_treinamento, parent, false);
        }

        Livro livro = getItem(position);

        TextView txtTitulo = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtNome = (TextView) convertView.findViewById(R.id.txtAula);
        TextView txtDataNasc = (TextView) convertView.findViewById(R.id.txtObjetivo);

        if (livro != null) {
            txtTitulo.setText(livro.getTitulo());

            txtNome.setText(livro.getAutor().getNome());

            txtDataNasc.setText(livro.getAutor().getDataNascimento());
        }

        return convertView;
    }

    public void setItems(List<Livro> items) {
        this.livros = items;
        notifyDataSetChanged();
    }
}
