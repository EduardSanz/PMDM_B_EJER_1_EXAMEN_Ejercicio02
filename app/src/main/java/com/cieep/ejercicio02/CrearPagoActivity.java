package com.cieep.ejercicio02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cieep.ejercicio02.databinding.ActivityCrearPagoBinding;
import com.cieep.ejercicio02.modelos.PagoHora;

public class CrearPagoActivity extends AppCompatActivity {

    private ActivityCrearPagoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearPagoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matricula = binding.txtMatriculaCrear.getText().toString();
                String importeS = binding.txtImporteCrear.getText().toString();
                String tiempoS = binding.txtTiempoCrear.getText().toString();

                if (!matricula.isEmpty() && !importeS.isEmpty() && !tiempoS.isEmpty()) {
                    PagoHora ph = new PagoHora(matricula, Float.parseFloat(tiempoS), Float.parseFloat(importeS));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PAGO", ph);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}