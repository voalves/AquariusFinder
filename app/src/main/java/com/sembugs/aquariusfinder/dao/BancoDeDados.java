package com.sembugs.aquariusfinder.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.sembugs.aquariusfinder.modelos.LineAdapter;
import com.sembugs.aquariusfinder.modelos.Morador;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {
    public static final String NOMEDB = "db_aquaconecta.sqlite";
    public static final String LOCALDB = "/data/data/com.sembugs.aquariusfinder/databases/";
    public static final int VERSION = 1;
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public BancoDeDados(Context context) {
        super(context, NOMEDB, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(NOMEDB).getPath();
        if (mSQLiteDatabase != null && mSQLiteDatabase.isOpen()){
            return;
        }
        mSQLiteDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if (mSQLiteDatabase != null){
            mSQLiteDatabase.close();
        }
    }

    public List<Morador> allMoradores(String text, int tipo, LineAdapter mAdapter){
        int quantidade_registros = 0;
        mAdapter.clearList();
        mAdapter.notifyDataSetChanged();
        openDatabase();
        mSQLiteDatabase = this.getWritableDatabase();
        List<Morador> listMorador = new ArrayList<Morador>();
        String info="";
        String sql = "Select moradores.id_morador,unidades.unidade,moradores.nome from moradores,unidades where moradores.id_unidade=unidades.id_unidade and moradores.nome LIKE '%"+text+"%' order by moradores.nome ASC";
        if (tipo==1){
            sql = "Select moradores.id_morador,unidades.unidade,moradores.nome,moradores.tel2,moradores.email from moradores,unidades where moradores.id_unidade=unidades.id_unidade and moradores.nome LIKE '%"+text+"%' order by moradores.nome ASC";
        }
        if (tipo==2){
            sql = "Select moradores.id_morador,unidades.unidade,moradores.nome,moradores.tel2,moradores.email from moradores,unidades where moradores.id_unidade=unidades.id_unidade and unidades.unidade LIKE '%"+text+"%' order by unidades.unidade ASC";
        }
        if (tipo==3){
            sql = "Select veiculos.id_veiculo,  unidades.unidade,veiculos.placa, Count(moradores.nome) from unidades,veiculos,moradores where unidades.id_unidade=moradores.id_unidade and unidades.id_unidade=veiculos.id_unidade and veiculos.placa LIKE '%"+text+"%' group by moradores.id_unidade order by veiculos.placa ASC";
        }

        Cursor cursor = mSQLiteDatabase.rawQuery(sql,null);
        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do{
                    Morador morador = new Morador();

                    if (tipo==1 || tipo==2) {

                        morador.setiMorador(cursor.getInt(0));
                        morador.setiUnidade(cursor.getInt(1));
                        morador.setsNome(cursor.getString(2));
                        morador.setsTel2(cursor.getString(3));
                        morador.setsEmail(cursor.getString(4));
                    }
                    if (tipo==3) {

                        morador.setiMorador(cursor.getInt(0)); //id_veiculo
                        morador.setiUnidade(cursor.getInt(1));
                        morador.setsNome(cursor.getString(2)); //placa
                        morador.setsTel2(cursor.getString(3)); //nome
                        morador.setsEmail("PLACA");
                    }


                    listMorador.add(morador);
                    mAdapter.insertItem(morador) ;
                    quantidade_registros++;
                } while (cursor.moveToNext());{

                }
            }
        }
        cursor.close();
        mSQLiteDatabase.close();
        //Toast.makeText(this,"registros encontrados",Toast.LENGTH_LONG).show();

        Toast.makeText(mContext.getApplicationContext(),quantidade_registros+" registro(s) encontrado(s)", Toast.LENGTH_LONG).show();
        return listMorador;
    }
}
