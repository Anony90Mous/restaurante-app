package com.example.restaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText inputEmail, inputSenha;
    Button btnlogin;
    CheckBox lembrarSenhaCheckBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.inputEmail);
        inputSenha = findViewById(R.id.inputSenha);
        lembrarSenhaCheckBox = findViewById(R.id.checkboxLembrarSenha);
        btnlogin = findViewById(R.id.btnLogin);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        SharedPreferences.Editor editar = preferences.edit();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = inputEmail.getText().toString();
                String Senha = inputSenha.getText().toString();
                if (lembrarSenhaCheckBox.isChecked()) {
                    editar.putString("Email", Email);
                    editar.putString("Senha", Senha);
                    editar.putBoolean("checked", true);
                    editar.apply();
                }
                if (!lembrarSenhaCheckBox.isChecked()) {
                    editar.remove("Email");
                    editar.remove("Senha");
                    editar.putBoolean("checked", false);
                    editar.apply();
                }
                if (Email.equals("admin@gmail.com") && Senha.equals("admin123")) {
                    Intent it = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(it);
                } else {
                    Toast.makeText(LoginActivity.this, "Email ou senha incorreta!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        inputEmail.setText(preferences.getString("Email", ""));
        inputSenha.setText(preferences.getString("Senha", ""));
        lembrarSenhaCheckBox.setChecked(preferences.getBoolean("checked", false));
    }
}
