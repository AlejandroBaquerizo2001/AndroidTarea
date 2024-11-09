package com.example.transportejuratempest;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterFormActivity extends AppCompatActivity {

    private static final String TAG = "RegisterFromActivity";

    private EditText txtCedula, txtNombre, txtApellido, txtEdad, txtFechaNacimiento;
    private Spinner spNacionalidad, spGenero;
    private RadioButton rbtnSoltero, rbtnCasado, rbtnLibre;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializamos los campos establecidos
        txtCedula = findViewById(R.id.txtcedula);
        txtNombre = findViewById(R.id.txtnombre);
        txtApellido = findViewById(R.id.txtapellido);
        txtEdad = findViewById(R.id.txtedad);
        txtFechaNacimiento = findViewById(R.id.txtfechanacimiento);
        spNacionalidad = findViewById(R.id.spnacionalidad);
        spGenero = findViewById(R.id.spgenero);
        rbtnSoltero = findViewById(R.id.rbtnsoltero);
        rbtnCasado = findViewById(R.id.rbtncasado);
        rbtnLibre = findViewById(R.id.rbtnlibre);
        ratingBar = findViewById(R.id.ratingBar);

        Button btnRegistrar = findViewById(R.id.btnregistrar);
        Button btnBorrar =  findViewById(R.id.btnborrar);
        Button btnCancelar = findViewById(R.id.btncancelar);

        //Spinner
        llenarSpinners();

        //Configuracion del boton registrar
        btnRegistrar.setOnClickListener(v -> registrarDatos());

        Log.d(TAG, "Metodo registrarDatos() llamado.");

        //Boton Borrar
        btnBorrar.setOnClickListener(v -> borrarDatos());
    }

    private void llenarSpinners (){

        // Datos para el Spinner de Nacionalidad
        String[] nacionalidades = {"Ecuatoriano", "Colombiano", "Peruano", "Chileno", "Argentino"};
        ArrayAdapter<String> adapterNacionalidad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nacionalidades);
        adapterNacionalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNacionalidad.setAdapter(adapterNacionalidad);

        // Datos para el Spinner de Género
        String[] generos = {"Masculino", "Femenino", "No Binario"};
        ArrayAdapter<String> adapterGenero = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapterGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenero.setAdapter(adapterGenero);
    }

    //Boton cancelar
    public void cancelar (View v){
        Intent intentCancelar = new Intent(v.getContext(), LoginActivity.class);
        startActivity(intentCancelar);
    }


    private void registrarDatos() {
        try {
            // Validación de datos
            if (txtCedula.getText().toString().isEmpty() ||
                    txtNombre.getText().toString().isEmpty() ||
                    txtApellido.getText().toString().isEmpty() ||
                    txtEdad.getText().toString().isEmpty() ||
                    txtFechaNacimiento.getText().toString().isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtenemos los datos
            String cedula = txtCedula.getText().toString();
            String nombre = txtNombre.getText().toString();
            String apellido = txtApellido.getText().toString();
            String edad = txtEdad.getText().toString();
            String fechaNacimiento = txtFechaNacimiento.getText().toString();
            String nacionalidad = spNacionalidad.getSelectedItem().toString();
            String genero = spGenero.getSelectedItem().toString();
            String estadoCivil = rbtnSoltero.isChecked() ? "Soltero" : rbtnCasado.isChecked() ? "Casado" : "Unión libre";
            float nivelIngles = ratingBar.getRating();

            // Mostramos los datos en el log
            Log.d(TAG, "Datos Registrados:");
            // Imprimir los datos...

            // Mostrar mensaje de éxito
            Toast.makeText(this, "Datos ingresados correctamente.", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "Error al registrar datos: " + e.getMessage());
            Toast.makeText(this, "Ocurrió un error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void borrarDatos(){
        // Limpiar los campos
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtFechaNacimiento.setText("");
        spNacionalidad.setSelection(0); // Suponiendo que el primer elemento es el predeterminado
        spGenero.setSelection(0);
        rbtnSoltero.setChecked(false);
        rbtnCasado.setChecked(false);
        rbtnLibre.setChecked(false);
        ratingBar.setRating(0);
    }
}