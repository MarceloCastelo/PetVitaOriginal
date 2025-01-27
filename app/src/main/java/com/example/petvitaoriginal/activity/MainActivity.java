package com.example.petvitaoriginal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petvitaoriginal.adapter.MyAdapter;
import com.example.petvitaoriginal.R;
import com.example.petvitaoriginal.adapter.ProfileActivity;
import com.example.petvitaoriginal.classes.Pets;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<Pets> dataList;
    MyAdapter adapter;
    SearchView searchView;
    FirebaseAuth firebaseAuth; // Declaração do FirebaseAuth
    DrawerLayout drawerLayout; // Declaração do DrawerLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa o FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Verifica se o usuário está logado
        if (firebaseAuth.getCurrentUser() == null) {
            // Se o usuário não estiver logado, redireciona para a LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Fecha a MainActivity para que o usuário não volte ao clicar em "voltar"
            return; // Interrompe a execução da onCreate se o usuário não estiver logado
        }

        // Configuração dos componentes da interface
        drawerLayout = findViewById(R.id.drawer_layout); // Inicializa o DrawerLayout
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        // Verifica o usuário logado e obtém o UID para acessar os dados correspondentes no Firebase
        String userId = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("Pets");

        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Pets pets = itemSnapshot.getValue(Pets.class);
                    if (pets != null) {
                        pets.setKey(itemSnapshot.getKey());
                        dataList.add(pets);
                    }
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        // Listener para a busca
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        // Listener para o FloatingActionButton (adiciona um novo pet)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        // Listener para o ícone de perfil
        findViewById(R.id.profile_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Listener para o ícone de menu
        findViewById(R.id.menu_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre o Drawer quando o menu for clicado
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        // Listeners para os itens do menu
        findViewById(R.id.item_quem_somos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuemSomosActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.LEFT); // Fecha o Drawer após a seleção
            }
        });

        findViewById(R.id.item_parceiros).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParceirosActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.LEFT); // Fecha o Drawer após a seleção
            }
        });

        findViewById(R.id.item_suporte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SuporteActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.LEFT); // Fecha o Drawer após a seleção
            }
        });
    }

    // Método para buscar na lista de pets
    public void searchList(String text) {
        ArrayList<Pets> searchList = new ArrayList<>();
        for (Pets pets : dataList) {
            if (pets.getDataPetName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(pets);
            }
        }
        adapter.searchDataList(searchList);
    }

    // Método para realizar logout
    public void logout() {
        firebaseAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
