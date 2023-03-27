package br.com.stapassoli.msloja.relatorio;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public abstract class BasicGeradorPDF<DTO> {

    protected String nomeRelatorio;
    protected PdfPTable table;
    protected List<DTO> dtos;
    protected List<String> camposRelatorio;

    public BasicGeradorPDF(String nomeRelatorio,List<String> camposRelatorio,List<DTO> dtos) {
        this.nomeRelatorio = nomeRelatorio;
        this.table = new PdfPTable(camposRelatorio.size());
        this.camposRelatorio = camposRelatorio;
        this.dtos = dtos;
    }

    public void criarArquivoPDF(String nomeArquivo, GeradorPDF pdf) throws FileNotFoundException, DocumentException {
        PdfWriter write = PdfWriter.getInstance(pdf, new FileOutputStream(nomeArquivo));
    }
    public void inicializarDocumento(GeradorPDF geradorPDF){
        geradorPDF.open();
    }

    public void cabecalho(){

        this.camposRelatorio.forEach(coluna ->{
            PdfPCell header = new PdfPCell();
            header.setBorderWidth(2f);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setPhrase(new Phrase(coluna));

            this.table.addCell(header);
        });
    }

    public abstract void gerarTabela ();

    public void GerarRelatorio() {
        try(GeradorPDF geradorPDF = new GeradorPDF()) {
            criarArquivoPDF(this.nomeRelatorio,geradorPDF);
            inicializarDocumento(geradorPDF);

            //gerar
            cabecalho();
            gerarTabela();

            geradorPDF.add(table);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}
