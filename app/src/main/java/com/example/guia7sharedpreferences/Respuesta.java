package com.example.guia7sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import static com.example.guia7sharedpreferences.MainActivity.KEY_RANDOM;
import static com.example.guia7sharedpreferences.MainActivity.configuracion;

public class Respuesta extends AppCompatActivity {

    TextView txvRespuestaRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.titleRespuesta);

        txvRespuestaRes = findViewById(R.id.txvRespuestaRes);
        getDatos();
    }

    private void getDatos(){
        txvRespuestaRes.setText(Integer.toString(configuracion.getInt(KEY_RANDOM, 0)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                this.finish();
            }break;
        }
        return super.onOptionsItemSelected(item);
    }
}
