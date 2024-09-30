package com.example.petvitaoriginal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConsultasActivity extends AppCompatActivity {

    private EditText editTextConsulta;
    private Button buttonAddConsulta;
    private RecyclerView recyclerViewConsultas;
    private ConsultasAdapter consultasAdapter;
    private ArrayList<String> listaConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        // Inicializando a lista de consultas
        listaConsultas = new ArrayList<>();

        // Referências dos componentes da UI
        editTextConsulta = findViewById(R.id.editTextConsulta);
        buttonAddConsulta = findViewById(R.id.buttonAddConsulta);
        recyclerViewConsultas = findViewById(R.id.recyclerViewConsultas);

        // Configurando RecyclerView
        recyclerViewConsultas.setLayoutManager(new LinearLayoutManager(this));
        consultasAdapter = new ConsultasAdapter(listaConsultas);
        recyclerViewConsultas.setAdapter(consultasAdapter);

        // Configuração do botão para adicionar consulta
        buttonAddConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String consulta = editTextConsulta.getText().toString().trim();
                if (!TextUtils.isEmpty(consulta)) {
                    listaConsultas.add(consulta);
                    consultasAdapter.notifyItemInserted(listaConsultas.size() - 1);
                    editTextConsulta.setText("");
                }
            }
        });
    }
}
