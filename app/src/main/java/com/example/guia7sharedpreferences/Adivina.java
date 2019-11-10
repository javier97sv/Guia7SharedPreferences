package com.example.guia7sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.guia7sharedpreferences.MainActivity.KEY_NICK;
import static com.example.guia7sharedpreferences.MainActivity.KEY_SCORE;
import static com.example.guia7sharedpreferences.MainActivity.KEY_INTENTOS;
import static com.example.guia7sharedpreferences.MainActivity.KEY_RANDOM;
import static com.example.guia7sharedpreferences.MainActivity.configuracion;

import com.rengwuxian.materialedittext.MaterialEditText;

public class Adivina extends AppCompatActivity {

    String nick;
    int score, intentos, random, puntajeActual;
    TextView txvNick, txvScore, txvIntentos, txvPista, txvPistaPista;
    MaterialEditText edtNumeroElegido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.titleAdivina);

        IniciarVariables();
        getDatos();
    }

    public void OnClickAdivinar(View v) {
        int numUsuario = 0;
        if (edtNumeroElegido.length() == 0 || Integer.parseInt(edtNumeroElegido.getText().toString()) < 1 ||
                Integer.parseInt(edtNumeroElegido.getText().toString()) > 10) {
            Toast.makeText(this, "Digite un número del 1 al 10", Toast.LENGTH_SHORT).show();
        } else {
            numUsuario = Integer.parseInt(edtNumeroElegido.getText().toString());
            intentos++;
            String ni = Integer.toString(intentos);
            txvIntentos.setText(ni);

            SharedPreferences.Editor editorConfiguracion = configuracion.edit();
            editorConfiguracion.putInt(KEY_INTENTOS, intentos);
            editorConfiguracion.commit();

            if (numUsuario == random) {
                new AlertDialog.Builder(this).setTitle("¡Felicidades!").setMessage("Ha ganado el juego")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                puntajeActual = 10 - intentos;
                                random = (int) ((Math.random() * 10) + 1);

                                SharedPreferences.Editor editorConfiguracionWin = configuracion.edit();
                                editorConfiguracionWin.putInt(KEY_SCORE, score + puntajeActual);
                                editorConfiguracionWin.putInt(KEY_INTENTOS, 0);
                                editorConfiguracionWin.putInt(KEY_RANDOM, random);
                                editorConfiguracionWin.commit();
                                finish();
                            }
                        }).show();

            } else {
                txvPistaPista.setText(R.string.lblPista);
                if (numUsuario < random) {
                    txvPista.setText(R.string.lblMayor);
                    txvPista.setTextColor(getResources().getColor(R.color.mayor));
                } else {
                    txvPista.setText(R.string.lblMenor);
                    txvPista.setTextColor(getResources().getColor(R.color.menor));
                }
            }
        }
    }

    private void getDatos() {
        txvNick.setText(nick);
        txvScore.setText(Integer.toString(score));
        txvIntentos.setText(Integer.toString(intentos));
    }

    private void IniciarVariables(){
        txvNick = findViewById(R.id.txvNick);
        txvScore = findViewById(R.id.txvScore);
        txvIntentos = findViewById(R.id.txvIntentos);
        txvPista = findViewById(R.id.txvPista);
        txvPistaPista = findViewById(R.id.txvPistaPista);
        edtNumeroElegido = findViewById(R.id.edtNumeroElegido);

        nick = configuracion.getString(KEY_NICK, "");
        score = configuracion.getInt(KEY_SCORE, 0);
        intentos = configuracion.getInt(KEY_INTENTOS, 0);
        random = configuracion.getInt(KEY_RANDOM, 0);
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
