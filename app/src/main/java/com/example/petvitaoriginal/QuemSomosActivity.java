package com.example.petvitaoriginal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class QuemSomosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_somos);

        Button buttonAcessarSite = findViewById(R.id.button_acessar_site);
        buttonAcessarSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/souunit.com.br/vitalcode/p%C3%A1gina-inicial"));
                startActivity(browserIntent);
            }
        });
    }
}
