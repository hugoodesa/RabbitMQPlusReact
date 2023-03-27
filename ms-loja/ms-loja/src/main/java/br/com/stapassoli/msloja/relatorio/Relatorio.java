package br.com.stapassoli.msloja.relatorio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Relatorio {

    PdfWriter writer;

    PdfPTable table;

    public void addTableHeader (PdfPTable table){
        List<String> colunas = List.of("Coluna 1", "Coluna 2", "Coluna 3");

        colunas.forEach(coluna->{
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2f);
            header.setPhrase(new Phrase(coluna));

            table.addCell(header);
        });
    }
    public void addRow (PdfPTable table){

        table.addCell("linha 1 , col 1");
        table.addCell("linha 1 , col 2");
        table.addCell("linha 1 , col 3");

    }

    public Relatorio() {
        try(GeradorPDF document = new GeradorPDF();) {
            this.table = new PdfPTable(3);

            this.writer = PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld1.pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER,16, BaseColor.BLACK);

            addTableHeader(table);
            addRow(table);
            addRow(table);

            document.add(table);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
