package com.example.tpn1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculadoraActivity extends AppCompatActivity {

    private TextView tvResultado;
    private double primerNumero = 0;
    private double segundoNumero = 0;
    private String operador = "";
    private boolean operadorSeleccionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvResultado = findViewById(R.id.tvResultado);
        setupButtons();
    }

    private void setupButtons() {
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        Button btnSumar = findViewById(R.id.btnMas);
        Button btnRestar = findViewById(R.id.btnMenos);
        Button btnMulti = findViewById(R.id.btnMulti);
        Button btnDivi = findViewById(R.id.btnDivi);
        Button btnIgual = findViewById(R.id.btnIgual);
        Button btnBorrar = findViewById(R.id.btnBorrar);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                if (operadorSeleccionado) {
                    tvResultado.setText("");
                    operadorSeleccionado = false;
                }
                tvResultado.append(b.getText().toString());
            }
        };

        btn0.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);

        btnSumar.setOnClickListener(view -> selectOperator("+"));
        btnRestar.setOnClickListener(view -> selectOperator("-"));
        btnMulti.setOnClickListener(view -> selectOperator("*"));
        btnDivi.setOnClickListener(view -> selectOperator("/"));

        btnIgual.setOnClickListener(view -> calcularResultado());

        btnBorrar.setOnClickListener(view -> {
            tvResultado.setText("");
            primerNumero = 0;
            segundoNumero = 0;
            operador = "";
            operadorSeleccionado = false;
        });
    }

    private void selectOperator(String selectedOperator) {
        primerNumero = Double.parseDouble(tvResultado.getText().toString());
        operador = selectedOperator;
        operadorSeleccionado = true;
    }

    private void calcularResultado() {
        segundoNumero = Double.parseDouble(tvResultado.getText().toString());
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = primerNumero + segundoNumero;
                break;
            case "-":
                resultado = primerNumero - segundoNumero;
                break;
            case "*":
                resultado = primerNumero * segundoNumero;
                break;
            case "/":
                if (segundoNumero != 0) {
                    resultado = primerNumero / segundoNumero;
                } else {
                    tvResultado.setText("Error");
                    return;
                }
                break;
        }

        tvResultado.setText(String.valueOf(resultado));
    }

    public void Regresar (View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}