package com.example.horses.Dataverwerking;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class HelloApplication extends Application {
    private BestandVerwerker betaling;
    private TableView opgehaald;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(maakRoot()/*fxmlLoader.load()/*, 320, 240*/);
        stage.setTitle("KSA KAS");
        stage.setScene(scene);
        stage.setMaximized(true);

        Image icon = new Image("file:C:\\Users\\sam_v\\Desktop\\Horses\\src\\main\\java\\com\\example\\horses\\logo.png");
        stage.getIcons().add(icon);

        betaling = new BestandVerwerker();
        betaling.leesBestandIn(new File("C:\\Users\\sam_v\\Desktop\\Horses\\src\\main\\java\\com\\example\\horses\\Data\\BE49 0689 3583 1371 2022-02-02 17-45-59 1.csv"));

        stage.show();
    }

    public BorderPane maakRoot(){
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(25));
        root.setCenter(maakCenter());
        root.setBottom(maakBottom());
        return  root;
    }

    public VBox maakBottom(){
        VBox mainDoos = new VBox(20);
        HBox mededelingDoos = new HBox(10);
        HBox datumKiezer = new HBox(10);
        HBox bedragDoos = new HBox(10);

        Button zoekAllesKnop = new Button();
        zoekAllesKnop.setText("Alles");
        EventHandler<ActionEvent> event = actionEvent -> {klikZoek();};
        zoekAllesKnop.setOnAction(event);

        Button zoekMededeling = new Button();
        zoekMededeling.setText("Filter mededelingen");
        TextField mededeling = new TextField();
        Label mededelingLabel = new Label("Mededeling");
        zoekMededeling.setOnAction((e) -> {
            if (!mededeling.getText().isEmpty()){
                klikMededeling(mededeling.getText());
            }
        });
        mededelingDoos.getChildren().addAll(mededelingLabel,mededeling,zoekMededeling);

        Button zoekData = new Button();
        zoekData.setText("Filter data");
        Label beginLabel = new Label("Begin");
        Label eindLabel = new Label("Einde");
        DatePicker begin = new DatePicker();
        DatePicker eind = new DatePicker();
        eind.setValue(LocalDate.now());
        zoekData.setOnAction((e) -> {
            if (begin.getValue() != null && eind.getValue() != null ){
               klikData(new Datum(begin.getValue()),new Datum(eind.getValue()));
            }
        });
        datumKiezer.getChildren().addAll(beginLabel,begin,eindLabel,eind,zoekData);

        Button zoekBedrag = new Button();
        zoekBedrag.setText("Filter bedragen");
        Label bedragLabel = new Label("Bedrag");
        TextField bedrag = new TextField();
        zoekBedrag.setOnAction((e) -> {
            if (!bedrag.getText().isEmpty()){
                try {
                    int x = Integer.parseInt(bedrag.getText());
                    klikBedrag(x);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        bedragDoos.getChildren().addAll(bedragLabel,bedrag,zoekBedrag);


        mainDoos.getChildren().addAll(mededelingDoos,datumKiezer,bedragDoos,zoekAllesKnop);
        return mainDoos;
    }

    public ScrollPane maakCenter(){

        ScrollPane scrollPane = new ScrollPane();

        TableColumn dateCol = new TableColumn("Datum");
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Betaling,Datum>("datum")
        );

        TableColumn naamCol = new TableColumn("Naam");
        naamCol.setCellValueFactory(
                new PropertyValueFactory<Betaling,String>("naam")
        );
        naamCol.setPrefWidth(270.0);

        TableColumn bedragCol = new TableColumn("Bedrag");
        bedragCol.setCellValueFactory(
                new PropertyValueFactory<Betaling,Double>("bedrag")
        );

        TableColumn mededelingCol = new TableColumn("Mededeling");
        mededelingCol.setCellValueFactory(
                new PropertyValueFactory<Betaling,String>("mededeling")
        );
        mededelingCol.setPrefWidth(1050);

        opgehaald = new TableView();

        opgehaald.setEditable(false);
        opgehaald.getColumns().addAll(dateCol,naamCol,bedragCol,mededelingCol);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(opgehaald);

        return scrollPane;
    }

    public void klikZoek(){
        ObservableList<Betaling> data = FXCollections.observableArrayList(
            betaling.getList()
        );
        opgehaald.setItems(data);

    }

    public void klikBedrag(int x){

        ObservableList<Betaling> data = FXCollections.observableArrayList(
                betaling.bedragX(x)
        );
        opgehaald.setItems(data);
    }

    public void klikMededeling(String x){
        ObservableList<Betaling> data = FXCollections.observableArrayList(
                betaling.mededelingX(x)
        );
        opgehaald.setItems(data);
    }

    public void klikData(Datum x, Datum y){
        ObservableList<Betaling> data = FXCollections.observableArrayList(
                betaling.AllesTussen(x,y)
        );
        opgehaald.setItems(data);
    }

}