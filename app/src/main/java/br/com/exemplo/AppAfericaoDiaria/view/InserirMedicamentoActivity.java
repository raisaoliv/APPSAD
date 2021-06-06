package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import br.com.exemplo.AppAfericaoDiaria.Controller.MedicamentoController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.api.AppUtil;
import br.com.exemplo.AppAfericaoDiaria.model.Medicamento;

public class InserirMedicamentoActivity extends AppCompatActivity {

    Medicamento inserirMedicamento;
    private SharedPreferences preferences;

    CheckBox cklembrete;
    EditText editnomemedicamento, editquantidade, editunidadedemedida, editobservacoes, editfrequencia;
    Button btnSalvarContinuar, btnCancelar;
    boolean isFormularioOK;
    int idLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_medicamento);

        idLogado = getIntent().getIntExtra("id", 0);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {
                    inserirMedicamento.setNomemedicamento(editnomemedicamento.getText().toString());

                    int quantidade = Integer.parseInt(editquantidade.getText().toString());
                    inserirMedicamento.setQuantidade(quantidade);

                    inserirMedicamento.setunidadedemedida(editunidadedemedida.getText().toString());

                    int frequencia = Integer.parseInt(editfrequencia.getText().toString());
                    inserirMedicamento.setFrequencia(frequencia);
                    inserirMedicamento.setObservacoes(editobservacoes.getText().toString());

                    inserirMedicamento.setIdCliente(idLogado);

                    //Salvar Medicamento
                    MedicamentoController medicamentoController = new MedicamentoController(InserirMedicamentoActivity.this);
                    medicamentoController.inserir(inserirMedicamento);

                    //TOAST AQUI

                    finish();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(InserirMedicamentoActivity.this);
                alerta.setTitle("Confirme o Cancelamento");
                alerta.setIcon(R.mipmap.ic_launcher_round);
                alerta.setMessage("Deseja realmente Cancelar?");
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alerta.setNegativeButton("Não", null);
                alerta.show();
            }
        });

    }

    private void initFormulario() {
        editnomemedicamento = findViewById(R.id.editnomemedicamento);
        editfrequencia = findViewById(R.id.editfrequencia);
        editquantidade = findViewById(R.id.editquantidade);
        editunidadedemedida = findViewById(R.id.editunidadedemedida);
        editobservacoes = findViewById(R.id.editobservacao);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);
//        cklembrete = findViewById(R.id.cklembrete);
//        cklembrete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//            }
//        });
        isFormularioOK = false;

        inserirMedicamento = new Medicamento();
    }

    private boolean validarFormulario() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editnomemedicamento.getText().toString())) {
            editnomemedicamento.setError("Insira o nome do medicamneto!");
            editnomemedicamento.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editfrequencia.getText().toString())) {
            editfrequencia.setError("Insira a frequência que é tomado!");
            editfrequencia.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editquantidade.getText().toString())) {
            editquantidade.setError("Insira a quantidade que deve ser ingerida!");
            editquantidade.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editunidadedemedida.getText().toString())) {
            editunidadedemedida.setError("Insira as medidas do medicamento(comprimido, mililitro, gotas)!");
            editunidadedemedida.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editobservacoes.getText().toString())) {
            editobservacoes.setError("Insira observações sobre o medicamento!");
            editobservacoes.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("nomemedicamento", editnomemedicamento.getText().toString());
        dados.putInt("quantidade", Integer.parseInt(editquantidade.getText().toString()));
        dados.putInt("frequencia", Integer.parseInt(editfrequencia.getText().toString()));
        dados.putString("unidade de medida", editunidadedemedida.getText().toString());
        dados.putString("Obervações", editobservacoes.getText().toString());
        dados.apply();
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

    }
}