package com.example.petvitaoriginal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class UploadActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;
    EditText uploadPetName, uploadPetType, uploadPetGender;
    String imageURL;
    Uri uri;
    String petKey; // Para armazenar a chave do pet, se estiver editando
    boolean isEditing = false; // Para saber se estamos editando ou adicionando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadImage = findViewById(R.id.uploadImage);
        uploadPetName = findViewById(R.id.uploadPetName);
        uploadPetType = findViewById(R.id.uploadPetType);
        uploadPetGender = findViewById(R.id.uploadPetGender);
        saveButton = findViewById(R.id.saveButton);

        // Verifica se há dados passados pela Intent para edição
        Intent intent = getIntent();
        petKey = intent.getStringExtra("Key");
        if (petKey != null) {
            isEditing = true; // Estamos em modo de edição
            String imageUrl = intent.getStringExtra("image_url");
            String name = intent.getStringExtra("nome_do_animal");
            String type = intent.getStringExtra("tipo_do_animal");
            String gender = intent.getStringExtra("sexo_do_animal");

            // Preenche os campos com os dados do pet
            uploadPetName.setText(name);
            uploadPetType.setText(type);
            uploadPetGender.setText(gender);
            Glide.with(this).load(imageUrl).into(uploadImage);
        }

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void saveData() {
        if (isEditing) {
            updateData(); // Se estamos editando, chama updateData
        } else {
            uploadImage(); // Caso contrário, faz o upload da nova imagem
        }
    }

    private void uploadImage() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData(); // Faz upload dos dados
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData() {
        String name = uploadPetName.getText().toString();
        String type = uploadPetType.getText().toString();
        String gender = uploadPetGender.getText().toString();

        HashMap<String, Object> updates = new HashMap<>();
        updates.put("dataPetName", name);
        updates.put("dataPetType", type);
        updates.put("dataPetGender", gender);

        // Se uma nova imagem foi selecionada, faça o upload
        if (uri != null) {
            uploadImage(); // Método para fazer o upload da nova imagem
        } else {
            // Se não houver nova imagem, apenas atualize os dados
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("Pets")
                    .child(petKey) // Usa a chave do pet
                    .updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UploadActivity.this, "Dados atualizados", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void uploadData() {
        // Verifica se o usuário está autenticado
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Obtém o UID do usuário autenticado
            String userId = user.getUid();

            DataClass dataClass = new DataClass(uploadPetName.getText().toString(),
                    uploadPetType.getText().toString(),
                    uploadPetGender.getText().toString(),
                    imageURL);

            // Salva os dados no caminho do usuário autenticado
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(userId)  // Usa o UID do usuário como nó
                    .child("Pets")  // Usa um nó adicional para organizar os dados
                    .child(uploadPetName.getText().toString())  // Usa o nome do pet como identificador do registro
                    .setValue(dataClass)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                finish();

                                Intent refreshIntent = new Intent(UploadActivity.this, UploadActivity.class);
                                refreshIntent.putExtra("Key", petKey);
                                startActivity(refreshIntent);
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Se o usuário não estiver autenticado, mostre uma mensagem de erro ou faça outra ação
            Toast.makeText(UploadActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }
}
