package com.example.filipe.at_javaandroid.Interfaces;

import com.example.filipe.at_javaandroid.Entities.TarefaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Filipe on 19/12/2017.
 */

public interface iTarefa {
    @GET("./")
    Call<TarefaResponse> getTarefas();
}