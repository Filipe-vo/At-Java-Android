package com.example.filipe.at_javaandroid.Entities;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Filipe on 19/12/2017.
 */

public class Tarefa {


    @SerializedName("id")
    public String id ;
    @SerializedName("descricao")
    public String descricao;
    @SerializedName("imagem")
    public String image;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String toString() {
        return getDescricao();
    }
}