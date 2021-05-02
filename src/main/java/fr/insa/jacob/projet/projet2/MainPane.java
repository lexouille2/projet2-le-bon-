/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import fr.insa.jacob.projet.projet2.terrain.TriangleTerrain;
import fr.insa.jacob.projet.projet2.treillis.Groupe;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */

//this est un MainPane c'est la fenêtre
public class MainPane extends BorderPane{
    
    private Groupe modelFS;
    private GroupeTT model;
    private Controleur controleur;
    /*    private Stage inStage;*/
    
    private RadioButton rbSelect;
    private RadioButton rbNoeud;
    private RadioButton rbBarre;
    
    private Button bGrouper;
    private Button bCouleur;
    
    private DessinCanvas cDessin;
    
    public MainPane(GroupeTT model, Groupe modelFS){
        /*this.model = model;*/
        this.modelFS = modelFS;
        this.controleur = new Controleur(this);
        this.rbSelect = new RadioButton("Select");
        this.rbNoeud = new RadioButton("Noeud");
        this.rbBarre = new RadioButton("Barre");
        
        VBox vbGauche = new VBox(this.getRbSelect(), this.getRbNoeud(), this.getRbBarre());
        this.setLeft(vbGauche);
        
        this.bGrouper = new Button ("Grouper");
        this.bGrouper.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                System.out.println("bouton Grouper cliqué");
            }
        });
        this.bCouleur = new Button("Couleur");
        this.bCouleur.setOnAction((t)->{
        System.out.println("Bouton couleur ");
    });
        this.bCouleur.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent t){
                System.out.println("entered couleur en : " + t.getX() + " , " + t.getY());
            }
        });
        VBox vbDroit = new VBox(this.getbGrouper(), this.getbCouleur());
        this.setRight(vbDroit);
        
        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);
    }

    /**
     * @return the model
     */
    public GroupeTT getModel() {
        return model;
    }

    public Groupe getModelFS() {
        return modelFS;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }
    
    /*    public MainPane(Stage inStage){
    this(inStage, new GroupeTT());
    }*/
    
    /*    public MainPane(Stage inStage, GroupeTT model){
    this(inStage, null, model);
    }*/

    /**
     * @return the rbSelect
     */
    public RadioButton getRbSelect() {
        return rbSelect;
    }

    /**
     * @return the rbNoeud
     */
    public RadioButton getRbNoeud() {
        return rbNoeud;
    }

    /**
     * @return the rbBarre
     */
    public RadioButton getRbBarre() {
        return rbBarre;
    }

    /**
     * @return the bGrouper
     */
    public Button getbGrouper() {
        return bGrouper;
    }

    /**
     * @return the bCouleur
     */
    public Button getbCouleur() {
        return bCouleur;
    }

    /**
     * @return the cDessin
     */
    public DessinCanvas getcDessin() {
        return cDessin;
    }
}
