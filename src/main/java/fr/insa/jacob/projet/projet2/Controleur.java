/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */
public class Controleur {
    
    private MainPane vue;
    
    private int etat;
    
    public Controleur(MainPane vue){
        this.vue = vue;
        this.changeEtat(30);
    }
    
    public void changeEtat(int nouvelEtat){
        if (nouvelEtat == 30){
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbCouleur().setDisable(true);
        }
    }

    void clicDansZoneDessin(MouseEvent t) {
        double px = t.getX();
        double py = t.getY();
        Color col = Color.color(Math.random(), Math.random(), Math.random());
        GroupeTT model = this.vue.getModel();
        model.add(new Point(px, py));
    }
}
