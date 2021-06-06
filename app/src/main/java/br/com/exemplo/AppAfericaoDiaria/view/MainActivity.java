package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.com.exemplo.AppAfericaoDiaria.Controller.ClienteController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.model.Cliente;

public class MainActivity extends AppCompatActivity {
    Cliente cliente;
    TextView txtNomeCliente;
    int idLogado;
    Button  btnRelatorios;


    private SharedPreferences preferences;
    List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idLogado = getIntent().getIntExtra("id", 0);

        initFormulario();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ClienteController clienteController = new ClienteController(MainActivity.this);
        cliente = clienteController.obter(idLogado);
        txtNomeCliente.setText("Bem vindo, " + cliente.getNome());
    }

    private void initFormulario() {
        txtNomeCliente = findViewById(R.id.txtNomeCliente);

        btnRelatorios = findViewById(R.id.btnRelatorios);
        btnRelatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RelatoriosActivity.class);
                intent.putExtra("id", idLogado);
                startActivity(intent);
            }
        });
    }

    public void atualizarMeusDados(View view) {
        Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
        intent.putExtra("id", idLogado); //passagem de valor id para mainactivity
        startActivity(intent);
    }

    /**
     * Para excluir a conta, basta declarar os objetos clientes novamente(que virão zerados) e, depois,
     * salvar os dados em branco. Isso sobrescreverá os dados antigos do usuário.
     * @param view
     */

    public void sairDoAplicativo(View view) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Sair do aplicativo");
        alerta.setMessage("Confirma sua saída do aplicativo?");
        alerta.setNegativeButton("Retornar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alerta.show();
    }

    public void btnInserirAfericaoHipertensos(View view) {
        Intent intent;
        intent = new Intent(MainActivity.this, InserirAfericaoHipertensosActivity.class);
        intent.putExtra("id", idLogado);
        startActivity(intent);
    }
    public void btninserirMedicamento(View view) {
        Intent intent;
        intent = new Intent(MainActivity.this,InserirMedicamentoActivity.class);
        intent.putExtra("id", idLogado);
        startActivity(intent);
    }
    public void btninserirAfericaoDiabeticos(View view) {
        Intent intent;
        intent = new Intent(MainActivity.this, InserirAfericaoDiabeticosActivity.class);
        intent.putExtra("id", idLogado);
        startActivity(intent);
    }
    /*picker1 do NumberPicker privado;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout. activity_main );
        picker1 = findViewById (R.id. numberpicker_main_picker );
    }*/


}



