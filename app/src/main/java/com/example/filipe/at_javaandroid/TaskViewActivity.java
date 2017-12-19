package com.example.filipe.at_javaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.filipe.at_javaandroid.Entities.Tarefa;
import com.example.filipe.at_javaandroid.Entities.TarefaResponse;
import com.example.filipe.at_javaandroid.Interfaces.iTarefa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskViewActivity extends AppCompatActivity {

    private Button btnback;

    ListView listagem;
    ArrayAdapter<Tarefa> adapter = null;

    private static final String TAG = TaskViewActivity.class.getSimpleName();
    public static final String BASE_URL = "http://infnet.educacao.ws/dadosAtividades.php/";
    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        btnback = (Button) findViewById(R.id.btnback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        listagem = (ListView) findViewById(R.id.listViewtask);

        connectApiData();


    }


    private void connectApiData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        iTarefa tarefaInterface = retrofit.create(iTarefa.class);
        Call<TarefaResponse> call = tarefaInterface.getTarefas();

        call.enqueue(new Callback<TarefaResponse>() {
            @Override
            public void onResponse(Call<TarefaResponse> call, Response<TarefaResponse> response) {
                List<Tarefa> tarefas = response.body().getTarefas();
                //Log.d("123", "Retorno" + tarefas.size());
                adapter = new ArrayAdapter<Tarefa>(getApplicationContext(), android.R.layout.simple_list_item_1, tarefas);
                listagem.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<TarefaResponse> call, Throwable t) {

            }
        });
    }
}
