/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import fr.insa.jacob.projet.projet2.treillis.Groupe;
import fr.insa.jacob.projet.projet2.treillis.Treillis;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author PC
 */
public class DessinCanvas extends Pane{
    
    private MainPane main;
    
    private Canvas realCanvas;
    
    public DessinCanvas(MainPane main){
        this.main = main;
        this.realCanvas = new Canvas(this.getWidth(), this.getHeight());
        this.getChildren().add(this.realCanvas);
        //System.out.println("w = " + this.getWidth() + ", h =" + this.getHeight());
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.realCanvas.heightProperty().addListener((o)->{
            //System.out.println("w = " + this.realCanvas.getWidth() + ", h =" + this.realCanvas.getHeight());
            this.redrawAll();
        });
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.widthProperty().addListener((o)->{
            this.redrawAll();
        });
        this.realCanvas.setOnMouseClicked((t)-> {
            Controleur control = this.main.getControleur();
            control.clicDansZoneDessin(t);
        });
        this.redrawAll();
    }
    
    public void redrawAll(){
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        context.clearRect(0, 0, this.realCanvas.getWidth(), this.realCanvas.getHeight());
        Groupe model = this.main.getModel();
        model.dessine(context);
        List<Treillis> select = this.main.getControleur().getSelection();
        if(! select.isEmpty()){
            for (Treillis t : select){
                t.dessineSelection(context);
            }
        }
    }

}
