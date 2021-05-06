/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author PC
 */
public abstract class Treillis {

    public static Color COULEUR_SELECTION = Color.RED;
    
    private Groupe grp;
    
    public Groupe getGrp() {
        return grp;
    }

    void setGrp(Groupe grp) {
        this.grp = grp;
    }
    
    public abstract void dessine(GraphicsContext context);  
    
    public abstract double distance(Point p);
    
    public abstract void dessineSelection(GraphicsContext context);
    
    public abstract void changeCouleur(Color value);
    
    public abstract void save(Writer w, Identificateur<Treillis> num) throws IOException;
}
