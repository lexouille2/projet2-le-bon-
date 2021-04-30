/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;

import fr.insa.jacob.projet.projet2.terrain.GroupeTT;

/**
 *
 * @author PC
 */
public class NoeudSimple extends Noeud{
    
    private Point noeudS;

    public NoeudSimple(Point noeudS, int idNoeud) {
        super(idNoeud);
        this.noeudS = noeudS;
    }

    public Point getNoeudS() {
        return noeudS;
    }
    
}
