package br.com.stapassoli.msloja.relatorio;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;

public class GeradorPDF extends Document implements AutoCloseable {

    public GeradorPDF() {
    }

    public GeradorPDF(Rectangle pageSize) {
        super(pageSize);
    }

    public GeradorPDF(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom) {
        super(pageSize, marginLeft, marginRight, marginTop, marginBottom);
    }
}
