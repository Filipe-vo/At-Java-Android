package com.example.filipe.at_javaandroid.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

/**
 * Created by Filipe on 19/12/2017.
 */

public class Preferences {

    private Context context;
    private SharedPreferences preferences;
    private String FILE_NAME =  "projetoFirebase.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;


    private final String CHAVE_INDENTIFICADORA = "IdentitificarUsuárioLogado";
    private final String CHAVE_NOME = "NomeUsuarioLogado";

    public Preferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME, MODE);

        editor = preferences.edit();
    }

    public void SalvarUsuariosPreferencias(String identificadorUsuario, String nomeUsuario ){
        editor.putString(CHAVE_INDENTIFICADORA, identificadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_INDENTIFICADORA, null);
    }

    public String getNome(){
        return preferences.getString(CHAVE_NOME, null);
    }
}
