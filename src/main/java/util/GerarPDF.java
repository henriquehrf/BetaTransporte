/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporte.vo.Rota;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class GerarPDF {

    private PdfPTable gerarTabelaEncomenda(List<Encomenda> encomenda) {
        // a table with three columns
        PdfPTable table = new PdfPTable(5);
        // the cell object
        PdfPCell cell;
        table.addCell("CÓDIGO");
        table.addCell("Nº NOTA FISCAL");
        table.addCell("CPF/CNPJ");
        table.addCell("CIDADE DESTINO");
        table.addCell("ROTA");
        table.completeRow();
        for (int i = 0; i < encomenda.size(); i++) {
            table.addCell("" + encomenda.get(i).getCodigo());
            table.addCell("" + encomenda.get(i).getNumNotaFiscal());
            table.addCell(encomenda.get(i).getCpfCnpjDestinatario());
            table.addCell("" + encomenda.get(i).getCidadeDestino());
            table.addCell(encomenda.get(i).getRotaCodigo());
            table.completeRow();
        }

        return table;
    }

    private PdfPTable gerarTabelaRota(List<Rota> rota) {
        // a table with three columns
        PdfPTable table = new PdfPTable(5);
        // the cell object
        PdfPCell cell;
        table.addCell("CÓDIGO");
        table.addCell("CIDADE ORIGEM");
        table.addCell("CIDADE DESTINO");
        table.addCell("DISTÂNCIA");
        table.addCell("TEMPO ESTIMADO");
        table.completeRow();
        for (int i = 0; i < rota.size(); i++) {
            table.addCell("" + rota.get(i).getCodigoRota());
            table.addCell("" + rota.get(i).getOrigem());
            table.addCell("" + rota.get(i).getDestino());
            table.addCell("" + rota.get(i).getDistanciaFormat());
            table.addCell(rota.get(i).getTempoFormat());
            table.completeRow();
        }

        return table;
    }

    void pdf(List<Encomenda> encomendas, List<Rota> rotas, String url, String destino, String garagem, String tempo, Boolean isMotorista) throws Exception {
        Date dt = new Date();
        String pattern = "dd/MM/yyyy";
        String dtSolicitacao = new SimpleDateFormat(pattern).format(dt);

// criação do documento
        Document document = new Document();
        document.addCreationDate();
        document.addAuthor("Beta Transporte");
        document.addProducer();

        if (isMotorista) {
            PdfWriter.getInstance(document, new FileOutputStream(url + "/Relatorio_Rota_Motorista" + dt.getTime() + ".pdf"));
        } else {
            PdfWriter.getInstance(document, new FileOutputStream(url + "/Relatorio_Rota_Carregador" + dt.getTime() + ".pdf"));

        }
        document.open();
        document.addCreationDate();
        //\src\main\resources\img
        String caminho = new File("./src/main/resources/img/IMG_01_LogoBetaTransportePNG.png").getCanonicalPath();
        Image figura = Image.getInstance(caminho);
         figura.scaleToFit(100, 100);
         figura.setAlignment(1);
        document.add(figura);

        Paragraph p = new Paragraph("Beta Transporte - Relatório de Encomendas e Rotas");
        p.setAlignment(1);
        document.add(p);
        document.add(new Paragraph(" "));
        Paragraph e = new Paragraph("Encomendas");
        e.setAlignment(1);
        document.add(e);
        document.add(new Paragraph(" "));
        document.add(gerarTabelaEncomenda(encomendas));
        document.add(new Paragraph(" "));
        Paragraph r = new Paragraph("Rotas");
        r.setAlignment(1);
        document.add(r);
        document.add(new Paragraph(" "));
        document.add(gerarTabelaRota(rotas));
        document.add(new Paragraph(" "));

        Paragraph dados = new Paragraph("\nTotal de Itens: " + encomendas.size() + "\nDestino Final: " + destino + "\nGaragem: " + garagem + "\nTempo Estimado: " + tempo + "\nData do Relatório: " + dtSolicitacao);
        dados.getIndentationLeft();
        document.add(dados);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Assinatura do Motorista:"));
        document.add(new Paragraph("Assinatura do Carregador:"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        if (isMotorista) {
            document.add(new Paragraph("Via do Motorista"));
        } else {
            document.add(new Paragraph("Via do Carregador"));
        }

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));

        document.close();

        Document document2 = new Document();
        document2.addCreationDate();
        document2.addAuthor("Beta Transporte");
        document2.addProducer();

    }

    public void solicitarEmprestimo(List<Encomenda> encomendas, List<Rota> rotas, String url, String destino, String garagem, String tempo) throws Exception {

        pdf(encomendas, rotas, url, destino, garagem, tempo, true);
        pdf(encomendas, rotas, url, destino, garagem, tempo, false);
    }

}
