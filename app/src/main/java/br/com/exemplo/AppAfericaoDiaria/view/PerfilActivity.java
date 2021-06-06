package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.exemplo.AppAfericaoDiaria.Controller.ClienteController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.model.AfericaoHipertensos;
import br.com.exemplo.AppAfericaoDiaria.model.Cliente;

public class PerfilActivity extends AppCompatActivity {

    Button btnSalvar;
    EditText editNome, editEmail, editCPF, editDataNascimento, editPeso, editAltura;
    Cliente cliente;
    ClienteController clienteController;
    int idLogado;
    boolean isFormularioOK;
   // private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        idLogado = getIntent().getIntExtra("id", 0);

        clienteController = new ClienteController(PerfilActivity.this);
        cliente = clienteController.obter(idLogado);

        initFormulario();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nesta logica, se algum campo deixar de ser preenchido, nao deixara clicar no botao e
                //ir para outra tela, apenas se todos os dados forem preenchidos e os termos checked
                isFormularioOK = true;

                if(TextUtils.isEmpty(editNome.getText().toString())){
                    editNome.setError("O nome é obrigatório!");
                    editNome.requestFocus();
                    isFormularioOK = false;
                }

                if(TextUtils.isEmpty(editCPF.getText().toString())){
                    editCPF.setError("O CPF é obrigatório!");
                    editCPF.requestFocus();
                    isFormularioOK = false;
                }

                if(isFormularioOK) {
                    cliente.setNome(editNome.getText().toString());
                    cliente.setCpf(editCPF.getText().toString());
                    cliente.setEmail(editEmail.getText().toString());

                    clienteController.atualizar(cliente);

                    finish();
                }
            }
        });
    }

    //Inicialização das variáveis
    private void initFormulario() {
        btnSalvar = findViewById(R.id.btnSalvar);
        editNome = findViewById(R.id.editNome);
        editNome.setText(cliente.getNome());

        editCPF = findViewById(R.id.editCPF);
        editCPF.setText(cliente.getCpf());

        editEmail = findViewById(R.id.editEmail);
        editEmail.setText(cliente.getEmail());

        isFormularioOK = false;
    }
}











