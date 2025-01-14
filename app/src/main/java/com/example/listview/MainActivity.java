package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lista;
    private TextView texto;
    private ArrayList<Encapsulador> datos;
    private RadioButton radioButtonSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lista = findViewById(R.id.lista);
        texto = findViewById(R.id.texto);


        datos = new ArrayList<>();
        datos.add(new Encapsulador(R.drawable.imagen1, "los chicos", "los chicos", false));
        datos.add(new Encapsulador(R.drawable.imagen2, "Rigby", "Rigby", false));
        datos.add(new Encapsulador(R.drawable.imagen3, "Un fondo", "Un fondo", false));
        datos.add(new Encapsulador(R.drawable.imagen4, "Foto con David", "Foto con David", false));
        datos.add(new Encapsulador(R.drawable.imagen5, "bauletti balon de oro", "bauletti balon de oro", false));
        datos.add(new Encapsulador(R.drawable.imagen6, "Regular Show", "Regular Show", false));
        datos.add(new Encapsulador(R.drawable.imagen7, "Perro salchicha", "Perro salchicha", false));


        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {

                    TextView textoTitulo = view.findViewById(R.id.texto_titulo);
                    TextView textoContenido = view.findViewById(R.id.texto_datos);
                    ImageView imagen = view.findViewById(R.id.imagen);
                    RadioButton radioButton = view.findViewById(R.id.boton);

                    Encapsulador item = (Encapsulador) entrada;


                    textoTitulo.setText(item.getTextoTitulo());
                    textoContenido.setText(item.getTextoContenido());
                    imagen.setImageResource(item.getIdImagen());
                    radioButton.setChecked(item.isSeleccionado());


                    radioButton.setOnClickListener(v -> {

                        // Si ya hay un RadioButton seleccionado, desmarcarlo
                        if (radioButtonSeleccionado != null) {
                            radioButtonSeleccionado.setChecked(false);
                        }

                        // Actualizar la imagen al seleccionar este RadioButton
                        radioButtonSeleccionado = radioButton;
                        item.setSeleccionado(true);

                        // Cambiar la imagen cuando se marca el RadioButton
                        if (radioButton.isChecked()) {
                            imagen.setImageResource(R.drawable.imagen_seleccionada); // Cambia esta imagen por la que desees
                        }

                        // Actualizar el texto
                        texto.setText("Seleccionado: " + item.getTextoTitulo());

                    });
                }
            }
        });
    }

    // Clase interna Encapsulador (POJO) para los datos
    public class Encapsulador {
        private int idImagen;
        private String textoTitulo;
        private String textoContenido;
        private boolean seleccionado;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean seleccionado) {
            this.idImagen = idImagen;
            this.textoTitulo = textoTitulo;
            this.textoContenido = textoContenido;
            this.seleccionado = seleccionado;
        }

        public int getIdImagen() {
            return idImagen;
        }

        public String getTextoTitulo() {
            return textoTitulo;
        }

        public String getTextoContenido() {
            return textoContenido;
        }

        public boolean isSeleccionado() {
            return seleccionado;
        }

        public void setSeleccionado(boolean seleccionado) {
            this.seleccionado = seleccionado;
        }
    }
}
