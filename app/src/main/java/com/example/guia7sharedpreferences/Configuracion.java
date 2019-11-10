package com.example.guia7sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import static com.example.guia7sharedpreferences.MainActivity.KEY_NICK;
import static com.example.guia7sharedpreferences.MainActivity.configuracion;

public class Configuracion extends AppCompatActivity {

    MaterialEditText edtnick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.titleConfig);

        edtnick = findViewById(R.id.edtNick);
        CargarNombre();
    }

    private void GuardarNick(String nick){
        if(configuracion != null){
            //Obtenemos el editor
            SharedPreferences.Editor editorConfiguracion = configuracion.edit();

            editorConfiguracion.putString(KEY_NICK, nick);
            editorConfiguracion.commit();
            new AlertDialog.Builder(this).setTitle("¡Éxito!").setMessage("¡Nick guardado con éxito!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
        }
    }

    private boolean Evaluar(String n){
        boolean re = true;

        if(n.length() <= 0){ re = false;}
        return re;
    }

    private void CargarNombre(){
        String n = configuracion.getString(KEY_NICK, "");
        edtnick.setText(n);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.configuracion_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ItemGuardarConfig:{
                String nick = edtnick.getText().toString();
                if(Evaluar(nick)){
                    GuardarNick(nick);
                } else{
                    Toast.makeText(this, "Complete el campo", Toast.LENGTH_SHORT).show();
                }
            }break;
            case android.R.id.home:{
                this.finish();
            }break;
        }
        return super.onOptionsItemSelected(item);
    }
}
