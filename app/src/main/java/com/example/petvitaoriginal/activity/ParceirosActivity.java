package com.example.petvitaoriginal.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petvitaoriginal.R;

public class ParceirosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parceiros);

        Button buttonPetz = findViewById(R.id.button_petz);
        Button buttonPetlove = findViewById(R.id.button_petlove);

        buttonPetz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.petz.com.br/"));
                startActivity(intent);
            }
        });

        buttonPetlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.petlove.com.br/"));
                startActivity(intent);
            }
        });
    }
}
