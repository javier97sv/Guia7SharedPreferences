package com.example.guia7sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.guia7sharedpreferences.MainActivity.KEY_NICK;
import static com.example.guia7sharedpreferences.MainActivity.KEY_SCORE;
import static com.example.guia7sharedpreferences.MainActivity.configuracion;

public class Puntaje extends AppCompatActivity {

    TextView txvNickPuntaje, txvPuntaPuntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.titlePuntaje);

        txvNickPuntaje = findViewById(R.id.txvNickPuntaje);
        txvPuntaPuntaje = findViewById(R.id.txvPuntaPuntaje);
        getDatos();
    }

    private void getDatos(){
        txvNickPuntaje.setText(configuracion.getString(KEY_NICK, ""));
        txvPuntaPuntaje.setText(Integer.toString(configuracion.getInt(KEY_SCORE, 0)));
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
