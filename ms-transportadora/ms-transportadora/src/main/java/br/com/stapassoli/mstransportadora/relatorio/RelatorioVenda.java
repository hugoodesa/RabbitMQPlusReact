package br.com.stapassoli.mstransportadora.relatorio;

import br.com.stapassoli.msloja.model.Venda;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class RelatorioVenda implements Relatorio{

    private Venda venda;
    private Document documentoPDF;
    private String caminhoRelatorio = "relarotioSimples.pdf";

    public RelatorioVenda(Venda venda) {
        this.venda = venda;
        this.documentoPDF = new Document();

        try {
            PdfWriter.getInstance(documentoPDF,new FileOutputStream(caminhoRelatorio));
            this.documentoPDF.open();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void gerarCabelho() {

    }

    @Override
    public void gerarCorpo() {

    }

    @Override
    public void gerarRodape() {

    }

    @Override
    public void imprimir() {

    }
}
