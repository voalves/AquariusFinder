package com.sembugs.aquariusfinder.modelos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sembugs.aquariusfinder.R;

import java.util.ArrayList;
import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private List<Morador> mMoradores;


    public LineAdapter(ArrayList moradores) {
        mMoradores = moradores;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_resultados, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {
        if (mMoradores.get(position).getsEmail()!="PLACA") {
            holder.txt_unidade.setText(Integer.toString(mMoradores.get(position).getiUnidade()));
            holder.txt_nome.setText(mMoradores.get(position).getsNome());
            if (mMoradores.get(position).getsTel2().length()>0){
                holder.txt_telefone.setText(mMoradores.get(position).getsTel2());
                holder.btn_phone.setVisibility(View.VISIBLE);
            } else {
                holder.txt_telefone.setText("");
                holder.btn_phone.setVisibility(View.INVISIBLE);
            }
            holder.txt_email.setText(mMoradores.get(position).getsEmail());

        } else {
            holder.txt_unidade.setText(mMoradores.get(position).getsNome());
            holder.txt_nome.setText("Unidade: "+Integer.toString(mMoradores.get(position).getiUnidade()));
            holder.txt_telefone.setText("Moradores: "+mMoradores.get(position).getsTel2());
            holder.txt_email.setText("");
            holder.btn_phone.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return mMoradores != null ? mMoradores.size() : 0;
    }


    public void clearList() {
        mMoradores.clear()
        ;
    }

    //
    //* Método publico chamado para atualziar a lista.
    // * @param user Novo objeto que será incluido na lista.
    // */
    public void updateList(Morador morador) {
        insertItem(morador);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.
    public void insertItem(Morador morador) {
        mMoradores.add(morador);
        notifyItemInserted(getItemCount());
    }

}
