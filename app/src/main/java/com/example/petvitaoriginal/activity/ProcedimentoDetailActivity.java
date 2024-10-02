package com.example.petvitaoriginal.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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

public class ProcedimentoDetailActivity extends AppCompatActivity {

    private TextView tipoProcedimento, dataProcedimento, nomeVeterinario, notasAdicionais;
    private Button btnExportarPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimento_detail);

        // Inicializando os TextViews
        tipoProcedimento = findViewById(R.id.tipoProcedimento);
        dataProcedimento = findViewById(R.id.dataProcedimento);
        nomeVeterinario = findViewById(R.id.nomeVeterinario);
        notasAdicionais = findViewById(R.id.notasAdicionais);
        btnExportarPDF = findViewById(R.id.btnExportarPDF);

        // Obtendo os dados passados pela Intent
        String procedimentoTipo = getIntent().getStringExtra("PROCEDIMENTO_TIPO");
        String procedimentoData = getIntent().getStringExtra("PROCEDIMENTO_DATA");
        String procedimentoNomeVeterinario = getIntent().getStringExtra("PROCEDIMENTO_NOME_VETERINARIO");
        String procedimentoNotasAdicionais = getIntent().getStringExtra("PROCEDIMENTO_NOTAS_ADICIONAIS");

        // Log para depuração
        Log.d("ProcedimentoDetail", "Tipo: " + procedimentoTipo);
        Log.d("ProcedimentoDetail", "Data: " + procedimentoData);
        Log.d("ProcedimentoDetail", "Veterinário: " + procedimentoNomeVeterinario);
        Log.d("ProcedimentoDetail", "Notas: " + procedimentoNotasAdicionais);

        // Definindo os dados nos TextViews
        tipoProcedimento.setText(procedimentoTipo != null ? procedimentoTipo : "Tipo não disponível");
        dataProcedimento.setText(procedimentoData != null ? procedimentoData : "Data não disponível");
        nomeVeterinario.setText(procedimentoNomeVeterinario != null ? procedimentoNomeVeterinario : "Veterinário não disponível");
        notasAdicionais.setText(procedimentoNotasAdicionais != null ? procedimentoNotasAdicionais : "Sem notas adicionais");

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

            // Desenha os detalhes do procedimento no PDF
            canvas.drawText("Tipo de Procedimento: " + tipoProcedimento.getText(), 10, 25, paint);
            canvas.drawText("Data do Procedimento: " + dataProcedimento.getText(), 10, 50, paint);
            canvas.drawText("Veterinário: " + nomeVeterinario.getText(), 10, 75, paint);
            canvas.drawText("Notas Adicionais: " + notasAdicionais.getText(), 10, 100, paint);

            pdfDocument.finishPage(page);

            // Salva o PDF na pasta Downloads
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File filePath = new File(downloadsDir, "ProcedimentoDetail.pdf");
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
