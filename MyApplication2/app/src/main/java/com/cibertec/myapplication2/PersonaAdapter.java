package com.cibertec.myapplication2;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alumno801 on 27/05/17.
 */

public class PersonaAdapter extends ArrayAdapter<Persona> {


    public PersonaAdapter(Context context){
        super(context,0,new ArrayList<Persona>());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tvNombre, tvEdad, tvDocumento;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.persona_item, parent, false);
        }
        tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
        tvDocumento = (TextView) convertView.findViewById(R.id.tvDireccion);
        tvEdad = (TextView) convertView.findViewById(R.id.tvEdad);

        Persona persona = getItem(position);
        tvNombre.setText(persona.getNombre() + " " + persona.getApellido());
        tvDocumento.setText(persona.getDireccion());
        tvEdad.setText("" + persona.getEdad());
        return convertView;
    }
}
