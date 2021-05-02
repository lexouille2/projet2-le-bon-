package fr.insa.jacob.projet.projet2;

import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        //var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new MainPane(GroupeTT.groupeTest()),800,600);
        stage.setScene(scene);
        stage.show();
    }
    
    /*    public void start(Stage stage){
    Scene sc = new Scene (new MainPane(GroupeTT.groupeTest()), 800,600);
    stage.setScene(sc);
    stage.show();
    }*/
    
    public static void main(String[] args) {
        launch();
    }

}