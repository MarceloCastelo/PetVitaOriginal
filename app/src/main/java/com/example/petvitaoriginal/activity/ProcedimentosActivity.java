package com.example.petvitaoriginal.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.petvitaoriginal.R;

public class ProcedimentosActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMPORT_PDF = 1;
    private static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimentos);

        Button importPdfButton = findViewById(R.id.pdf_importer);
        importPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndImportPdf();
            }
        });
    }

    private void checkPermissionAndImportPdf() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permissão para ler armazenamento
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_STORAGE);
        } else {
            // Se já tiver permissão, iniciar importação do PDF
            importPdf();
        }
    }

    private void importPdf() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_IMPORT_PDF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMPORT_PDF && resultCode == RESULT_OK) {
            if (data != null) {
                Uri pdfUri = data.getData();
                // Aqui você pode manipular o PDF importado
                Toast.makeText(this, "PDF importado: " + pdfUri.getPath(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION_READ_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                importPdf();
            } else {
                Toast.makeText(this, "Permissão negada para ler o armazenamento", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
