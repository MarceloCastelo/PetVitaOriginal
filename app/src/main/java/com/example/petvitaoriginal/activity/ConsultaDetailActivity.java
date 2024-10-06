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

public class ConsultaDetailActivity extends AppCompatActivity {

    private TextView tipoConsulta, dataConsulta, nomeVeterinario, observacoes;
    private Button btnExportarPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_detail); // Certifique-se de criar este layout

        // Inicializando os TextViews
        tipoConsulta = findViewById(R.id.tipoConsulta);
        dataConsulta = findViewById(R.id.dataConsulta);
        nomeVeterinario = findViewById(R.id.nomeVeterinario);
        observacoes = findViewById(R.id.observacoes);
        btnExportarPDF = findViewById(R.id.btnExportarPDF);

        // Obtendo os dados passados pela Intent
        String consultaTipo = getIntent().getStringExtra("CONSULTA_TIPO");
        String consultaData = getIntent().getStringExtra("CONSULTA_DATA");
        String consultaNomeVeterinario = getIntent().getStringExtra("CONSULTA_NOME_VETERINARIO");
        String consultaNotasAdicionais = getIntent().getStringExtra("CONSULTA_NOTAS_ADICIONAIS");

        // Log para depuração
        Log.d("ConsultaDetail", "Tipo: " + consultaTipo);
        Log.d("ConsultaDetail", "Data: " + consultaData);
        Log.d("ConsultaDetail", "Veterinário: " + consultaNomeVeterinario);
        Log.d("ConsultaDetail", "Notas: " + consultaNotasAdicionais);

        // Definindo os dados nos TextViews
        tipoConsulta.setText(consultaTipo != null ? consultaTipo : "Tipo não disponível");
        dataConsulta.setText(consultaData != null ? consultaData : "Data não disponível");
        nomeVeterinario.setText(consultaNomeVeterinario != null ? consultaNomeVeterinario : "Veterinário não disponível");
        observacoes.setText(consultaNotasAdicionais != null ? consultaNotasAdicionais : "Sem notas adicionais");

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

            // Desenha os detalhes da consulta no PDF
            canvas.drawText("Tipo de Consulta: " + tipoConsulta.getText(), 10, 25, paint);
            canvas.drawText("Data da Consulta: " + dataConsulta.getText(), 10, 50, paint);
            canvas.drawText("Veterinário: " + nomeVeterinario.getText(), 10, 75, paint);
            canvas.drawText("Notas Adicionais: " + observacoes.getText(), 10, 100, paint);

            pdfDocument.finishPage(page);

            // Salva o PDF na pasta Downloads
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File filePath = new File(downloadsDir, "ConsultaDetail.pdf");
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
