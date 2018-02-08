/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



/**
 *
 * @author Adam
 */
public class GerarComprovantePDF {
    String mes;
    
   public GerarComprovantePDF(String local_arquivo, float valor, String destinatario, int num_nota_fiscal,int data_pagamento_mes,int dia, int ano){
        //Criação do Documento em PDF
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream(local_arquivo + "/teste.pdf"));
            document.open();

            //Adiciona o logo da empresa dentro de um retângulo
            String caminho = new File("./src/main/resources/img/IMG_02_LogoBetaTransporteComprovantePNG.png").getCanonicalPath();
            Image figura = Image.getInstance(caminho);
            figura.scaleToFit(550, 550);
            figura.setAlignment(1);
            document.add(figura);
            //Fim

            //Texto referente ao "RECIBO" e "RS"
            document.add(new Paragraph("_____________________________________________________________________________"));
            Paragraph p1 = new Paragraph("RECIBO        RS = " + valor,
                    FontFactory.getFont(FontFactory.COURIER_BOLD, 30, Font.BOLD));
            document.add(p1);
            //Fim
            document.add(new Paragraph(" "));

            Paragraph p2 = new Paragraph("Recebi(emos) de " + destinatario + " a importância de R$ " + valor + " reais referente ao transporte da mercadoria cujo"
                    + " número da nota fiscal é " + num_nota_fiscal);
            document.add(p2);
            document.add(new Paragraph(" "));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph p3 = new Paragraph("Para maior clareza firmamos o presente recibo");
            p3.setAlignment(2);
            document.add(p3);

            switch (data_pagamento_mes) {
                case 1:
                    mes = "Janeiro";
                    break;
                case 2:
                    mes = "Fevereiro";
                    break;
                case 3:
                    mes = "Março";
                    break;
                case 4:
                    mes = "Abril";
                    break;
                case 5:
                    mes = "Maio";
                    break;
                case 6:
                    mes = "Junho";
                    break;
                case 7:
                    mes = "Julho";
                    break;
                case 8:
                    mes = "Agosto";
                    break;
                case 9:
                    mes = "Setembro";
                    break;
                case 10:
                    mes = "Outubro";
                    break;
                case 11:
                    mes = "Novembro";
                    break;
                case 12:
                    mes = "Dezembro";
                    break;
            }

            Paragraph p4 = new Paragraph("Cuiabá, " + dia + 
            " de " +mes+ " de "+ano);

            p4.setAlignment(2);
            document.add(p4);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph p5 = new Paragraph(".......................................................");
            p5.setAlignment(2);
            document.add(p5);

            Paragraph p6 = new Paragraph("Assinatura e Carimbo");
            p6.setAlignment(2);
            document.add(p6);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        document.close();
   }
    
    
}
    
    
    
    
    

