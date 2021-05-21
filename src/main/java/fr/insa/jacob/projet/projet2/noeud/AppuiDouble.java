/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;

import javafx.scene.paint.Color;

/**
 *
 * @author yohann
 */
public class AppuiDouble extends NoeudAppui{

    public AppuiDouble(int num, Point debutSeg, Point finSeg, int idNoeud, Point ne, Color couleur) {
        super(num, debutSeg, finSeg, idNoeud, ne, couleur);
    }

    public AppuiDouble(int num, Point debutSeg, Point finSeg, int idNoeud, Point ne) {
        super(num, debutSeg, finSeg, idNoeud, ne);
    }
    
    
    
}
