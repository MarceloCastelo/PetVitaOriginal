package com.example.petvitaoriginal;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView detailName, detailType, detailGender;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ligação dos componentes com o layout XML
        detailType = findViewById(R.id.detailType);
        detailName = findViewById(R.id.detailName);
        detailGender = findViewById(R.id.detailGender);
        detailImage = findViewById(R.id.detailImage);

        // Verificar se o bundle contém os dados e exibir no layout
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tipo = bundle.getString("tipo_do_animal");
            String nome = bundle.getString("nome_do_animal");
            String sexo = bundle.getString("sexo_do_animal");
            String imageUrl = bundle.getString("image_url");

            // Verificação de logs para depuração
            Log.d("DetailActivity", "Tipo: " + tipo + ", Nome: " + nome + ", Sexo: " + sexo + ", Imagem: " + imageUrl);

            // Definir os textos nos TextViews
            detailType.setText(tipo);
            detailName.setText(nome);
            detailGender.setText(sexo);

            // Usar Glide para carregar a imagem
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(this).load(imageUrl).into(detailImage);
            } else {
                Log.d("DetailActivity", "URL da imagem é inválida ou vazia");
            }
        } else {
            Log.d("DetailActivity", "Bundle está vazio");
        }

    }
}
