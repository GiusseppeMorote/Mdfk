package com.cibertec.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lvPersonas;
    private Button btnNuevo;
    private PersonaAdapter adaptadorPersonaListView;
    private final int NUEVO = 0;
    private final int EDITAR = 1;


    private final View.OnClickListener btnNuevoClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(intent, NUEVO);
        }
    };

    private AdapterView.OnItemClickListener lvPersonasOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            Persona persona = adaptadorPersonaListView.getItem(position);

            intent.putExtra("id", persona.getId());
            intent.putExtra("nombre", persona.getNombre());
            intent.putExtra("apellido", persona.getApellido());
            intent.putExtra("telefono",persona.getTelefono());
            intent.putExtra("direccion", persona.getDireccion());
            intent.putExtra("edad", persona.getEdad());

            startActivityForResult(intent, EDITAR);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPersonas = (ListView) findViewById(R.id.lvPersonas);
        btnNuevo = (Button) findViewById(R.id.btnNuevo);
        adaptadorPersonaListView = new PersonaAdapter(MainActivity.this);

        btnNuevo.setOnClickListener(btnNuevoClick);
        lvPersonas.setOnItemClickListener(lvPersonasOnItemClickListener);
        lvPersonas.setAdapter(adaptadorPersonaListView);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == NUEVO) {
            Persona persona = new Persona();
            persona.setId(java.util.UUID.randomUUID().toString());
            persona.setNombre(data.getStringExtra("nombre"));
            persona.setApellido(data.getStringExtra("apellido"));
            persona.setTelefono(data.getStringExtra("telefono"));
            persona.setDireccion(data.getStringExtra("direccion"));
            persona.setEdad(data.getIntExtra("edad", 0));
            adaptadorPersonaListView.add(persona);
            adaptadorPersonaListView.notifyDataSetChanged();
        } else if (requestCode == EDITAR) {
            int subAccion = data.getIntExtra("subAccion", Main2Activity.EDITAR);
            if (subAccion == Main2Activity.EDITAR) {
                for (int i = 0; i < adaptadorPersonaListView.getCount(); i++) {
                    if (adaptadorPersonaListView.getItem(i).getId().equals(data.getStringExtra("id"))) {
                        Persona persona = adaptadorPersonaListView.getItem(i);
                        persona.setNombre(data.getStringExtra("nombre"));
                        persona.setApellido(data.getStringExtra("apellido"));
                        persona.setTelefono(data.getStringExtra("telefono"));
                        persona.setDireccion(data.getStringExtra("direccion"));
                        persona.setEdad(data.getIntExtra("edad", 0));

                        adaptadorPersonaListView.notifyDataSetChanged();
                    }
                }
            }
        }
    }


}
