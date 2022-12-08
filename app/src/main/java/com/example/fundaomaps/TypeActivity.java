package com.example.fundaomaps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Type;

public class TypeActivity extends AppCompatActivity {
    private static final int EDIT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        final LatLng latLong = (LatLng) getIntent().getParcelableExtra("location");
        // TODO implementar utilitarios depois
        Button alimentacaoButton = findViewById(R.id.alimentacao_click);
        Button utilitariosButton = findViewById(R.id.utilitarios_click);

        alimentacaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alimentacaoIntent = new Intent(TypeActivity.this, AlimentacaoActivity.class);
                alimentacaoIntent.putExtra("location", latLong);
                TypeActivity.this.startActivityForResult(alimentacaoIntent, EDIT_REQUEST);
            }
        });
        utilitariosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent utilitariosIntent = new Intent(TypeActivity.this, AlimentacaoActivity.class);
                utilitariosIntent.putExtra("location", latLong);
                TypeActivity.this.startActivityForResult(utilitariosIntent, EDIT_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (EDIT_REQUEST) : {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);

                if (resultCode == Activity.RESULT_OK) {
                    MarkerOptions markerOptions = data.getParcelableExtra("marker");
                    String nome = data.getStringExtra("nome");
                    String endereco = data.getStringExtra("endereco");


                    resultIntent.putExtra("marker", markerOptions);
                    resultIntent.putExtra("nome", nome);
                    resultIntent.putExtra("endereco", endereco);
                }

                finish();
                break;
            }
        }
    }
}