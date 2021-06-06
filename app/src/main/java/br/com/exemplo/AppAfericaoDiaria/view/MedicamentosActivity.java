package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

import br.com.exemplo.AppAfericaoDiaria.Controller.MedicamentoController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.model.Medicamento;

public class MedicamentosActivity extends AppCompatActivity {
    int idLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);

        idLogado = getIntent().getIntExtra("id", 0);

        initFormulario();
    }

    private void initFormulario() {
        TextView txtCabecalho = findViewById(R.id.txtCabecalhoMedicamento);
        //txtCabecalho.setText("NOME                             QUANTIDADE   FREQUENCIA");

        TextView txtCorpo = findViewById(R.id.txtCorpoMedicamento);

        MedicamentoController medicamentoController = new MedicamentoController(MedicamentosActivity.this);
        List<Medicamento> medicamentos = medicamentoController.obterDoCliente(idLogado);

        String texto = "";

        for (Medicamento medicamento : medicamentos){
            texto = texto.concat(String.format(Locale.getDefault(),
                    "%30s   %d%s   %d/%dhoras", medicamento.getNomemedicamneto(),
                    medicamento.getQuantidade(), medicamento.getunidadedemedida(),
                    medicamento.getFrequencia(), medicamento.getFrequencia()));
            texto += "\n";
        }

        txtCorpo.setText(texto);


    }


}