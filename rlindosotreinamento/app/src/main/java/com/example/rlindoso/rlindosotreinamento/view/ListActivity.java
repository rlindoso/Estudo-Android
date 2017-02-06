package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.adapter.AppTreinamentoAdapter;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;
import com.example.rlindoso.rlindosotreinamento.repository.AppTreinamentoRepository;

public class ListActivity extends BaseActivity {
    private AppTreinamentoAdapter adapter;
    TextView edtPesquisa;
    private int editPosition;

    private AppTreinamentoRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editPosition = -1;

        repo = new AppTreinamentoRepository(this);

        final ListView listView = (ListView) findViewById(R.id.list);
        edtPesquisa = (TextView) findViewById(R.id.edtPesquisa);

        adapter = new AppTreinamentoAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppTreinamento appTreinamento = adapter.getItem(position);

                //showMessage("Nome: " + appTreinamento.getNome());
                Intent intent = new Intent(ListActivity.this, AppTreinamentoDetailActivity.class);
                intent.putExtra(AppTreinamento.EXTRA, appTreinamento);

                startActivity(intent);

            }
        });

        edtPesquisa.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    salvar();
                    return true;
                }
                // Return true if you have consumed the action, else false.
                return false;
            }
        });

        registerForContextMenu(listView);

    }

    private void salvar() {
        String nome = edtPesquisa.getText().toString();

        if (nome.equals("")) {
            edtPesquisa.setError("Informe um valor válido");
        } else {
            AppTreinamento app = new AppTreinamento(nome, 1000, "Objetivo teste");

            repo.save(app);

            editPosition = -1;
            edtPesquisa.setText("");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AppTreinamento appTreinamento = adapter.getItem(info.position);

        if (item.getItemId() == R.id.mnuEditar) {
            repo.save(appTreinamento);
            edtPesquisa.setText(appTreinamento.getNome());
        } else {
            if (appTreinamento != null) {
                repo.delete(appTreinamento.getId());
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }
}
