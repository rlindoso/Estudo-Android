package sample.treinamento.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import sample.treinamento.R;
import sample.treinamento.model.Pessoa;

/**
 * Created by palves on 26/01/2017.
 */

public class PessoaAdapter extends ArrayAdapter<Pessoa> {
    Pessoa[] pessoas;

    public PessoaAdapter(Context context, Pessoa[] pessoas) {
        super(context, android.R.layout.simple_list_item_1, pessoas);
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public Pessoa getItem(int position) {
        return pessoas[position];
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Pessoa pessoa = getItem(position);

        TextView txtNome = (TextView) convertView.findViewById(android.R.id.text1);
        txtNome.setText(pessoa.getNome());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_pessoa, parent, false);
        }

        Pessoa pessoa = getItem(position);

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtIdade = (TextView) convertView.findViewById(R.id.txtIDade);
        txtNome.setText(pessoa.getNome());
        txtIdade.setText(String.valueOf(pessoa.getIdade()));

        return convertView;
    }
}
