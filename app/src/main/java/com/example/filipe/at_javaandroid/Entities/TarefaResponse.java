package com.example.filipe.at_javaandroid.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Filipe on 19/12/2017.
 */

public class TarefaResponse implements Serializable {

    @SerializedName("tarefa")
    private List<Tarefa> tarefas;

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefa(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}