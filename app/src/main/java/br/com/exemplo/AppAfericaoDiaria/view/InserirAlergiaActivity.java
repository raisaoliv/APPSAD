package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.exemplo.AppAfericaoDiaria.Controller.MedicamentoController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.api.AppUtil;
import br.com.exemplo.AppAfericaoDiaria.model.Medicamento;

public class InserirAlergiaActivity extends AppCompatActivity {

    //Alergia inserirAlergia;
    private SharedPreferences preferences;

    EditText editsubstancia, editgraudeseveridade, editobservacoes;
    Button btnSalvarContinuar, btnCancelar;
    boolean isFormularioOK;
    int idLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alergia);

        idLogado = getIntent().getIntExtra("id", 0);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {
                    /*inserirAlergia.setsubstancia(editsubstancia.getText().toString());

                    int graudeseveridade = Integer.parseInt(editgraudeseveridade.getText().toString());
                    inserirAlergia.setgraudeseveridade(graudeseveridade);

                    inserirAlergia.setObservacoes(editobservacoes.getText().toString());

                    inserirAlergia.setIdCliente(idLogado);*/

                    //Salvar Alergia
                    /*MedicamentoController medicamentoController = new MedicamentoController(inserirAlergiaActivity.this);
                    medicamentoController.inserir(inserirAlergia);*/

                    //TOAST AQUI

                    finish();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(InserirAlergiaActivity.this);
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
        /*editsubstancia = findViewById(R.id.editsubstancia);
        editgraudeseveridade = findViewById(R.id.editgraudeseveridade);
        editobservacoes = findViewById(R.id.editobservacoes);
        editobservacoes = findViewById(R.id.editobservacao);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);
        isFormularioOK = false;

        inserirAlergia = new Alergia();*/
    }

    private boolean validarFormulario() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editsubstancia.getText().toString())) {
            editsubstancia.setError("Insira o tipo de substância a que é alergico!");
            editsubstancia.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editgraudeseveridade.getText().toString())) {
            editgraudeseveridade.setError("Insira o grau de severidade da sua alergia!");
            editgraudeseveridade.requestFocus();
            retorno = false;
        }

        //if (TextUtils.isEmpty(editunidadedemedida.getText().toString())) {
           // editunidadedemedida.setError("Insira as medidas do medicamento(comprimido, mililitro, gotas)!");
          //  editunidadedemedida.requestFocus();
         //   retorno = false;
        //}
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

        dados.putString("grau de severidade", editgraudeseveridade.getText().toString());
        dados.putInt("substância", Integer.parseInt(editsubstancia.getText().toString()));
        dados.putString("Obervações", editobservacoes.getText().toString());
        dados.apply();
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

    }
}