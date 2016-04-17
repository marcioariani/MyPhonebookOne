package com.example.marcio.myphonebookone.helper;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by marcio on 17/04/2016.
 */
@Database(name = BancoHelper.NAME, version = BancoHelper.VERSION)
public class BancoHelper {
    // Nome da Base de dados
    public static final String NAME = "agenda";
    // Versão do Banco
    public static final int VERSION = 1;
}
