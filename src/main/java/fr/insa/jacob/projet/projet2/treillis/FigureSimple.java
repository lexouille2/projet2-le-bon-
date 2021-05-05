/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import fr.insa.jacob.projet.projet2.noeud.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */
public abstract class FigureSimple extends Treillis{

    private Color couleur;
       
    public FigureSimple(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }
    
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void changeCouleur(Color value) {
        this.setCouleur(value);
    }
}
