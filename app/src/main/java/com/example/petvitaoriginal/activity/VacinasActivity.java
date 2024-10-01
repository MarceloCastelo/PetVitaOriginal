package com.example.petvitaoriginal.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petvitaoriginal.R;
import com.example.petvitaoriginal.adapter.VacinaAdapter;
import com.example.petvitaoriginal.classes.Vacina;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VacinasActivity extends AppCompatActivity {
    private RecyclerView recyclerViewVacinas;
    private VacinaAdapter vacinaAdapter;
    private List<Vacina> listaVacinas;

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
                mostrarDialogAdicionarVacina();
            }
        });
    }

    private void mostrarDialogAdicionarVacina() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar Vacina");

        // Inflate o layout do diálogo
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_vacina, null);
        builder.setView(dialogView);

        // Referências para os EditTexts
        EditText editTextCodigoVacina = dialogView.findViewById(R.id.editTextCodigoVacina);
        EditText editTextTipoVacina = dialogView.findViewById(R.id.editTextTipoVacina);
        EditText editTextDataVacina = dialogView.findViewById(R.id.editTextDataVacina);
        EditText editTextNumeroTotalDoses = dialogView.findViewById(R.id.editTextNumeroTotalDoses);
        EditText editTextDoseAplicada = dialogView.findViewById(R.id.editTextDoseAplicada);
        EditText editTextNomeVeterinario = dialogView.findViewById(R.id.editTextNomeVeterinario);
        EditText editTextLoteVacina = dialogView.findViewById(R.id.editTextLoteVacina);
        EditText editTextLocalAplicacao = dialogView.findViewById(R.id.editTextLocalAplicacao);
        EditText editTextNotasAdicionais = dialogView.findViewById(R.id.editTextNotasAdicionais);

        // Abrindo o DatePickerDialog ao clicar no EditText da data
        editTextDataVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker(editTextDataVacina);
            }
        });

        // Botão Salvar
        builder.setPositiveButton("Salvar", null); // Não atribuir a ação ainda

        // Botão Cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Fecha o diálogo
            }
        });

        // Criar o diálogo
        AlertDialog dialog = builder.create();

        // Configurando a ação do botão "Salvar"
        dialog.setOnShowListener(dialogInterface -> {
            Button btnSalvar = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            btnSalvar.setOnClickListener(v -> {
                String codigoVacina = editTextCodigoVacina.getText().toString().trim();
                String tipoVacina = editTextTipoVacina.getText().toString().trim();
                String dataVacina = editTextDataVacina.getText().toString().trim();
                String numeroTotalDoses = editTextNumeroTotalDoses.getText().toString().trim();
                String doseAplicada = editTextDoseAplicada.getText().toString().trim();
                String nomeVeterinario = editTextNomeVeterinario.getText().toString().trim();
                String loteVacina = editTextLoteVacina.getText().toString().trim();
                String localAplicacao = editTextLocalAplicacao.getText().toString().trim();
                String notasAdicionais = editTextNotasAdicionais.getText().toString().trim();

                // Verifica se os campos obrigatórios estão preenchidos
                if (codigoVacina.isEmpty() || tipoVacina.isEmpty() || dataVacina.isEmpty() ||
                        numeroTotalDoses.isEmpty() || doseAplicada.isEmpty() || nomeVeterinario.isEmpty() ||
                        loteVacina.isEmpty() || localAplicacao.isEmpty()) {

                    // Exibe um aviso se algum campo obrigatório estiver vazio
                    AlertDialog.Builder warningBuilder = new AlertDialog.Builder(VacinasActivity.this);
                    warningBuilder.setMessage("Por favor, preencha todos os campos obrigatórios.")
                            .setPositiveButton("OK", null);
                    warningBuilder.show();
                } else {
                    // Adiciona a vacina na lista se todos os campos estiverem preenchidos
                    adicionarVacina(codigoVacina, tipoVacina, dataVacina, numeroTotalDoses, doseAplicada, nomeVeterinario, loteVacina, localAplicacao, notasAdicionais);
                    dialog.dismiss(); // Fecha o diálogo se tudo estiver certo
                }
            });
        });

        dialog.show(); // Mostra o diálogo
    }

    private void abrirDatePicker(EditText editTextDataVacina) {
        // Cria um Calendar para pegar a data atual
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // Cria o DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
            editTextDataVacina.setText(dataSelecionada);
        }, ano, mes, dia);

        datePickerDialog.show(); // Mostra o DatePickerDialog
    }

    private void adicionarVacina(String codigo, String tipo, String data, String numeroDoses, String doseAplicada, String nomeVeterinario, String lote, String local, String notas) {
        // Cria uma nova instância de Vacina
        Vacina novaVacina = new Vacina(codigo, tipo, data, numeroDoses, doseAplicada, nomeVeterinario, lote, local, notas);
        listaVacinas.add(novaVacina);
        vacinaAdapter.notifyItemInserted(listaVacinas.size() - 1);
        recyclerViewVacinas.scrollToPosition(listaVacinas.size() - 1); // Rolagem para a nova vacina
    }
}
