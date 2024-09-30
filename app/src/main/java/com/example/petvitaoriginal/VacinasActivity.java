package com.example.petvitaoriginal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VacinasActivity extends AppCompatActivity {
    private RecyclerView recyclerViewVacinas;
    private VacinaAdapter vacinaAdapter;
    private List<String> listaVacinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);

        // Inicializando a lista de vacinas
        listaVacinas = new ArrayList<>();
        vacinaAdapter = new VacinaAdapter(listaVacinas);

        // Configurando o RecyclerView
        recyclerViewVacinas = findViewById(R.id.recyclerViewVacinas);
        recyclerViewVacinas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVacinas.setAdapter(vacinaAdapter);

        // Botão para adicionar nova vacina
        Button buttonAddVacina = findViewById(R.id.buttonAddVacina);
        buttonAddVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarVacina("Vacina " + (listaVacinas.size() + 1)); // Adiciona uma nova vacina
            }
        });
    }

    private void adicionarVacina(String vacina) {
        listaVacinas.add(vacina);
        vacinaAdapter.notifyItemInserted(listaVacinas.size() - 1);
        recyclerViewVacinas.scrollToPosition(listaVacinas.size() - 1); // Rolagem para a nova vacina
    }
}
