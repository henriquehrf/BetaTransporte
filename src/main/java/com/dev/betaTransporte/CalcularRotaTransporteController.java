/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.dao.RotaDAO;
import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporte.vo.Rota;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Status;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import util.GerarPDF;
import util.Navegation;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class CalcularRotaTransporteController implements Initializable {

    @FXML
    private TableColumn<Encomenda, String> tbcCpfCnpj;

    @FXML
    private TableColumn<Encomenda, String> tbcNumNotaFiscal;

    @FXML
    private TableColumn<Encomenda, String> tbcDestino;

    @FXML
    private TableColumn<Rota, String> tbcTempo;

    @FXML
    private TextField txtGaragem;

    @FXML
    private TableColumn<Encomenda, String> tbcCodigo;

    @FXML
    private TextField txtDestinoFinal;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Rota, String> tbcDistancia;

    @FXML
    private TableColumn<Rota, String> tbcRota;

    @FXML
    private TextField txtTotalItem;

    @FXML
    private StackPane stpCalcularRota;
    
    @FXML
    private TableColumn<Encomenda, String> tbcRotaEncomenda;

    @FXML
    private TableColumn<Rota, String> tbcOrigemRota;

    @FXML
    private TableView<Encomenda> tbvListaEncomenda;

    @FXML
    private TableColumn<Rota, String> tbcDestinoRota;

    @FXML
    private TextField txtTempo;

    @FXML
    private Button btnGerarRelatorio;

    @FXML
    private TableView<Rota> tbvRotas;

    List<Rota> rotas = null;
    Navegation navegation = new Navegation();
    private ObservableList<Encomenda> EncomendaList = FXCollections.observableArrayList();
    private ObservableList<Rota> RotaList = FXCollections.observableArrayList();

    @FXML
    void btnGerarRelatorioOnAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);

        GerarPDF pdf = new GerarPDF();

        try {
            pdf.solicitarEmprestimo(EncomendaList, tbvRotas.getItems(), selectedDirectory.getAbsolutePath(),txtDestinoFinal.getText(),txtGaragem.getText(),txtTempo.getText());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnGerarRelatorioOnKeyPressed(ActionEvent event) {

    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) {
        navegation.getFather(this.stpCalcularRota);
    }

    @FXML
    void btnVoltarOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            navegation.getFather(this.stpCalcularRota);
        }
    }

    void completarTabelaRota(List<Rota> list) {

        RotaList.addAll(list);
        this.tbcRota.setCellValueFactory(new PropertyValueFactory<Rota, String>("CodigoRota"));
        this.tbcOrigemRota.setCellValueFactory(new PropertyValueFactory<Rota, String>("Origem"));
        this.tbcDestinoRota.setCellValueFactory(new PropertyValueFactory<Rota, String>("Destino"));
        this.tbcDistancia.setCellValueFactory(new PropertyValueFactory<Rota, String>("DistanciaFormat"));
        this.tbcTempo.setCellValueFactory(new PropertyValueFactory<Rota, String>("TempoFormat"));

        tbvRotas.setItems(RotaList);

        txtDestinoFinal.setText("" + RotaList.get(0).getDestino());
        txtGaragem.setText("" + RotaList.get(0).getGaragem());
        txtTempo.setText("" + RotaList.get(0).getTempoFormat());
        txtTotalItem.setText("" + EncomendaList.size());
    }

    void completarTabelaEncomenda(List<Encomenda> list) {

        EncomendaList.addAll(list);
        this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("Codigo"));
        this.tbcNumNotaFiscal.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("NumNotaFiscal"));
        this.tbcCpfCnpj.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("CpfCnpjDestinatario"));
        this.tbcDestino.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("CidadeDestino"));
        this.tbcRotaEncomenda.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("RotaCodigo"));

        tbvListaEncomenda.setItems(EncomendaList);

    }

    List<Encomenda> addMock() {
        List<Encomenda> dados = new ArrayList<>();

        //------------------------
        Encomenda enc1 = new Encomenda();
        enc1.setID(1l);
        enc1.setCidadeDestino(Cidade.CONFRESA);
        enc1.setCidadeOrigem(Cidade.ALTA_FLORESTA);
        enc1.setCpfCnpjDestinatario("72.226.737/0001-38");
        enc1.setNumNotaFiscal(331400023);
        dados.add(enc1);

        //------------------------
        Encomenda enc2 = new Encomenda();
        enc2.setID(2l);
        enc2.setCidadeDestino(Cidade.RONDONOPOLIS);
        enc2.setCidadeOrigem(Cidade.ALTA_FLORESTA);
        enc2.setCpfCnpjDestinatario("72.226.737/0001-38");
        enc2.setNumNotaFiscal(33141223);
        dados.add(enc2);
        //------------------------
        Encomenda enc3 = new Encomenda();
        enc3.setID(3l);
        enc3.setCidadeDestino(Cidade.RONDONOPOLIS);
        enc3.setCidadeOrigem(Cidade.ALTA_FLORESTA);
        enc3.setCpfCnpjDestinatario("72.226.737/0001-38");
        enc3.setNumNotaFiscal(4343);
        dados.add(enc3);

        //------------------------
        Encomenda enc4 = new Encomenda();
        enc4.setID(4l);
        enc4.setCidadeDestino(Cidade.CUIABA);
        enc4.setCidadeOrigem(Cidade.ALTA_FLORESTA);
        enc4.setCpfCnpjDestinatario("72.226.737/0001-38");
        enc4.setNumNotaFiscal(3123);
        dados.add(enc4);

        return dados;

    }

    void gerarRota(List<Encomenda> list) {

        List<Encomenda> result = list;
        List<Rota> rotaFinal = new ArrayList<>();
        Rota rota = new Rota();

        for (Rota vo : rotas) {
            if (list.get(0).getCidadeOrigem().equals(vo.getOrigem())
                    && vo.getDestino().equals(Cidade.CUIABA)) {
                rota = vo;
            }
        }

        rotaFinal.add(rota);
        for (Encomenda vo : list) {
            vo.setId_rota(rota);
            if (vo.getCidadeDestino() != rota.getDestino()) {
                vo.setStatus(Status.Encomenda_em_transporte_para_o_centro_de_distribuição);
            } else {
                vo.setStatus(Status.Encomenda_em_transporte_para_a_cidade_de_destino);
            }
            System.out.println(vo.getRotaCodigo());
        }

        completarTabelaEncomenda(result);
        completarTabelaRota(rotaFinal);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtDestinoFinal.setEditable(false);
        txtGaragem.setEditable(false);
        txtTempo.setEditable(false);
        txtTotalItem.setEditable(false);

        List<Encomenda> mock = new ArrayList<>();
        try {
            RotaDAO dao = new RotaDAO();
            rotas = dao.GetAll();
            mock = addMock();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        gerarRota(mock);

    }

}
