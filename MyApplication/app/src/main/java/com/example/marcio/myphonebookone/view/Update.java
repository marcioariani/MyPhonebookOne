package com.example.marcio.myphonebookone.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.marcio.myphonebookone.R;
import com.example.marcio.myphonebookone.model.Contatos;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Update extends AppCompatActivity {

    @Bind(R.id.picture)
    ImageView imvImagem;


    @Bind(R.id.edtTelefone)
    EditText edtTelefone;
    @Bind(R.id.edtNome)
    EditText edtNome;

    @Bind(R.id.fabDel)
    FloatingActionButton fbDell;

    private boolean update = false;
    private Contatos conts;

    private String localFoto;

    private static final int FOTO = 1;

    private boolean fotoResource = false;

    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conts = new Contatos();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            conts = (Contatos) b.get("contato");
            update = true;
            fbDell.setVisibility(View.VISIBLE);
            updateUI();
            localFoto = conts.getPicture();
        }
    }

    @OnClick(R.id.fabDel)
    public void fabDel(View view) {
        conts.delete();
        Toast.makeText(getApplicationContext(),"Contato excluido!", Toast.LENGTH_SHORT).show();
        finish();
    }


    @OnClick(R.id.fabCad)
    public void fabcad(View v) {

        conts.setNome(edtNome.getText().toString());
        conts.setTelefone(edtTelefone.getText().toString());
        if (update) {
            conts.setPicture((String) imvImagem.getTag());
            conts.update();
            Toast.makeText(getApplicationContext(),"Contato alterado!", Toast.LENGTH_SHORT).show();
        } else {
            conts.setNome(edtNome.getText().toString());
            conts.setTelefone(edtTelefone.getText().toString());
            conts.setPicture((String) imvImagem.getTag());
            Log.i("Teste", (String) imvImagem.getTag());
            conts.save();
            List<Contatos> lanches = SQLite.select().from(Contatos.class).queryList();
            Toast.makeText(getApplicationContext(),"Novo contato criado!", Toast.LENGTH_SHORT).show();

        }

        finish();
    }

    public void updateUI() {
        if (conts == null) {
            edtNome.setText(null);
            edtTelefone.setText(null);
        } else {
            edtNome.setText(conts.getNome());
            setFoto(conts.getPicture());
            edtTelefone.setText(conts.getTelefone());
        }
    }

    public void carregaFoto() {
        fotoResource = true;
        localFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localFoto)));

        startActivityForResult(intentCamera, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!fotoResource) {
            if (resultCode == -1) {
                InputStream stream = null;
                try {
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    stream = getContentResolver().openInputStream(data.getData());
                    bitmap = BitmapFactory.decodeStream(stream);
                    imvImagem.setImageBitmap(bitmap);
                    localFoto=data.getDataString();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            if (requestCode == FOTO) {
                if (resultCode == Activity.RESULT_OK) {
                    setFoto(localFoto);
                } else {
                    this.localFoto = null;
                }
            }
        }

    }

    private void setFoto(String url) {
        if (url != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(url);
            imvImagem.setImageBitmap(imagemFoto);
            imvImagem.setTag(url);
        }
    }

    @OnClick(R.id.fabFhoto)
    public  void foto(View v){
        carregaFoto();
    }

}
