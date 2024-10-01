package com.example.petvitaoriginal.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petvitaoriginal.R;

public class VacinaDetailActivity extends AppCompatActivity {

    private TextView nomeVacina, dataVacina, numeroDoses, doseAplicada, nomeVeterinario, lote, local, notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_detail); // Certifique-se de ter esse layout

        // Inicializando os TextViews
        nomeVacina = findViewById(R.id.nomeVacina);
        dataVacina = findViewById(R.id.dataVacina);
        numeroDoses = findViewById(R.id.numeroDoses);
        doseAplicada = findViewById(R.id.doseAplicada);
        nomeVeterinario = findViewById(R.id.nomeVeterinario);
        lote = findViewById(R.id.lote);
        local = findViewById(R.id.local);
        notas = findViewById(R.id.notas);

        // Obtém os dados passados pela Intent
        String vacinaNome = getIntent().getStringExtra("VACINA_NOME");
        String vacinaData = getIntent().getStringExtra("VACINA_DATA");
        String vacinaNumeroDoses = getIntent().getStringExtra("VACINA_NUMERO_DOSES");
        String vacinaDoseAplicada = getIntent().getStringExtra("VACINA_DOSE_APLICADA");
        String vacinaNomeVeterinario = getIntent().getStringExtra("VACINA_NOME_VETERINARIO");
        String vacinaLote = getIntent().getStringExtra("VACINA_LOTE");
        String vacinaLocal = getIntent().getStringExtra("VACINA_LOCAL");
        String vacinaNotas = getIntent().getStringExtra("VACINA_NOTAS");

        // Define os dados nos TextViews
        nomeVacina.setText(vacinaNome);
        dataVacina.setText(vacinaData);
        numeroDoses.setText(vacinaNumeroDoses);
        doseAplicada.setText(vacinaDoseAplicada);
        nomeVeterinario.setText(vacinaNomeVeterinario);
        lote.setText(vacinaLote);
        local.setText(vacinaLocal);
        notas.setText(vacinaNotas);
    }
}
