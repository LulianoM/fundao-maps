package com.example.fundaomaps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

public class UtilitariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilitarios);

        final LatLng latLong = (LatLng) getIntent().getParcelableExtra("location");
        final TextInputEditText nome = (TextInputEditText) findViewById(R.id.input_nomeEstabelecimento);
        final TextInputEditText endereco = (TextInputEditText) findViewById(R.id.input_endereco);
        Button confirmarButton = findViewById(R.id.confirmarButton);

        // ao clicar no botao de salvar, precisamos voltar as informações pro mapa
        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarkerOptions options = new MarkerOptions().position(latLong);

                // se tiver alguma coisa no campo de nome, insiro como titulo do marker
                if (nome.getText() != null) {
                    options.title(nome.getText().toString());
                    options.snippet(endereco.getText().toString());
                }

                // crio um intent e passo as informações inseridas pelo usuário
                Intent resultIntent = new Intent();
                resultIntent.putExtra("marker", options);
                resultIntent.putExtra("nome", nome.getText());
                resultIntent.putExtra("endereco", endereco.getText());
                resultIntent.putExtra("tipo", "utilitarios");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}