package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import br.com.exemplo.AppAfericaoDiaria.Controller.AfericaoHipertensoController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.model.AfericaoHipertensos;

public class InserirAfericaoHipertensosActivity extends AppCompatActivity {

    AfericaoHipertensos inserirAfericaoHipertensos;
    private SharedPreferences preferences;

    EditText editdata, edithora, editsis, editdia, editpulso;
    Button btnSalvarContinuar, btnCancelar;
    TextView txtData;
    boolean isFormularioOK;
    int idLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_afericao_hipertensos);

        idLogado = getIntent().getIntExtra("id", 0);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {
                    inserirAfericaoHipertensos.setData(Integer.parseInt(editdata.getText().toString()));
                    inserirAfericaoHipertensos.setHora(Integer.parseInt(edithora.getText().toString()));
                    inserirAfericaoHipertensos.setSis(editsis.getText().toString());
                    inserirAfericaoHipertensos.setDia(editdia.getText().toString());
                    inserirAfericaoHipertensos.setPulso(editpulso.getText().toString());
                    inserirAfericaoHipertensos.setIdCliente(idLogado);


                    AfericaoHipertensoController afericaoHipertensoController = new AfericaoHipertensoController(InserirAfericaoHipertensosActivity.this);
                    afericaoHipertensoController.inserirAfericaoHipertensos(inserirAfericaoHipertensos);
                    //TOAST AQUI

                    finish();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(InserirAfericaoHipertensosActivity.this);
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
        editdata = findViewById(R.id.editdata);
        edithora = findViewById(R.id.editHora);
        editsis = findViewById(R.id.editsis);
        editdia = findViewById(R.id.editdia);
        editpulso = findViewById(R.id.editpulso);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);
        isFormularioOK = false;

        inserirAfericaoHipertensos = new AfericaoHipertensos();
    }

    private void selecionarData() {
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, null, year, month, day);
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Selecionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final DatePicker datePicker = datePickerDialog.getDatePicker();
                int d = datePicker.getDayOfMonth();
                int m = datePicker.getMonth();
                int ano = datePicker.getYear();

                String dia = d > 9 ? String.valueOf(d) : "0"+d;
                String mes = m > 9 ? String.valueOf(m) : "0"+m;

                txtData.setText(String.format(Locale.getDefault(), "%s/%s/%d", dia, mes, ano));
            }
        });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", (DialogInterface.OnClickListener) null);
        datePickerDialog.show();
    }

    private boolean validarFormulario() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editdata.getText().toString())) {
            editdata.setError("Insira a data atual!");
            editdata.requestFocus();

            retorno = false;
        }
        return retorno;
    }
    }
