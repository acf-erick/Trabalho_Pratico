package br.uniceub.ads.pdm.fitcalcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    //Tela principal
    public TextView txt_principal;
    public Button btn_imc;
    public Button btn_altura;
    public Button btn_peso;

    //Tela imc
    public TextView txt_imc;
    public RadioGroup rg_imc;
    public RadioButton rd_masculino;
    public RadioButton rd_feminino;
    public EditText et_imc1;
    public EditText et_imc2;
    public Button tela_imc_calcular;
    public Button tela_imc_voltar;

    //Tela altura
    public TextView txt_altura;
    public RadioGroup tela_altura;
    public RadioButton tela_altura_rd_masculino;
    public RadioButton tela_altura_rd_feminino;
    public EditText et_imc_3;
    public EditText et_imc_4;
    public Button tela_altura_calcular;
    public Button tela_altura_voltar;

    //Tela peso
    public TextView txt_peso;
    public RadioGroup tela_peso;
    public RadioButton tela_peso_rd_masculino;
    public RadioButton tela_peso_rd_feminino;
    public EditText et_imc_5;
    public Button tela_peso_calcular;
    public Button tela_peso_voltar;

    public Button txt_peso_altura;

    /////
    float altura;
    float peso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        carregarTelaPrincipal();
    }

    private void carregarTelaPrincipal() {
        setContentView(R.layout.tela_principal);

        btn_imc = findViewById(R.id.button1);
        btn_altura = findViewById(R.id.button2);
        btn_peso = findViewById(R.id.button3);

        btn_imc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarregarCalculadoraIMC();
            }
        });

        btn_altura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarregarCalculadoraAlturaIdeal();
            }
        });

        btn_peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarregarCalculadoraPesoIdeal();
            }
        });
    }

    private void CarregarCalculadoraIMC() {
        setContentView(R.layout.calculadora_imc);

        // Mapeamento dos elementos da tela
        txt_imc = findViewById(R.id.textView2);
        rg_imc = findViewById(R.id.rd1);
        rd_masculino = findViewById(R.id.radioButton);
        rd_feminino = findViewById(R.id.radioButton2);
        et_imc1 = findViewById(R.id.editTextText);
        et_imc2 = findViewById(R.id.editTextText2);
        tela_imc_calcular = findViewById(R.id.button1);
        tela_imc_voltar = findViewById(R.id.button2);

        tela_imc_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capturando os valores de altura e peso
                String alturaStr = et_imc1.getText().toString();
                String pesoStr = et_imc2.getText().toString();


                // Convertendo os valores de texto para números
                float altura = Float.parseFloat(alturaStr);
                float peso = Float.parseFloat(pesoStr);

                // Calculando o IMC
                float imc = peso / (altura * altura);

                // Verificando se os campos estão vazios
                if (alturaStr.isEmpty() || pesoStr.isEmpty()) {
                    txt_imc.setText("Porfavor, insira altura e peso.");
                    return;
                }

                // Capturando o sexo selecionado no RadioGroup
                int sexoSelecionado = rg_imc.getCheckedRadioButtonId();

                // Verificando o resultado do IMC com base no sexo
                if (sexoSelecionado == R.id.radioButton) {  // Masculino
                    if (imc < 18.5) {
                        txt_imc.setText(String.format("IMC: %.2f - Abaixo do peso", imc));
                    } else if (imc >= 18.5 && imc <= 24.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Peso normal", imc));
                    } else if (imc >= 25.0 && imc <= 29.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Pré-obesidade", imc));
                    } else if (imc >= 30.0 && imc <= 34.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Obesidade Grau 1", imc));
                    } else if (imc >= 35.0 && imc <= 39.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Obesidade Grau 2", imc));
                    } else {
                        txt_imc.setText(String.format("IMC: %.2f - Obesidade Grau 3", imc));
                    }
                } else if (sexoSelecionado == R.id.radioButton2) {  // Feminino
                    if (imc < 18.5) {
                        txt_imc.setText(String.format("IMC: %.2f - Abaixo do peso", imc));
                    } else if (imc >= 18.5 && imc <= 26.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Peso normal", imc));
                    } else if (imc >= 27.0 && imc <= 32.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Pré-obesidade", imc));
                    } else if (imc >= 33.0 && imc <= 37.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Obesidade Grau 1", imc));
                    } else if (imc >= 38.0 && imc <= 44.9) {
                        txt_imc.setText(String.format("IMC: %.2f - Obesidade Grau 2", imc));
                    } else {
                        txt_imc.setText(String.format("IMC: %.2f - Obesidade Grau 3", imc));
                    }
                } else {
                    txt_imc.setText("Por favor, selecione o sexo.");
                }

            }
        });

        // Ação para o botão voltar
        tela_imc_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarTelaPrincipal();  // Voltar à tela principal
            }
        });


    }

    private void CarregarCalculadoraPesoIdeal() {
        setContentView(R.layout.calculadora_peso_ideal);

        // Mapeamento dos elementos da tela
        txt_peso = findViewById(R.id.textView3);
        tela_peso = findViewById(R.id.rd2);
        tela_peso_rd_masculino = findViewById(R.id.radioButton3);
        tela_peso_rd_feminino = findViewById(R.id.radioButton4);
        tela_peso_calcular = findViewById(R.id.button5);
        tela_peso_voltar = findViewById(R.id.button6);



        // Ação de calcular o peso ideal
        tela_peso_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alturaStr = et_altura.getText().toString().trim();

                // Verificando se o campo de altura está vazio
                if (alturaStr.isEmpty()) {
                    txt_peso_resultado.setText("Por favor, insira a altura.");
                    return;
                }

                try {
                    // Convertendo a altura para float
                    float altura = Float.parseFloat(alturaStr);

                    // Verificando se a altura é válida
                    if (altura <= 0) {
                        txt_peso_resultado.setText("Por favor, insira uma altura válida.");
                        return;
                    }

                    // Definindo o IMC ideal de acordo com o sexo selecionado
                    float imcIdeal;
                    int sexoSelecionado = rg_sexo.getCheckedRadioButtonId();

                    if (sexoSelecionado == R.id.radioButtonMasculino) {
                        imcIdeal = 21.7f;  // IMC ideal para homens
                    } else if (sexoSelecionado == R.id.radioButtonFeminino) {
                        imcIdeal = 22.7f;  // IMC ideal para mulheres
                    } else {
                        txt_peso_resultado.setText("Por favor, selecione o sexo.");
                        return;
                    }

                    // Calculando o peso ideal usando a fórmula PESO_IDEAL = IMC_IDEAL * (ALTURA ^ 2)
                    float pesoIdeal = imcIdeal * (altura * altura);

                    // Exibindo o resultado
                    txt_peso_resultado.setText(String.format("Peso ideal: %.2f kg", pesoIdeal));

                } catch (NumberFormatException e) {
                    txt_peso_resultado.setText("Por favor, insira um valor numérico válido.");
                }
            }
        });

        // Ação do botão voltar
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarTelaPrincipal();  // Voltar à tela principal
            }
        });
    }


    }

    private void CarregarCalculadoraAlturaIdeal() {

    }

}
