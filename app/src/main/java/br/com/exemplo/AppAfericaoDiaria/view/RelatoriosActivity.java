package br.com.exemplo.AppAfericaoDiaria.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import br.com.exemplo.AppAfericaoDiaria.Controller.AfericaoDiabeticoController;
import br.com.exemplo.AppAfericaoDiaria.Controller.AfericaoHipertensoController;
import br.com.exemplo.AppAfericaoDiaria.R;
import br.com.exemplo.AppAfericaoDiaria.model.AfericaoDiabeticos;
import br.com.exemplo.AppAfericaoDiaria.model.AfericaoHipertensos;

public class RelatoriosActivity extends AppCompatActivity {
    TextView txtCabecalho, txtCorpo;
    Button btnRelatorioDiabetico, btnRelatorioHipertenso, btnMedicamentos;
    int idLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        idLogado = getIntent().getIntExtra("id", 0);

        initFormulario();
    }

    private void initFormulario() {
        txtCabecalho = findViewById(R.id.txtCabecalhoRelatorio);
        txtCorpo = findViewById(R.id.txtCorpoRelatorio);

        btnRelatorioDiabetico = findViewById(R.id.btnGerarRelatorioDiabetico);
        btnRelatorioDiabetico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarPDFDiabetico();


                /*txtCabecalho.setText("DATA         HORA    GLICOSE");
                AfericaoDiabeticoController afericaoDiabeticoController = new AfericaoDiabeticoController(RelatoriosActivity.this);
                List<AfericaoDiabeticos> resultados = afericaoDiabeticoController.obterDoCliente(idLogado);

                String texto = "";
                for (AfericaoDiabeticos afericao : resultados){

                    texto = texto.concat(afericao.getData() + "   "+afericao.getHora()+"   "+afericao.getNivelglicose() + "     "+afericao.getobservacao());
                    texto += "\n";
                }

                txtCorpo.setText(texto);*/
            }
        });
        btnRelatorioHipertenso = findViewById(R.id.btnGerarRelatorioHipertenso);
        btnRelatorioHipertenso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarPDFHipertenso();
                /*txtCabecalho.setText("DATA         HORA   SIS   DIA   PULSO");


                String texto = "";
                for (AfericaoHipertensos afericao : resultados) {
                    texto = texto.concat(afericao.getData() + "   " + afericao.getHora() +
                            "   " + afericao.getSis() + "   " + afericao.getDia());
                    texto += "\n";
                }
                txtCorpo.setText(texto);*/
            }
        });

        btnMedicamentos = findViewById(R.id.btnMedicamentos);
        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RelatoriosActivity.this, MedicamentosActivity.class);
                intent.putExtra("id", idLogado);
                startActivity(intent);
            }
        });
    }

    private void gerarPDFHipertenso() {
        String filename = "relatorio_diabetico.pdf";
        //File path = new File(Environment.getExternalStorageDirectory(),"");
        File pdffile = new File(Environment.getExternalStorageDirectory(), filename);

        try {
            PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(pdffile));
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            Document document = new Document(pdfDocument);
            document.setMargins(0,0,0,0);

            float larguraColunas[] = new float[]{120, 220, 120, 100};
            Table table = new Table(larguraColunas);

            table.addCell(new Cell().add(new Paragraph("Meu Titulo 1").setFontSize(14).setFontColor(ColorConstants.BLACK).setBorder(Border.NO_BORDER)));

            AfericaoHipertensoController afericaoHipertensoController = new AfericaoHipertensoController(RelatoriosActivity.this);
            List<AfericaoHipertensos> resultados = afericaoHipertensoController.obterDoCliente(idLogado);

            for(AfericaoHipertensos afericaoHipertenso : resultados)


            document.add(table);
            document.close();
            Toast.makeText(this, "pdf salvo", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        Toast.makeText(this, "Abrindo...", Toast.LENGTH_SHORT).show();
        File file = new File(Environment.getExternalStorageDirectory().getPath()+"/"+filename);
        //criar o intent para visualização do documento
        Uri caminho = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(caminho, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }

    private void gerarPDFDiabetico() {
        String filename = "relatorio_diabetico.pdf";
        //File path = new File(Environment.getExternalStorageDirectory(),"");
        File pdffile = new File(Environment.getExternalStorageDirectory(), filename);

        try {
            PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(pdffile));
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            Document document = new Document(pdfDocument);
            document.setMargins(0,0,0,0);

            float larguraColunas[] = new float[]{120, 220, 120, 100};
            Table table = new Table(larguraColunas);

            table.addCell(new Cell().add(new Paragraph("Meu Titulo 1").setFontSize(14).setFontColor(ColorConstants.BLACK).setBorder(Border.NO_BORDER)));

            AfericaoDiabeticoController afericaoDiabeticoController = new AfericaoDiabeticoController(RelatoriosActivity.this);
            List<AfericaoDiabeticos> resultados = afericaoDiabeticoController.obterDoCliente(idLogado);

            for (AfericaoDiabeticos afericaoDiabeticos : resultados)

            document.add(table);
            document.close();
            Toast.makeText(this, "pdf salvo", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        Toast.makeText(this, "Abrindo...", Toast.LENGTH_SHORT).show();
        File file = new File(Environment.getExternalStorageDirectory().getPath()+"/"+filename);
        //criar o intent para visualização do documento
        Uri caminho = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(caminho, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }
}