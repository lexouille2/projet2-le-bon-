/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC
 */

//this est un MainPane c'est la fenêtre
public class MainPane extends BorderPane{
    
    private RadioButton rbSelect;
    private RadioButton rbNoeud;
    private RadioButton rbBarre;
    
    private Button bGrouper;
    private Button bCouleur;
    
    private DessinCanvas1 cDessin;
    
    public MainPane(){
        this.rbSelect = new RadioButton("Select");
        this.rbNoeud = new RadioButton("Noeud");
        this.rbBarre = new RadioButton("Barre");
        
        VBox vbGauche = new VBox(this.rbSelect,this.rbNoeud,this.rbBarre);
        this.setLeft(vbGauche);
        
        this.bGrouper = new Button ("Grouper");
        this.bCouleur = new Button("Couleur");
        
        VBox vbDroit = new VBox(this.bGrouper,this.bCouleur);
        this.setRight(vbDroit);
        
        this.cDessin = new DessinCanvas1(200,200);
        this.setCenter(this.cDessin);
    }
}
