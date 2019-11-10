package com.example.guia7sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_NICK = "NICK";
    public static final String KEY_SCORE = "SCORE";
    public static final String KEY_INTENTOS = "INTENTOS";
    public static final String KEY_RANDOM = "RAMDOM";
    public static final String NAME_FILE = "Configuracion";
    public static SharedPreferences configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrearJugador();
    }

    public void OnClickJugar(View v){
        Intent in = new Intent(this, Adivina.class);
        startActivity(in);
    }

    public void OnClickPuntajes(View v){
        Intent in = new Intent(this, Puntaje.class);
        startActivity(in);
    }

    public void OnClickVerRespuesta(View v){
        Intent in = new Intent(this, Respuesta.class);
        startActivity(in);
    }

    public void OnClickDatos(View v){
        Intent in = new Intent(this, Datos.class);
        startActivity(in);
    }

    private void CrearJugador(){
        if(this.configuracion == null){
            this.configuracion = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
            int random = (int) ((Math.random() * 10) + 1);
            SharedPreferences.Editor editorConfiguracion = this.configuracion.edit();
            editorConfiguracion.putString(KEY_NICK, "Player 1");
            editorConfiguracion.putInt(KEY_SCORE, 0);
            editorConfiguracion.putInt(KEY_INTENTOS, 0);
            editorConfiguracion.putInt(KEY_RANDOM, random);
            editorConfiguracion.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ItemConfiguracion:{
                Intent in = new Intent(this, Configuracion.class);
                startActivity(in);
            }break;
        }
        return super.onOptionsItemSelected(item);
    }
}
