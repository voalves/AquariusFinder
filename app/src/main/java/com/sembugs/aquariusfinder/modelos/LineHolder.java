package com.sembugs.aquariusfinder.modelos;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.sembugs.aquariusfinder.R;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView txt_unidade,txt_nome,txt_telefone,txt_email;
    public ImageButton btn_phone,btn_whatsapp;

    public LineHolder(View itemView) {
        super(itemView);
        txt_unidade = (TextView) itemView.findViewById(R.id.id_unidade);
        txt_nome = (TextView) itemView.findViewById(R.id.id_nome_resultado);
        txt_telefone = (TextView) itemView.findViewById(R.id.id_tel_resultado);
        txt_email = (TextView) itemView.findViewById(R.id.id_email_resultado);
        btn_phone = (ImageButton) itemView.findViewById(R.id.bt_phone);
        btn_whatsapp = (ImageButton) itemView.findViewById(R.id.bt_whatsapp);
    }
}
