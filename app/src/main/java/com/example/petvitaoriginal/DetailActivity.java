package com.example.petvitaoriginal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {

    private TextView detailName, detailGender, detailType;
    private ImageView detailImage;
    private Button editButton, deleteButton;
    private String petKey; // Para armazenar a chave do pet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailName = findViewById(R.id.detailName);
        detailGender = findViewById(R.id.detailGender);
        detailType = findViewById(R.id.detailType);
        detailImage = findViewById(R.id.detailImage);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        // Obtendo os dados passados pela Intent
        Intent intent = getIntent();
        petKey = intent.getStringExtra("Key");
        String imageUrl = intent.getStringExtra("image_url");
        String name = intent.getStringExtra("nome_do_animal");
        String gender = intent.getStringExtra("sexo_do_animal");
        String type = intent.getStringExtra("tipo_do_animal");

        // Definindo os dados na UI
        detailName.setText(name);
        detailGender.setText(gender);
        detailType.setText(type);
        Glide.with(this).load(imageUrl).into(detailImage);

        // Listener para o botão de editar
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crie uma Intent para abrir a UploadActivity com os dados do pet
                Intent editIntent = new Intent(DetailActivity.this, UploadActivity.class);
                editIntent.putExtra("Key", petKey);
                editIntent.putExtra("image_url", imageUrl);
                editIntent.putExtra("nome_do_animal", name);
                editIntent.putExtra("sexo_do_animal", gender);
                editIntent.putExtra("tipo_do_animal", type);
                startActivity(editIntent);
                finish();
            }
        });

        // Listener para o botão de deletar
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }
        });
    }

    // Método para mostrar a confirmação de deleção
    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar Pet");
        builder.setMessage("Tem certeza de que deseja deletar este pet?");
        builder.setPositiveButton("Sim", (dialog, which) -> deletePet());
        builder.setNegativeButton("Não", null);
        builder.show();
    }

    // Método para deletar o pet
    private void deletePet() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users")
                .child(userId)
                .child("Pets")
                .child(petKey) // Usa a chave do pet para deletar
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailActivity.this, "Pet deletado com sucesso", Toast.LENGTH_SHORT).show();
                            finish(); // Fecha a activity após a deleção
                        } else {
                            Toast.makeText(DetailActivity.this, "Erro ao deletar pet", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
