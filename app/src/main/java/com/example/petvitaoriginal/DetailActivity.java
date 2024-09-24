package com.example.petvitaoriginal;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView detailName, detailType, detailGender;
    ImageView detailImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailType = findViewById(R.id.detailType);
        detailName = findViewById(R.id.detailName);
        detailGender = findViewById(R.id.detailGender);
        detailImage = findViewById(R.id.detailImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailType.setText(bundle.getString("Tipo do animal"));
            detailName.setText(bundle.getString("nome do animal"));
            detailGender.setText(bundle.getString("sexo do animal"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

    }
}