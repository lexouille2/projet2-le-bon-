/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author PC
 */
public class DessinCanvas extends Pane{
    
    private Canvas realCanvas;
    
    public DessinCanvas(){
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
        this.redrawAll();
    }
    
    public void redrawAll(){
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        context.setFill(Color.CORAL);
        context.fillRect(0, 0, this.realCanvas.getWidth(), this.realCanvas.getHeight());
    }

}
