package com.cieep.ejercicio02;

import android.content.Intent;
import android.os.Bundle;

import com.cieep.ejercicio02.adapters.PagosHoraAdapter;
import com.cieep.ejercicio02.modelos.PagoHora;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


import com.cieep.ejercicio02.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private List<PagoHora> pagosHora;
    private ActivityResultLauncher<Intent> crearLauncher;

    private PagosHoraAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        pagosHora = new ArrayList<>();

        adapter = new PagosHoraAdapter(pagosHora, R.layout.pago_hora_view_holder, this);
        layoutManager = new GridLayoutManager(this, 1);

        binding.contentMain.contenedor.setAdapter(adapter);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        crearLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData().getExtras() != null) {
                                PagoHora ph = (PagoHora) result.getData().getExtras().getSerializable("PAGO");
                                pagosHora.add(ph);
                                adapter.notifyItemInserted(pagosHora.size()-1);
                            }
                        }
                    }
                }
        );

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearLauncher.launch(new Intent(MainActivity.this, CrearPagoActivity.class));
            }
        });
    }


}