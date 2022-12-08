package com.example.fundaomaps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

public class AlimentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacao);

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
                resultIntent.putExtra("tipo", "alimentacao");
                setResult(Activity.RESULT_OK, resultIntent);

                // cria um post
                JSONObject json = new JSONObject();
                json.put("name", nome.getText());    
                json.put("class", "FOOD");
                json.put("coordinates", endereco.getText())
                
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                
                try {
                    HttpPost request = new HttpPost("http://geoprocess-argos/api/commercials");
                    StringEntity params = new StringEntity(json.toString());
                    request.addHeader("content-type", "application/json");
                    request.setEntity(params);
                    httpClient.execute(request);
                } catch (Exception ex) {} finally {
                    httpClient.close();
                }
                finish();
            }
        });
    }
}