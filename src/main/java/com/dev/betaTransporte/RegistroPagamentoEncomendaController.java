/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import util.BoxInfo;
import util.GerarComprovantePDF;
import util.Message;
import util.Navegation;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RegistroPagamentoEncomendaController implements Initializable {

    @FXML
    private RadioButton rdbSim;

    @FXML
    private DatePicker dtpDataPagamento;

    @FXML
    private TextField txtNumNotaFiscal;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnGeraComprovante;

    @FXML
    private ToggleGroup Escolha;

    @FXML
    private TextField txtDestinatario;

    @FXML
    private RadioButton rdbNao;

    @FXML
    private TextField txtRemetente;

    @FXML
    private TextField txtValorDeclarado;

    @FXML
    private StackPane stpRegistroPagamentoEncomenda;

    @FXML
    private BorderPane bdpRegistroPagamentoEncomenda;

    Navegation navegation = new Navegation();

    @FXML
    void VoltarOnAction(ActionEvent event) {
        navegation.getFather(stpRegistroPagamentoEncomenda);
    }

    BoxInfo box = new BoxInfo();
    Message msg = new Message();

    @FXML
    void GeraComprovanteOnAction(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);

        GerarComprovantePDF pdf = new GerarComprovantePDF(selectedDirectory.getAbsolutePath(),
                Float.parseFloat(this.txtValorDeclarado.getText()),
                txtDestinatario.getText(),
                Integer.parseInt(this.txtNumNotaFiscal.getText()),
                this.dtpDataPagamento.getValue().getMonthValue(),
                this.dtpDataPagamento.getValue().getDayOfMonth(),
                this.dtpDataPagamento.getValue().getYear());

        box.BoxInfo(Alert.AlertType.INFORMATION, msg.message("suss.title.Insert"), msg.message("suss.msg.Pagamento"));
        

        this.txtValorDeclarado.setText("");
        this.txtDestinatario.setText("");
        this.txtNumNotaFiscal.setText("");
        this.txtRemetente.setText("");
        this.dtpDataPagamento.setValue(null);
        this.rdbNao.setSelected(true);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
