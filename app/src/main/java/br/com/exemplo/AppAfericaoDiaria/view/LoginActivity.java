package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.exemplo.AppAfericaoDiaria.Controller.ClienteController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.api.AppUtil;
import br.com.exemplo.AppAfericaoDiaria.model.Cliente;

public class LoginActivity extends AppCompatActivity {

    Cliente cliente;
    private SharedPreferences preferences;
    TextView txtLerPolitica;
    EditText editEmail, editSenha;
    CheckBox chlembrar;
    Button btnAcessar,btnNOVO;
    boolean isFormularioOK, isLembrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFormulario();
    }

    @Override
    protected void onResume() {
        super.onResume();

        buscarSenha();
    }


    private boolean validarFormulario() {
        boolean retorno = true;

        if(TextUtils.isEmpty(editEmail.getText().toString())) {
            editEmail.setError("Insira o email!");
            editEmail.requestFocus();
            retorno = false;
        }

        if(TextUtils.isEmpty(editSenha.getText().toString())) {
            editSenha.setError("Insira o email!");
            editSenha.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    private void initFormulario() {
        //agora so aguardar
        txtLerPolitica = findViewById(R.id.txtLerPolitica);
        editSenha = findViewById(R.id.editSenha);
        editEmail = findViewById(R.id.editEmail);
        chlembrar = findViewById(R.id.chLembrar);
        btnAcessar = findViewById(R.id.btnAcessar);
        btnNOVO = findViewById(R.id.btnNovo);

        //Permite o acesso ao sistema, caso as credenciasi de acesso estejam corretas
        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormularioOK = validarFormulario()) {
                    String email = editEmail.getText().toString();
                    String senha = editSenha.getText().toString();

                    ClienteController clienteController = new ClienteController(LoginActivity.this);
                    int idCliente = clienteController.login(email, senha);
                    if (idCliente > 0) {
                        salvarSenha();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("id", idCliente); //passagem de valor id para mainactivity
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Email ou senha incorretos.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnNOVO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(LoginActivity.this, novoclienteActivity.class);
                startActivity(intent);
            }
        });

        txtLerPolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
                alerta.setTitle("Política de Privacidade & Termos de Uso");
                alerta.setMessage("No SAD, nosso objetivo é oferecer suporte aos nossos usuários em suas jornadas de melhora da saúde e fornecer melhor experiência possível. Aceitando os termos, o usuário está ciente do fornecimento dos seus dados pessoas, sendo estes não disponíveis para terceiros.");
                alerta.setNegativeButton("Discordo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Lamento, mas é necessário " +
                                        "concordar com os termos de uso para utilizar este aplicativo",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                alerta.setPositiveButton("Concordo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Obrigado, seja bem vindo " +
                                "e conclua seu cadastro para usar o aplicativo.",Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.show();
            }
        });

        isFormularioOK = false;
        cliente = new Cliente();
    }

    private void salvarSenha() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("lembrarSenha", isLembrarSenha);
        dados.putString("email", editEmail.getText().toString());
        dados.putString("senha", editSenha.getText().toString());
        dados.apply();
    }

    private void buscarSenha() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("lembrarSenha", false);

        chlembrar.setChecked(isLembrarSenha);
        String email = preferences.getString("email", "");
        String senha = preferences.getString("senha", "");

        if(isLembrarSenha){
            editEmail.setText(email);
            editSenha.setText(senha);
        }
    }


    //Se estiver checado, irá lembrar a senha do usuario
    public void lembrarSenha(View view) {
        isLembrarSenha = chlembrar.isChecked();
    }

}