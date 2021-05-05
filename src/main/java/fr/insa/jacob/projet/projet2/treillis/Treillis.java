/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author PC
 */
public abstract class Treillis {

    public static Color COULEUR_SELECTION = Color.RED;
    
    private Groupe grpFS;
    
    public Groupe getGrpFS() {
        return grpFS;
    }

    void setGrpFS(Groupe grpFS) {
        this.grpFS = grpFS;
    }
    
    public abstract void dessine(GraphicsContext context);  
    
    public abstract double distance(Point p);
    
    public abstract void dessineSelection(GraphicsContext context);
    
}
