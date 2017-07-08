package com.cibertec.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad, etDireccion,etTelefono;
    private Button btnGrabar;
    public static final int EDITAR = 0;
    private String id;

    private View.OnClickListener btnGrabarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!validamos()) {
                Toast.makeText(Main2Activity.this, "Por Favor Ingresar Todos Los Campos", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("apellido", etApellido.getText().toString());
            intent.putExtra("telefono",etTelefono.getText().toString());
            intent.putExtra("direccion", etDireccion.getText().toString());
            intent.putExtra("edad", Integer.parseInt(etEdad.getText().toString()));
            intent.putExtra("subAccion", EDITAR);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        btnGrabar = (Button) findViewById(R.id.btnGrabar);

        btnGrabar.setOnClickListener(btnGrabarOnClickListener);

        Intent intent = getIntent();

        etNombre.setText(intent.getStringExtra("nombre"));
        etApellido.setText(intent.getStringExtra("apellido"));
        etTelefono.setText(intent.getStringExtra("telefono"));
        etDireccion.setText(intent.getStringExtra("direccion"));
        id = intent.getStringExtra("id");
        int edad = intent.getIntExtra("edad", 0);
        etEdad.setText("" + edad);

    }


    private boolean validamos() {

        boolean validacion = true;

        if (TextUtils.isEmpty(etNombre.getText())) {
            validacion = false;
        }
        if (TextUtils.isEmpty(etApellido.getText())) {
            validacion = false;
        }
        if (TextUtils.isEmpty(etEdad.getText())) {
            validacion = false;
        }
        if (TextUtils.isEmpty(etDireccion.getText())) {
            validacion = false;
        }
        if(TextUtils.isEmpty(etTelefono.getText())){
            validacion = false;
        }

        return validacion;

    }




}
