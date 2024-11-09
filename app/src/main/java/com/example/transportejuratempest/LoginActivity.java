package com.example.transportejuratempest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, clave;
    CheckBox recordarClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        recoveryCredenciales();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void registrarse(View v)
    {
        //Toast.makeText(v.getContext(), "Ha presionado el boton Registrarse",Toast.LENGTH_LONG).show();

        Intent intentRegistro = new Intent(v.getContext(), RegisterFormActivity.class);
        startActivity(intentRegistro);
    }

    private void guardarSharedPreference(String user, String clave)
    {
        SharedPreferences shpLogin = getSharedPreferences("CredencialAcceso", Context.MODE_PRIVATE);
        SharedPreferences.Editor shpEditorLogin = shpLogin.edit();
        shpEditorLogin.putString("user" , user);
        shpEditorLogin.putString("clave", clave);
        shpEditorLogin.commit();
    }

    private void recoveryCredenciales()
    {
        SharedPreferences shpLogin = getSharedPreferences("CredencialAcceso", Context.MODE_PRIVATE);
        String usuarioTmp = shpLogin.getString("user", "");
        String claveTmp = shpLogin.getString("clave", "");

        usuario = findViewById(R.id.txtusuario);
        clave = findViewById(R.id.textclave);

        usuario.setText(usuarioTmp);
        clave.setText(claveTmp);
    }
    public void iniciarSesion(View v)
    {
        usuario = findViewById(R.id.txtusuario);
        clave = findViewById(R.id.textclave);
        recordarClave = findViewById(R.id.chkrecordarclave);
        //Toast.makeText(v.getContext(), "Has Iniciado sesion correctamente: " + usuario.getText(),Toast.LENGTH_LONG).show();

        if (recordarClave.isChecked())
        {
            guardarSharedPreference(String.valueOf(usuario.getText()), String.valueOf(clave.getText()));
        }

        Intent intentPrincipal = new Intent(v.getContext(), MainActivity.class);
        startActivity(intentPrincipal);
    }
}