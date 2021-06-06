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

import java.util.InputMismatchException;

import br.com.exemplo.AppAfericaoDiaria.Controller.ClienteController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.model.Cliente;

public class novoclienteActivity extends AppCompatActivity {

    Button btnCadastro;
    EditText editNome, editEmail, editCPF, editSenhaA, editSenhaB;
    CheckBox ckTermo;
    boolean isFormularioOK;
    private SharedPreferences preferences;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novocliente);

        initFormulario();

        btnCadastro.setOnClickListener(new View.OnClickListener() {
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

                if(validaCPF(editCPF.getText().toString())){
                    editCPF.setError("CPF inválido");
                    editCPF.requestFocus();
                    isFormularioOK = false;
                }

                if(TextUtils.isEmpty(editEmail.getText().toString())){
                    editEmail.setError("O email é obrigatório!");
                    editEmail.requestFocus();
                    isFormularioOK = false;
                }

                if(TextUtils.isEmpty(editSenhaA.getText().toString())){
                    editSenhaA.setError("A senha é obrigatória!");
                    editSenhaA.requestFocus();
                    isFormularioOK = false;
                }

                if(TextUtils.isEmpty(editSenhaB.getText().toString())){
                    editSenhaB.setError("A confirmação da senha é obrigatória!");
                    editSenhaB.requestFocus();
                    isFormularioOK = false;
                }

                //Se os termos de uso nao tiver marcado, desativa botao de finalizar
                if(!ckTermo.isChecked()){
                    isFormularioOK = false;
                }

                if(isFormularioOK) {
                    if(!validarSenha()){
                        editSenhaA.setError("As senhas não conferem!");
                        editSenhaB.setError("As senhas não conferem!");
                        editSenhaA.requestFocus();

                        AlertDialog.Builder alerta = new AlertDialog.Builder(novoclienteActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setIcon(R.mipmap.ic_launcher_round);
                        alerta.setMessage("As senhas não são iguais");
                        alerta.setPositiveButton("Continuar", null);
                        alerta.show();

                    }else {
                        Cliente novocliente = new Cliente();
                        novocliente.setNome(editNome.getText().toString());
                        novocliente.setCpf(editCPF.getText().toString());
                        novocliente.setEmail(editEmail.getText().toString());
                        novocliente.setSenha(editSenhaA.getText().toString());

                        ClienteController clienteController = new ClienteController(novoclienteActivity.this);
                        clienteController.inserir(novocliente);

                        finish();
                    }
                }
            }
        });
    }

    //Inicialização das variáveis
    private void initFormulario() {
        btnCadastro = findViewById(R.id.btnCadastro);
        editNome = findViewById(R.id.editNome);
        editCPF = findViewById(R.id.editCPF);
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenhaA);
        editSenhaB = findViewById(R.id.editSenhaB);
        ckTermo = findViewById(R.id.ckTermo);

        isFormularioOK = false;

    }

    public void validarTermo(View view) {
        if(!ckTermo.isChecked()){
            Toast.makeText(getApplicationContext(), "É necessário aceitar os termos de uso para continuar o cadastro...",
                    Toast.LENGTH_LONG).show();
        }
    }

    public boolean validarSenha() {
        boolean retorno = false;
        String senhaA, senhaB;

        senhaA = editSenhaA.getText().toString();
        senhaB = editSenhaB.getText().toString();
        retorno = (senhaA.equals(senhaB));

        return retorno;
    }

    public static boolean validaCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return false;

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
}