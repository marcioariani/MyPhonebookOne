package com.example.marcio.myphonebookone.phonebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.myphonebookone.R;
import com.example.marcio.myphonebookone.model.Contatos;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by marcio on 17/04/2016.
 */

public class CustonPhone extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<Contatos> itens;

    public CustonPhone(List<Contatos> itens, Context context){
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Contatos contatos = itens.get(position);

        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView =  layoutInflater.inflate(R.layout.item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.nome.setText(contatos.getNome());

        holder.telefone.setText(contatos.getTelefone());

        String url = contatos.getPicture();
        if (url != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(url);
            holder.imagem.setImageBitmap(imagemFoto);
            holder.imagem.setTag(url);
        }

        return convertView;
    }

    static class ViewHolder{
        @Bind(R.id.tvNome) TextView nome;
        @Bind(R.id.tvTelfone) TextView telefone;
        @Bind(R.id.imageView)
        ImageView imagem;
        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

}
