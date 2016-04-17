package com.example.marcio.myphonebookone.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.marcio.myphonebookone.R;
import com.example.marcio.myphonebookone.model.Contatos;
import com.example.marcio.myphonebookone.phonebook.CustonPhone;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.lista)
    ListView listView;

    private List<Contatos> contats = new ArrayList<Contatos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        updateUi();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("men", "update" + contats.get(position).getNome());
                Intent i = new Intent(getBaseContext(), Update.class);
                i.putExtra("contato", contats.get(position));

                startActivity(i);
            }
        });
    }


    @OnClick(R.id.fab)
    public void onFabCad(View view) {
        Intent i = new Intent(getBaseContext(), Update.class);
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
    }

    private void updateUi() {
        contats = SQLite.select().from(Contatos.class).queryList();

        CustonPhone custonPhone = new CustonPhone(contats, getApplicationContext());
        listView.setAdapter(custonPhone);
    }
}
