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
        datos.add(new Encapsulador(R.drawable.imagen5, "Juan estudiando", "Juan estudiando", false));
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
                        if (radioButton.isChecked()) {

                            item.setSeleccionado(true); // Marcamos el item como seleccionado
                            texto.setText("Seleccionado: " + item.getTextoTitulo());
                        } else {

                            item.setSeleccionado(false); // Desmarcamos el item
                            texto.setText("Deseleccionado: " + item.getTextoTitulo());
                        }


                        if (radioButtonSeleccionado != null && radioButtonSeleccionado != radioButton) {
                            radioButtonSeleccionado.setChecked(false); // Desmarcar el otro RadioButton
                        }
                        radioButtonSeleccionado = radioButton; // Guardamos el RadioButton seleccionado
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
