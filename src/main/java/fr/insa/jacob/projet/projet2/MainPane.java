/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import fr.insa.jacob.projet.projet2.terrain.TriangleTerrain;
import fr.insa.jacob.projet.projet2.treillis.Groupe;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */

public class MainPane extends BorderPane{
    
    private Groupe model;
    private Controleur controleur;
    private Stage inStage;
    private File curFile;
        
    private RadioButton rbSelect;
    private RadioButton rbTT;
    private RadioButton rbNoeud;
    private RadioButton rbNoeudA;
    private RadioButton rbBarre1;
    private RadioButton rbBarre2;
    
    private Button bGrouper;
    private ColorPicker cpCouleur;
    private Button bSupprimer;
    
    private DessinCanvas cDessin;
    private MainMenu menu;
    
    public MainPane(Stage inStage){
        this(inStage, new Groupe());
    }
    
    public MainPane(Stage inStage, Groupe model){
        this(inStage, null, model);
    }
    
    public MainPane(Stage inStage, File fromFile, Groupe model) {
        this.inStage = inStage;
        this.curFile = fromFile;
        this.model = model;
        this.controleur = new Controleur(this);
        
        this.rbSelect = new RadioButton("Select");
        this.rbSelect.setOnAction((t)->{
            this.controleur.boutonSelect(t);
        });
        this.rbTT = new RadioButton("Triangle Terrain");
        this.rbTT.setOnAction((t)->{//setOnAction est un écouteur
            this.controleur.boutonTT(t);
        });
        this.rbNoeud = new RadioButton("Noeud Simple");
        this.rbNoeud.setOnAction((t)->{//setOnAction est un écouteur
            this.controleur.boutonNoeud(t);
        });
        this.rbNoeudA = new RadioButton("Noeud Appui");
        this.rbNoeudA.setOnAction((t)->{//setOnAction est un écouteur
            this.controleur.boutonNoeudA(t);
        });
        this.rbBarre1 = new RadioButton("Barre Acier");
        this.rbBarre1.setOnAction((t)->{
            this.controleur.boutonBarre1(t);
        });
        this.rbBarre2 = new RadioButton("Barre Bois");
        this.rbBarre2.setOnAction((t)->{
            this.controleur.boutonBarre2(t);
        });
        
        ToggleGroup bgEtat = new ToggleGroup();//permet d'ajouter un certain nombre de bouton liés entre eux donc si un sélectionné, pas l'autre
        this.rbSelect.setToggleGroup(bgEtat);
        this.rbNoeud.setToggleGroup(bgEtat);
        this.rbNoeudA.setToggleGroup(bgEtat);
        this.rbTT.setToggleGroup(bgEtat);
        this.rbBarre1.setToggleGroup(bgEtat);
        this.rbBarre2.setToggleGroup(bgEtat);
        this.rbNoeud.setSelected(true);
        
        VBox vbGauche = new VBox(this.rbSelect, this.rbTT, this.rbNoeud, this.rbNoeudA, this.rbBarre1, this.rbBarre2);
        this.setLeft(vbGauche);
        
        this.bGrouper = new Button ("Grouper");
        this.bGrouper.setOnAction((t)->{
            this.controleur.boutonGrouper(t);
        });
        
        this.bSupprimer = new Button("Supprimer");
        this.bSupprimer.setOnAction((t)->{
            System.out.println("Bouton supprimer");
            this.controleur.boutonSupprimer(t);
        });
        
        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t)->{
            this.controleur.changeColor(this.cpCouleur.getValue());
        });
        
        VBox vbDroit = new VBox(this.bGrouper, this.bSupprimer, this.cpCouleur);
        this.setRight(vbDroit);
        
        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);
        this.controleur.changeEtat(30);
        
        this.menu = new MainMenu(this);
        this.setTop(this.menu);
        
        this.controleur.changeEtat(20);
    }
    
    public void redrawAll(){
        this.cDessin.redrawAll();
    }

    /**
     * @return the model
     */
    public Groupe getModel() {
        return model;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @return the rbSelect
     */
    public RadioButton getRbSelect() {
        return rbSelect;
    }

    public RadioButton getRbTT() {
        return rbTT;
    }   
    
    /**
     * @return the rbNoeud
     */
    public RadioButton getRbNoeud() {
        return rbNoeud;
    }

    public RadioButton getRbNoeudA() {
        return rbNoeudA;
    }
    
    /**
     * @return the rbBarre
     */
    public RadioButton getRbBarre1() {
        return rbBarre1;
    }

    public RadioButton getRbBarre2() {
        return rbBarre2;
    }
    
    /**
     * @return the bGrouper
     */
    public Button getbGrouper() {
        return bGrouper;
    }
    
    public Button getbSupprimer(){
        return bSupprimer;
    }

    /**
     * @return the cDessin
     */
    public DessinCanvas getcDessin() {
        return cDessin;
    }
    
    /**
     * @return the cpCouleur
     */
    public ColorPicker getCpCouleur() {
        return cpCouleur;
    }
    
    public Stage getInStage() {
        return inStage;
    }

    public File getCurFile() {
        return curFile;
    }

    public void setCurFile(File curFile) {
        this.curFile = curFile;
    }
}
