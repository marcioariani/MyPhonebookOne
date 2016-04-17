package com.example.marcio.myphonebookone.model;

import com.example.marcio.myphonebookone.helper.BancoHelper;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by marcio on 17/04/2016.
 */

@Table(database = BancoHelper.class)
public class Contatos extends BaseModel implements Serializable{
    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String picture;


    public Contatos() {
    }

    public Contatos(String nome, String telefone, String picture) {
        this.nome = nome;
        this.telefone = telefone;
        this.picture = picture;
    }

    public Contatos(long id, String nome, String telefone, String picture) {
        this.nome = nome;
        this.telefone = telefone;
        this.picture = picture;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
