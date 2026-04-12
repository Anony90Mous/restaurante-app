package com.example.restaurante;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout_1, layout_2, layout_3, layout_4, layout_5, layout_6, layout_7, layout_8, layout_9, layout_selecionado;
    ArrayList<LinearLayout> layouts_reservados = new ArrayList<>();
    TextView textoMesa1, textoMesa2, textoMesa3, textoMesa4, textoMesa5, textoMesa6, textoMesa7, textoMesa8, textoMesa9, texto_selecionado;
    Button btnReserva, limpar, tirarReserva;


    void salvarReservas() {
        SharedPreferences prf = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editar = prf.edit();

        StringBuilder dados = new StringBuilder();

        for (LinearLayout layout : layouts_reservados) {
            int numeroReversa = (int) layout.getTag();
            dados.append(numeroReversa).append(",");
        }

        editar.putString("mesas_reservadas", dados.toString());
        editar.apply();
    }

    void carregarReservas() {
        SharedPreferences prf = PreferenceManager.getDefaultSharedPreferences(this);

        String dados = prf.getString("mesas_reservadas", "");

        if (!dados.isEmpty()) {
            String[] mesas = dados.split(",");

            for (String m : mesas) {
                if (!m.isEmpty()) {
                    int numero = Integer.parseInt(m);

                    LinearLayout layout = null;
                    TextView texto = null;

                    switch (numero) {
                        case 1:
                            layout = layout_1;
                            texto = textoMesa1;
                            break;
                        case 2:
                            layout = layout_2;
                            texto = textoMesa2;
                            break;
                        case 3:
                            layout = layout_3;
                            texto = textoMesa3;
                            break;
                        case 4:
                            layout = layout_4;
                            texto = textoMesa4;
                            break;
                        case 5:
                            layout = layout_5;
                            texto = textoMesa5;
                            break;
                        case 6:
                            layout = layout_6;
                            texto = textoMesa6;
                            break;
                        case 7:
                            layout = layout_7;
                            texto = textoMesa7;
                            break;
                        case 8:
                            layout = layout_8;
                            texto = textoMesa8;
                            break;
                        case 9:
                            layout = layout_9;
                            texto = textoMesa9;
                            break;
                    }

                    if (layout != null) {
                        layout.setTag(numero);
                        layout.setBackgroundResource(R.drawable.bg_lyt_reservado);
                        texto.setTextSize(16);
                        texto.setText(R.string.reservado);
                        layouts_reservados.add(layout);
                    }
                }
            }
        }
    }


    void resetMesa(LinearLayout layout, TextView texto, int textoPadrao) {
        if (layouts_reservados.contains(layout)) {
            layout.setBackgroundResource(R.drawable.bg_lyt_reservado);
            texto.setText(R.string.reservado);
        } else {
            layout.setBackgroundResource(R.drawable.bg_lyt);
            texto.setTextSize(40);
            texto.setText(textoPadrao);
        }
    }

    void tirarReserva(LinearLayout layout, TextView text, int textDefault) {
        layouts_reservados.remove(layout);
        layout.setBackgroundResource(R.drawable.bg_lyt);
        text.setTextSize(40);
        text.setText(textDefault);
    }

    void limparCores() {
        resetMesa(layout_1, textoMesa1, R.string.q1);
        resetMesa(layout_2, textoMesa2, R.string.q2);
        resetMesa(layout_3, textoMesa3, R.string.q3);
        resetMesa(layout_4, textoMesa4, R.string.q4);
        resetMesa(layout_5, textoMesa5, R.string.q5);
        resetMesa(layout_6, textoMesa6, R.string.q6);
        resetMesa(layout_7, textoMesa7, R.string.q7);
        resetMesa(layout_8, textoMesa8, R.string.q8);
        resetMesa(layout_9, textoMesa9, R.string.q9);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        layout_1 = findViewById(R.id.layout_1);
        textoMesa1 = findViewById(R.id.text_table_1);

        layout_2 = findViewById(R.id.layout_2);
        textoMesa2 = findViewById(R.id.text_table_2);

        layout_3 = findViewById(R.id.layout_3);
        textoMesa3 = findViewById(R.id.text_table_3);

        layout_4 = findViewById(R.id.layout_4);
        textoMesa4 = findViewById(R.id.text_table_4);

        layout_5 = findViewById(R.id.layout_5);
        textoMesa5 = findViewById(R.id.text_table_5);

        layout_6 = findViewById(R.id.layout_6);
        textoMesa6 = findViewById(R.id.text_table_6);

        layout_7 = findViewById(R.id.layout_7);
        textoMesa7 = findViewById(R.id.text_table_7);

        layout_8 = findViewById(R.id.layout_8);
        textoMesa8 = findViewById(R.id.text_table_8);

        layout_9 = findViewById(R.id.layout_9);
        textoMesa9 = findViewById(R.id.text_table_9);

        btnReserva = findViewById(R.id.reservar);

        limpar = findViewById(R.id.limpar);

        tirarReserva = findViewById(R.id.tirarReserva);

        layout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_1.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_1;
                layout_selecionado.setTag(1);
                texto_selecionado = textoMesa1;
            }
        });

        layout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_2.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_2;
                layout_selecionado.setTag(2);
                texto_selecionado = textoMesa2;
            }
        });

        layout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_3.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_3;
                layout_selecionado.setTag(3);
                texto_selecionado = textoMesa3;
            }
        });

        layout_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_4.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_4;
                layout_selecionado.setTag(4);
                texto_selecionado = textoMesa4;
            }
        });
        layout_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_5.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_5;
                layout_selecionado.setTag(5);
                texto_selecionado = textoMesa5;
            }
        });
        layout_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_6.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_6;
                layout_selecionado.setTag(6);
                texto_selecionado = textoMesa6;
            }
        });
        layout_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_7.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_7;
                layout_selecionado.setTag(7);
                texto_selecionado = textoMesa7;
            }
        });
        layout_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_8.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_8;
                layout_selecionado.setTag(8);
                texto_selecionado = textoMesa8;
            }
        });
        layout_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCores();
                layout_9.setBackgroundResource(R.drawable.bg_lyt_selected);
                layout_selecionado = layout_9;
                layout_selecionado.setTag(9);
                texto_selecionado = textoMesa9;
            }
        });

        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (layout_selecionado == null) {
                    Toast.makeText(MainActivity.this, "Selecione uma mesa!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (layouts_reservados.contains(layout_selecionado)) {
                    Toast.makeText(MainActivity.this, "Esta mesa ja foi reservada!", Toast.LENGTH_SHORT).show();
                    return;
                }

                layout_selecionado.setBackgroundResource(R.drawable.bg_lyt_reservado);
                texto_selecionado.setTextSize(16);
                texto_selecionado.setText(R.string.reservado);
                layouts_reservados.add(layout_selecionado);
                salvarReservas();
            }
        });

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layouts_reservados.clear();
                salvarReservas();
                limparCores();
            }
        });

        tirarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout_selecionado == null) {
                    Toast.makeText(MainActivity.this, "Selecione uma mesa reservada!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int numero = (int) layout_selecionado.getTag();
                int textoDefault = getResources().getIdentifier(
                        "q" + numero,
                        "string",
                        getPackageName()
                );
                tirarReserva(layout_selecionado, texto_selecionado, textoDefault);
                salvarReservas();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        carregarReservas();
    }
}