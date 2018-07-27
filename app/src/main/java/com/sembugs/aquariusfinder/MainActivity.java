package com.sembugs.aquariusfinder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sembugs.aquariusfinder.dao.BancoDeDados;
import com.sembugs.aquariusfinder.modelos.LineAdapter;
import com.sembugs.aquariusfinder.modelos.LineHolder;
import com.sembugs.aquariusfinder.modelos.Morador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BancoDeDados mBancoDeDados;
    private Button btn_pesquisar;
    private EditText edit_pesquisa;
    private TextView titulo_respostas;
    private RadioButton rb_morador, rb_unidade, rb_veiculo;
    private List<Morador> listMorador = new ArrayList<Morador>();

    //private ListView lvMorador;
    //private ArrayAdapter<Morador> arrayAdapterMorador;

    private RecyclerView rvResultados;
    private LineAdapter mAdapter;

    private int list_position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();
        inicializarBancoDeDados();
        setupRecycler();

        btn_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popularListaMoradores();
            }
        });

        /*rvResultados.setOnLongClickListener(  new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int posicao, long id) {

                list_position = posicao;

                // TODO Auto-generated method stub

                //registra o menu após o click

                registerForContextMenu(rvResultados);

                return false;

            }

        });*/
    }

    private void inicializarComponentes() {
        //lvMorador =     (ListView) findViewById(R.id.list_resultados);
        btn_pesquisar = (Button) findViewById(R.id.btn_pesquisar);
        edit_pesquisa = (EditText) findViewById(R.id.edit_pesquisa);
        rb_morador = (RadioButton) findViewById(R.id.rb_morador);
        rb_unidade = (RadioButton) findViewById(R.id.rb_unidade);
        rb_veiculo = (RadioButton) findViewById(R.id.rb_veiculo);
        titulo_respostas = (TextView) findViewById(R.id.textView_titulo);


    }


    private void popularListaMoradores() {
        int tipo = 1;
        if (rb_morador.isChecked()) {
            tipo = 1;
        }
        if (rb_unidade.isChecked()) {
            tipo = 2;
        }
        if (rb_veiculo.isChecked()) {
            tipo = 3;
        }
        String filtro = edit_pesquisa.getText().toString();
        if (filtro.length() > 1) {
            titulo_respostas.setText(R.string.tit_resultado);
            mBancoDeDados = new BancoDeDados(this);
            listMorador.clear();
            listMorador = mBancoDeDados.allMoradores(filtro, tipo, mAdapter);

        } else {
            Toast.makeText(this, "Necessário digitar pelo menos 2 caracteres!", Toast.LENGTH_LONG).show();
        }
    }

    private void inicializarBancoDeDados() {
        mBancoDeDados = new BancoDeDados(this);
        File database = getApplicationContext().getDatabasePath(BancoDeDados.NOMEDB);
        if (database.exists() == false) {
            mBancoDeDados.getReadableDatabase();
            if (copiaBanco(this)) {
                alert("Banco de Dados criado com sucesso!");
            } else {
                alert("Erro na criacao do Banco de Dados!");
            }
        }
    }

    private void alert(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }

    private boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(BancoDeDados.NOMEDB);
            String outFile = BancoDeDados.LOCALDB + BancoDeDados.NOMEDB;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            ;
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.

        rvResultados = (RecyclerView) findViewById(R.id.rv_resultados);
        //rvResultados.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvResultados.setLayoutManager(layoutManager);
        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter(new ArrayList<>(0), this);
        rvResultados.setAdapter(mAdapter);
        //registerForContextMenu(rvResultados);
        // Configurando um dividr entre linhas, para uma melhor visualização.
        //rvResultados.addItemDecoration(
        //        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


}