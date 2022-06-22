package com.svalero.TopLaptop.controller;

import com.svalero.TopLaptop.domain.Computer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import static com.svalero.TopLaptop.api.Constants.API_BASE_URL;

public class ApplicationController implements Initializable {

    public TableView<Computer> computerTable;
    public Button searchButton;
    public TextField computerIDText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareTableView();
        listAllComputers();
    }


    private void prepareTableView() {
        TableColumn<Computer, String> idComputerColumn = new TableColumn<>("Id");
        idComputerColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Computer, String> brandComputerColumn = new TableColumn<>("Brand");
        brandComputerColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        TableColumn<Computer, String> modelComputerColumn = new TableColumn<>("Model");
        modelComputerColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        computerTable.getColumns().add(idComputerColumn);
        computerTable.getColumns().add(brandComputerColumn);
        computerTable.getColumns().add(modelComputerColumn);

    }


    public void listAllComputers() {
        computerTable.getItems().clear();
        WebClient webClient = WebClient.create(API_BASE_URL);
        Flux<Computer> computersFlux = webClient.get()
                .uri("/computers")
                .retrieve()
                .bodyToFlux(Computer.class);

        computersFlux.subscribeOn(Schedulers.fromExecutor(Executors.newCachedThreadPool()))
                .subscribe(computerTable.getItems()::add);
    }





    @FXML
    public void searchComputer(ActionEvent event) {
        computerTable.getItems().clear();
        String searchText = computerIDText.getText();
        computerIDText.clear();
        computerIDText.requestFocus();
        WebClient webClient = WebClient.create(API_BASE_URL);
        Flux<Computer> computersFlux = webClient.get()
                .uri("/computersbrand?brand=" + searchText)
                .retrieve()
                .bodyToFlux(Computer.class);

        computersFlux.subscribeOn(Schedulers.fromExecutor(Executors.newCachedThreadPool()))
                .subscribe(computerTable.getItems()::add);
    }



    @FXML
    private void computerInformation(ActionEvent event){


        Computer c = (Computer) this.computerTable.getSelectionModel().getSelectedItem();


        String idComputer = c.getId();
        String  brandComputer = c.getBrand();
        String modelComputer = c.getModel();
        String ramComputer = c.getRam();



        /*
        private long id;
        private String brand;
        private String model;
        private String ram;
        private byte[] computerImage;
        private User user;
         */

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Id del ordenador="+ idComputer +",   marca del ordenador= " +
                brandComputer + ",   modelo del ordenador="+ modelComputer +
                ",   gigas de RAM del oredenador = "+ ramComputer);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();


    }





}
