package com.example.petvitaoriginal.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.petvitaoriginal.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class VacinaDetailActivity extends AppCompatActivity {

    private TextView nomeVacina, dataVacina, numeroDoses, doseAplicada, nomeVeterinario, lote, local, notas;
    private Button btnExportarPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_detail);

        // Inicializando os TextViews
        nomeVacina = findViewById(R.id.nomeVacina);
        dataVacina = findViewById(R.id.dataVacina);
        numeroDoses = findViewById(R.id.numeroDoses);
        doseAplicada = findViewById(R.id.doseAplicada);
        nomeVeterinario = findViewById(R.id.nomeVeterinario);
        lote = findViewById(R.id.lote);
        local = findViewById(R.id.local);
        notas = findViewById(R.id.notas);
        btnExportarPDF = findViewById(R.id.btnExportarPDF);

        // Obtendo os dados passados pela Intent
        String vacinaNome = getIntent().getStringExtra("VACINA_NOME");
        String vacinaData = getIntent().getStringExtra("VACINA_DATA");
        String vacinaNumeroDoses = getIntent().getStringExtra("VACINA_NUMERO_DOSES");
        String vacinaDoseAplicada = getIntent().getStringExtra("VACINA_DOSE_APLICADA");
        String vacinaNomeVeterinario = getIntent().getStringExtra("VACINA_NOME_VETERINARIO");
        String vacinaLote = getIntent().getStringExtra("VACINA_LOTE");
        String vacinaLocal = getIntent().getStringExtra("VACINA_LOCAL");
        String vacinaNotas = getIntent().getStringExtra("VACINA_NOTAS");

        // Definindo os dados nos TextViews
        nomeVacina.setText(vacinaNome);
        dataVacina.setText(vacinaData);
        numeroDoses.setText(vacinaNumeroDoses);
        doseAplicada.setText(vacinaDoseAplicada);
        nomeVeterinario.setText(vacinaNomeVeterinario);
        lote.setText(vacinaLote);
        local.setText(vacinaLocal);
        notas.setText(vacinaNotas);

        // Listener para o botão de exportação
        btnExportarPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportarPDF();
            }
        });
    }

    private void exportarPDF() {
        // Verifica permissão de armazenamento
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            // Cria documento PDF
            PdfDocument pdfDocument = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
            PdfDocument.Page page = pdfDocument.startPage(pageInfo);

            // Canvas para desenhar no PDF
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setTextSize(12);
            paint.setColor(Color.BLACK);

            // Desenha os detalhes da vacina no PDF
            canvas.drawText("Nome da Vacina: " + nomeVacina.getText(), 10, 25, paint);
            canvas.drawText("Data da Vacina: " + dataVacina.getText(), 10, 50, paint);
            canvas.drawText("Número de Doses: " + numeroDoses.getText(), 10, 75, paint);
            canvas.drawText("Dose Aplicada: " + doseAplicada.getText(), 10, 100, paint);
            canvas.drawText("Veterinário: " + nomeVeterinario.getText(), 10, 125, paint);
            canvas.drawText("Lote: " + lote.getText(), 10, 150, paint);
            canvas.drawText("Local da Aplicação: " + local.getText(), 10, 175, paint);
            canvas.drawText("Notas: " + notas.getText(), 10, 200, paint);

            pdfDocument.finishPage(page);

            // Salva o PDF na pasta Downloads
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File filePath = new File(downloadsDir, "VacinaDetail.pdf");
            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                pdfDocument.writeTo(fos);
                fos.close();
                Toast.makeText(this, "PDF exportado com sucesso: " + filePath.getAbsolutePath(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao exportar PDF", Toast.LENGTH_SHORT).show();
            } finally {
                pdfDocument.close();
            }
        }
    }
}
