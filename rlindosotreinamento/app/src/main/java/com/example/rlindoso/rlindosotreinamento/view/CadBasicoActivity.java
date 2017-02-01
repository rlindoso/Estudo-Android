package com.example.rlindoso.rlindosotreinamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rlindoso.rlindosotreinamento.R;
import com.example.rlindoso.rlindosotreinamento.adapter.AppTreinamentoAdapter;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;
import com.example.rlindoso.rlindosotreinamento.repository.AppTreinamentoRepository;
import com.example.rlindoso.rlindosotreinamento.utils.Constants;

import java.util.List;

public class CadBasicoActivity extends BaseActivity {
    TextView edtPesquisa;
    AppTreinamentoAdapter adapter;
    AppTreinamento copiarApp;

    AppTreinamentoRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repo = new AppTreinamentoRepository(this);

        final ListView listView = (ListView) findViewById(R.id.lista);
        edtPesquisa = (TextView) findViewById(R.id.edtPesquisa);

        adapter = new AppTreinamentoAdapter(this);
        listView.setAdapter(adapter);
        refreshList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppTreinamento appTreinamento = adapter.getItem(position);

                Intent intent = new Intent(CadBasicoActivity.this, AppTreinamentoDetailActivity.class);
                intent.putExtra(AppTreinamento.EXTRA, appTreinamento);

                startActivity(intent);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppTreinamento app = new AppTreinamento(edtPesquisa.getText().toString(), 0, "");
                openEditActivity(app);
            }
        });

        edtPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        registerForContextMenu(listView);

    }

    private void refreshList() {
        List<AppTreinamento> treinamentos = repo.getAll();
        adapter.setItems(treinamentos);
    }

    private void openEditActivity(AppTreinamento app) {
        Intent intent = new Intent(CadBasicoActivity.this, CadBasicoEditAddActivity.class);
        intent.putExtra(AppTreinamento.EXTRA, app);
        intent.putExtra(AppTreinamento.EXTRA_COPY, copiarApp);

        startActivityForResult(intent, Constants.REQUEST_CAD_APP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_CAD_APP) {
                AppTreinamento treinamento = (AppTreinamento) data.getSerializableExtra(AppTreinamento.EXTRA);
                salvar(treinamento);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void salvar(AppTreinamento treinamento) {

        if (treinamento.getId() > 0) {
            repo.update(treinamento);
        } else {
            repo.insert(treinamento);
            edtPesquisa.setText("");
        }

        refreshList();
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

        switch (item.getItemId()) {
            case R.id.mnuEditar:
                openEditActivity(appTreinamento);
                break;
            case R.id.mnuCopiar:
                copiarApp = adapter.getItem(info.position);
                break;
            case R.id.mnuExcluir:
                if (appTreinamento != null) {
                    repo.delete(appTreinamento.getId());
                    refreshList();
                }
                break;
            default:

        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cad_basico;
    }
}
