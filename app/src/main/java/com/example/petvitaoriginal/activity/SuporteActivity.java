package com.example.petvitaoriginal.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petvitaoriginal.R;

public class SuporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suporte);

        Button buttonEnviarEmail = findViewById(R.id.button_enviar_email);
        buttonEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:fernando.bento@souunit.com.br")); // somente e-mails
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contato sobre o projeto");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá, gostaria de saber mais sobre o projeto.");

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });
    }
}
