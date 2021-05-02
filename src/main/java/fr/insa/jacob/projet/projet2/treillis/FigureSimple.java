/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author PC
 */
public abstract class FigureSimple {

    private Groupe grpFS;
    
    public Groupe getGrpFS() {
        return grpFS;
    }

    public void setGrpFS(Groupe grpFS) {
        this.grpFS = grpFS;
    }

    public abstract void dessine(GraphicsContext context);  
    
}
